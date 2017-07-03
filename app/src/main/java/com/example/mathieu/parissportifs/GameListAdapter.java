package com.example.mathieu.parissportifs;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by apprenti on 03/06/17.
 */

public class GameListAdapter extends Firebaseadapter <NewGame> {

    TextView homeTeam;
    TextView scoreHomeTeam;
    TextView scoreAwayTeam;
    TextView awayTeam;
    TextView hour;
    ImageView imageViewAway;
    ImageView imageViewHome;
    public DatabaseReference mDatabaseref;


    public GameListAdapter(Query ref, Activity activity, int layout) {
        super(ref, NewGame.class, layout, activity);

    }


    @Override
    protected void populateView(View view, NewGame mNewGame, int position) {


        String prout = mNewGame.getmReportDate();
        mDatabaseref = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_GAMES).child(mNewGame.getmReportDate()).child(mNewGame.getmIdGame());

        hour = (TextView) view.findViewById(R.id.textViewHour);
        homeTeam = (TextView) view.findViewById(R.id.textViewHomeTeam);
        awayTeam = (TextView) view.findViewById(R.id.textViewAwayTeam);
        scoreHomeTeam = (TextView) view.findViewById(R.id.textViewScoreHome);
        scoreAwayTeam = (TextView) view.findViewById(R.id.textViewScoreAway);
        imageViewAway = (ImageView) view.findViewById(R.id.imageViewAwayTeam);
        imageViewHome = (ImageView) view.findViewById(R.id.imageViewHomeTeam);

        if (mNewGame.getmStatus().equals("OUVERT")){

            StopBet myTask = new StopBet();
            Timer myTimer = new Timer();

            Date date_game = new Date(mNewGame.getmOurDate());
            long proutprout = System.currentTimeMillis();
            myTimer.schedule(myTask, date_game);

            hour.setText(String.valueOf(mNewGame.getmHour()) + ":" + String.valueOf(mNewGame.getmMinute()));

        } else if (mNewGame.getmStatus().equals("EN COURS")) {

            hour.setText("MATCH EN COURS");


        } else {

            hour.setText("TERMINE");
            scoreHomeTeam.setText(String.valueOf(mNewGame.getmScoreHomeTeam()));
            scoreAwayTeam.setText(String.valueOf(mNewGame.getmScoreAwayTeam() ));
        }

        homeTeam.setText(String.valueOf(mNewGame.getmHomeTeam()));
        awayTeam.setText(String.valueOf(mNewGame.getmAwayTeam()));

        SwitchLogoModel.switchLogo(homeTeam, imageViewHome, awayTeam , imageViewAway);

    }

    private class StopBet extends TimerTask {

        private NewGame newGame;

        public void run () {


            mDatabaseref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newGame = dataSnapshot.getValue(NewGame.class);
                    newGame.setmStatus("EN COURS");
                    mDatabaseref.setValue(newGame);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });
        }

    }
}
