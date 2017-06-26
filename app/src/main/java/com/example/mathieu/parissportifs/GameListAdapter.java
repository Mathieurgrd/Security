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
    public DatabaseReference mDatabaseref;


    public GameListAdapter(Query ref, Activity activity, int layout) {
        super(ref, NewGame.class, layout, activity);

    }


    @Override
    protected void populateView(View view, NewGame mNewGame, int position) {

        mDatabaseref = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_GAMES).child(mNewGame.getmReportDate()).child(mNewGame.getmIdGame());

        hour = (TextView) view.findViewById(R.id.textViewHour);
        homeTeam = (TextView) view.findViewById(R.id.textViewHomeTeam);
        awayTeam = (TextView) view.findViewById(R.id.textViewAwayTeam);
        scoreHomeTeam = (TextView) view.findViewById(R.id.textViewScoreHome);
        scoreAwayTeam = (TextView) view.findViewById(R.id.textViewScoreAway);


        if (mNewGame.getmStatus().equals("OUVERT")){

            StopBet myTask = new StopBet();
            Timer myTimer = new Timer();

            myTimer.schedule(myTask, mNewGame.getmOurDate());

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
    }

    private class StopBet extends TimerTask {

        private NewGame newGame;

        public void run () {

            ValueEventListener gameListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    newGame = dataSnapshot.getValue(NewGame.class);
                    newGame.setmStatus("EN COURS");
                    mDatabaseref.setValue(newGame);

                    // ...
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w("Erreur", "loadPost:onCancelled", databaseError.toException());
                    // ...
                }
            };
             mDatabaseref.addValueEventListener(gameListener);




        }

    }
}
