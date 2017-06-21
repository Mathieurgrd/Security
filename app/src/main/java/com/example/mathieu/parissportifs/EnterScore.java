package com.example.mathieu.parissportifs;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import static com.example.mathieu.parissportifs.Constants.WINNER_AWAY;
import static com.example.mathieu.parissportifs.Constants.WINNER_HOME;
import static com.example.mathieu.parissportifs.Constants.WINNER_NULL;

public class EnterScore extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "enterScore";

    private TextView date;
    private TextView hour;
    private TextView homeTeam;
    private TextView awayTeam;
    private TextView homeScore;
    private TextView awayScore;
    private Button buttonUpload;
    private MaterialNumberPicker numberPickerHome;
    private MaterialNumberPicker numberPickerAway;
    private NewGame newGame;
    private DatabaseReference mDatabaseGame;
    private DatabaseReference mDatabaseUser;
    private DatabaseReference mDatabaseCompet;
    private String date_firebase;
    private DateFormat dff;
    private String uploadId;
    private int compareScoreHome;
    private int compareScoreAway;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_score);

        mDatabaseGame = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_GAMES);
        mDatabaseUser = FirebaseDatabase.getInstance().getReference(Constants.USER);
        mDatabaseCompet = FirebaseDatabase.getInstance().getReference(Constants.COMPET);


                date = (TextView) findViewById(R.id.textViewDate);
        hour = (TextView) findViewById(R.id.textViewHour);
        homeTeam = (TextView) findViewById(R.id.textViewHomeTeam);
        awayTeam = (TextView) findViewById(R.id.textViewAwayTeam);
        homeScore = (TextView) findViewById(R.id.textViewHomeScore);
        homeScore.setOnClickListener(this);
        awayScore = (TextView) findViewById(R.id.textViewAwayScore);
        awayScore.setOnClickListener(this);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonUpload.setOnClickListener(this);

        numberPickerHome = new MaterialNumberPicker.Builder(this)
                .minValue(0)
                .maxValue(10)
                .defaultValue(0)
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.TRANSPARENT)
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();

        numberPickerAway = new MaterialNumberPicker.Builder(this)
                .minValue(0)
                .maxValue(10)
                .defaultValue(0)
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.TRANSPARENT)
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();

        Intent i = getIntent();
        newGame = (NewGame) i.getSerializableExtra("newGame");

        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String reportDate = df.format(newGame.getmDate());

        String hourGame = String.valueOf(newGame.getmHour())+" : "+String.valueOf(newGame.getmMinute());

        date.setText(reportDate);
        hour.setText(hourGame);
        homeTeam.setText(newGame.getmHomeTeam());
        awayTeam.setText(newGame.getmAwayTeam());

        if(newGame.getmScoreHomeTeam() == -1){
            homeScore.setText("/");
            awayScore.setText("/");
        } else {
            homeScore.setText(String.valueOf(newGame.getmScoreHomeTeam()));
            awayScore.setText(String.valueOf(newGame.getmScoreAwayTeam()));
        }


    }

    private void changeScoreHomeTeam(){

        new AlertDialog.Builder(this)
                .setTitle("Entrer le score de " + newGame.getmHomeTeam())
                .setView(numberPickerHome)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        homeScore.setText(Integer.toString(numberPickerHome.getValue()));
                        newGame.setmScoreHomeTeam(numberPickerHome.getValue());

                    }
                }).show();

    }

    private void changeScoreAwayTeam(){

        new AlertDialog.Builder(this)
                .setTitle("Entrer le score de " + newGame.getmAwayTeam())
                .setView(numberPickerAway)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        awayScore.setText(Integer.toString(numberPickerAway.getValue()));
                        newGame.setmScoreAwayTeam(numberPickerAway.getValue());


                    }
                })
                .show();

    }
    private void checkWinnerGame(){

        compareScoreAway = newGame.getmScoreAwayTeam();
        compareScoreHome = newGame.getmScoreHomeTeam();
        if (compareScoreHome > compareScoreAway){
            newGame.setmWinner(WINNER_HOME);
        } else if (compareScoreAway > compareScoreHome){
            newGame.setmWinner(WINNER_AWAY);
        } else
         {
            newGame.setmWinner(WINNER_NULL);
        }
        uploadId = newGame.getmIdGame();
        dff = new SimpleDateFormat("yyMMdd");
        date_firebase = dff.format(newGame.getmDate());
        newGame.setmStatus("TERMINE");
        mDatabaseGame.child(date_firebase).child(uploadId).setValue(newGame);

    }

    public void onClick (View v) {
        if (v == homeScore) {

            changeScoreHomeTeam();

        }

        if (v == awayScore) {

            changeScoreAwayTeam();

        }

        if (v == buttonUpload) {

            checkWinnerGame();
            checkUsersBet();

        }
    }

    private void checkUsersBet(){
        mDatabaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()){
                    setBetScore(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setBetScore(DataSnapshot user){
        final DatabaseReference currentUserRef = mDatabaseUser.child(user.getKey());
        currentUserRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                if (mutableData.getValue() == null){
                    return Transaction.success(mutableData);
                }
                UserModel currentUser = mutableData.getValue(UserModel.class);
                HashMap<String, BetGameModel> betsList = currentUser.getUsersBets();
                if (betsList.containsKey(uploadId)){
                    BetGameModel currentBet = betsList.get(uploadId);
                    int score = 0;
                    if (currentBet.getmHomeScore() == compareScoreHome && currentBet.getmAwayScore() == compareScoreAway) {
                        score = 3;
                    } else if(currentBet.getmWinner().equals(newGame.getmWinner())){
                        score = 1;
                    }
                    currentBet.setmBetResult(score);
                    mutableData.setValue(currentUser);
                    setUserScore(currentUserRef,currentBet.getmCompetitionId() , score);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });
    }

    private void setUserScore(DatabaseReference currentUserRef, final String competiitionID, final int score){
        currentUserRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                if (mutableData.getValue() == null){
                    return Transaction.success(mutableData);
                }
                UserModel currentUser = mutableData.getValue(UserModel.class);
                HashMap<String, Integer> competitionMap = currentUser.getUserScorePerCompetition();
                int newScore = competitionMap.get(competiitionID) + score;
                competitionMap.put(competiitionID, newScore);
                currentUser.setUserScorePerCompetition(competitionMap);
                mutableData.setValue(currentUser);
                updateCompetition(competiitionID, score, currentUser);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });
    }

    private void updateCompetition(final String competitionId, final int score, final UserModel currentUser){
       mDatabaseCompet.child(competitionId).runTransaction(new Transaction.Handler() {
           @Override
           public Transaction.Result doTransaction(MutableData mutableData) {
               if (mutableData.getValue() == null){
                   return Transaction.success(mutableData);
               }

               // Getting the competition
               CompetitionModel currentCompetition = mutableData.getValue(CompetitionModel.class);

               // Update score of the compet
               int newScore = currentCompetition.getCompetitionScore() + score;
               currentCompetition.setCompetitionScore(newScore);

               //Update members in the competition
               HashMap<String, UserModel> membersMap = currentCompetition.getMembersMap();
               membersMap.put(currentUser.getUserId(), currentUser);
               currentCompetition.setMembersMap(membersMap);

               //send the new data
               mutableData.setValue(currentCompetition);
               return Transaction.success(mutableData);
           }

           @Override
           public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

           }
       });

    }

}
