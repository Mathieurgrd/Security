package com.example.mathieu.parissportifs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.HashMap;

import biz.borealis.numberpicker.NumberPicker;
import biz.borealis.numberpicker.OnValueChangeListener;

import static com.example.mathieu.parissportifs.Constants.DATABASE_PATH_GAMES;
import static com.example.mathieu.parissportifs.Constants.USER;

public class BetGame extends AppCompatActivity implements View.OnClickListener {

    private NewGame newGame;
    private TextView homeTeam;
    private TextView awayTeam;
    private TextView hour;
    private Button buttonSaveBet;
    private NumberPicker numberPickerHome;
    private NumberPicker numberPickerAway;
    private BetGameModel betGameModel;
    private int mScoreHome;
    private int mScoreAway;
    private String mWinner;
    private DatabaseReference mDatabaseUser;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_game);

        Intent i = getIntent();
        newGame = (NewGame) i.getSerializableExtra("newGame");

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseUser = FirebaseDatabase.getInstance()
                .getReference(USER)
                .child(mUser.getUid());



        homeTeam = (TextView) findViewById(R.id.textViewHomeTeam);
        awayTeam = (TextView) findViewById(R.id.textViewAwayTeam);
        hour = (TextView) findViewById(R.id.textViewHour);
        buttonSaveBet = (Button) findViewById(R.id.buttonSaveBet);
        buttonSaveBet.setOnClickListener(this);

        homeTeam.setText(newGame.getmHomeTeam());
        awayTeam.setText(newGame.getmAwayTeam());
        hour.setText(String.valueOf(newGame.getmHour())+ " : "+String.valueOf(newGame.getmMinute()));



        numberPickerHome = (NumberPicker) findViewById(R.id.numberPickerHome);
        numberPickerHome.setOnValueChangeListener(new OnValueChangeListener() {
            @Override
            public void onValueChanged(int newValue) {

                mScoreHome = newValue;

            }
        });
        numberPickerAway= (NumberPicker) findViewById(R.id.numberPickerAway);
        numberPickerAway.setOnValueChangeListener(new OnValueChangeListener() {
            @Override
            public void onValueChanged(int newValue) {

                mScoreAway = newValue;
            }
        });
    }

    private void checkWinnerBet (){

        if(mScoreAway < mScoreHome){
            mWinner = "HOME";
        } else if (mScoreAway > mScoreHome){
            mWinner = "AWAY";
        } else {
            mWinner = "NULL";
        }

    }

    public void onClick (View view) {

        if (view == buttonSaveBet){
            checkWinnerBet();
            betGameModel = new BetGameModel(
                    newGame.getmIdGame(),
                    newGame.getmHomeTeam(),
                    newGame.getmAwayTeam(),
                    mScoreHome,
                    mScoreAway,
                    newGame.getmMatchWeek(),
                    mWinner,
                    newGame.getmReportDate()
            );
            mDatabaseUser.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {
                    UserModel currentUser = mutableData.getValue(UserModel.class);
                    HashMap<String, BetGameModel> newBetList = currentUser.getUsersBets();
                    if(newBetList == null){
                        newBetList = new HashMap<String, BetGameModel>();
                    }
                    newBetList.put(newGame.getmIdGame(), betGameModel);
                    currentUser.setUsersBets(newBetList);
                    mutableData.setValue(currentUser);
                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                }
            });
        }

    }
}
