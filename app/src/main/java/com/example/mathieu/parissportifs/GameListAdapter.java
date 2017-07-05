package com.example.mathieu.parissportifs;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by apprenti on 03/06/17.
 */

public class GameListAdapter extends Firebaseadapter <NewGame> {

    TextView homeTeam;
    TextView scoreHomeTeam, betScoreHome, betScoreAway;
    TextView scoreAwayTeam;
    TextView awayTeam;
    TextView hour, textviewTag;
    ImageView imageViewAway;
    ImageView imageViewHome;
    int ScoreBetAway, ScoreBetHome;
    String competitionid, userid, gameId;
    Handler newHandler;
    public DatabaseReference mDatabaseref;
    private NewGame newGame;

    public GameListAdapter(Query ref, Activity activity, int layout) {
        super(ref, NewGame.class, layout, activity);

    }


    public GameListAdapter(Query ref, Activity activity, int layout, String CompetitionId, String UserId) {
        super(ref, NewGame.class, layout, activity);

        competitionid= CompetitionId;
        userid = UserId;
    }



    @Override
    protected void populateView(View view, NewGame mNewGame, int position) {


        mDatabaseref = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_GAMES).child(mNewGame.getmReportDate()).child(mNewGame.getmIdGame());

        gameId = mNewGame.getmIdGame();
        textviewTag = (TextView) view.findViewById(R.id.textViewTag);
        textviewTag.setText(gameId);
        textviewTag.setVisibility(View.INVISIBLE);
        hour = (TextView) view.findViewById(R.id.textViewHour);
        homeTeam = (TextView) view.findViewById(R.id.textViewHomeTeam);
        awayTeam = (TextView) view.findViewById(R.id.textViewAwayTeam);
        scoreHomeTeam = (TextView) view.findViewById(R.id.textViewScoreHome);
        scoreAwayTeam = (TextView) view.findViewById(R.id.textViewScoreAway);
        betScoreHome = (TextView) view.findViewById(R.id.textViewbetScoreHome);
        betScoreAway = (TextView) view.findViewById(R.id.textViewbetScoreAway);
        imageViewAway = (ImageView) view.findViewById(R.id.imageViewAwayTeam);
        imageViewHome = (ImageView) view.findViewById(R.id.imageViewHomeTeam);





        if (mNewGame.getmStatus().equals("OUVERT")){

            if (mNewGame.getmMinute() == 0){
                hour.setText(String.valueOf(mNewGame.getmHour()) + ":0" + String.valueOf(mNewGame.getmMinute()));
            } else {
                hour.setText(String.valueOf(mNewGame.getmHour()) + ":" + String.valueOf(mNewGame.getmMinute()));
            }

        } else if (mNewGame.getmStatus().equals("EN COURS")) {

            hour.setText("MATCH EN COURS");


        } else {

            hour.setText("TERMINE");
            scoreHomeTeam.setText(String.valueOf(mNewGame.getmScoreHomeTeam()));
            scoreAwayTeam.setText(String.valueOf(mNewGame.getmScoreAwayTeam() ));
        }

        homeTeam.setText(String.valueOf(mNewGame.getmHomeTeam()));
        awayTeam.setText(String.valueOf(mNewGame.getmAwayTeam()));

       /** newHandler = new Handler();
        newHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                GetBet(competitionid, userid, gameId);

            }
        },1500);*/




        SwitchLogoModel.switchLogo(homeTeam, imageViewHome, awayTeam , imageViewAway);

    }


   /** public void GetBet(final String GetCompetitionId, final String GetUserId, final String GameId){

        DatabaseReference mUserRef = FirebaseDatabase.getInstance().getReference(Constants.COMPET).child(GetCompetitionId).child("membersMap")
                .child(GetUserId).child("usersBets").child(GameId);

        mUserRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BetGameModel userBetOnCurrentMatch = dataSnapshot.getValue(BetGameModel.class);

                if (userBetOnCurrentMatch != null && userBetOnCurrentMatch.getmGameId().equals(GameId)) {

                    ScoreBetAway = userBetOnCurrentMatch.getmAwayScore();
                    ScoreBetHome = userBetOnCurrentMatch.getmHomeScore();

                }else{
                    ScoreBetAway = 0 ;
                    ScoreBetHome = 0;

                }

                        betScoreAway.setText(String.valueOf(ScoreBetAway));
                        betScoreHome.setText(String.valueOf(ScoreBetHome));




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }*/
}
