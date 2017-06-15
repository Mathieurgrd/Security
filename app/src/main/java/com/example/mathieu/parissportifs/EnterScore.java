package com.example.mathieu.parissportifs;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;

public class EnterScore extends AppCompatActivity implements View.OnClickListener {

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
    private DatabaseReference mDatabase;
    private String date_firebase;
    private String winnerHome = "HOME";
    private String winnerAway = "AWAY";
    private String winnerNull= "NULL";
    private DateFormat dff;
    private String uploadId;
    private int compareScoreHome;
    private int compareScoreAway;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_score);

        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_GAMES);

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
                })
                .show();

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
            newGame.setmWinner(winnerHome);
        } else if (compareScoreAway > compareScoreHome){
            newGame.setmWinner(winnerAway);
        } else
         {
            newGame.setmWinner(winnerNull);
        }
        uploadId = newGame.getmIdGame();
        dff = new SimpleDateFormat("yyMMdd");
        date_firebase = dff.format(newGame.getmDate());
        newGame.setmStatus("TERMINE");
        mDatabase.child(date_firebase).child(uploadId).setValue(newGame);





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

        }
    }


}
