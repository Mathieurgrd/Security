package com.example.mathieu.parissportifs;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;

public class NewGameActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextView hour;
    private TextView dateView;
    private TextView choiceMatchWeek;
    private Calendar dateCalendar;
    private Calendar timeCalendar;
    private DateFormat formatDateTime;
    private Spinner teamHome;
    private Spinner teamAway;
    private String homeTeam;
    private String awayTeam;
    private String winner = "O";
    private Button saveGame;
    private int scoreHome = -1;
    private int scoreAway = -1;
    private DatabaseReference mDatabase;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private String date_time;
    private Date date_time_object;
    private List<String> ligue1List;
    private MaterialNumberPicker numberPicker;
    private int matchWeek;
    private String uploadId;
    private int date;
    private int month;
    private int years;
    private Date ourDate;
    private String reportDate;
    private Date mydate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_activity);

        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_GAMES);

        hour = (TextView) findViewById(R.id.textViewChangeHour);
        dateView = (TextView) findViewById(R.id.textViewDate);
        choiceMatchWeek = (TextView) findViewById(R.id.textViewChoiceJourney);
        choiceMatchWeek.setOnClickListener(this);
        hour.setOnClickListener(this);
        dateView.setOnClickListener(this);

        teamHome = (Spinner) findViewById(R.id.spinnerHome);
        teamHome.setOnItemSelectedListener(this);
        teamAway = (Spinner) findViewById(R.id.spinnerAway);
        teamAway.setOnItemSelectedListener(this);
        saveGame = (Button) findViewById(R.id.buttonSaveGame);
        saveGame.setOnClickListener(this);

        numberPicker = new MaterialNumberPicker.Builder(this)
                .minValue(1)
                .maxValue(38)
                .defaultValue(19)
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.TRANSPARENT)
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();


        formatDateTime = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
        dateCalendar = Calendar.getInstance();
        timeCalendar = Calendar.getInstance();

        ligue1List = new ArrayList<String>();
        ligue1List.add("Select your Favorite Team !");
        ligue1List.add("Angers SCO");
        ligue1List.add("AC Bastia");
        ligue1List.add("Girondins Bordeaux");
        ligue1List.add("Caen");
        ligue1List.add("Dijon FC");
        ligue1List.add("EA Guingamp");
        ligue1List.add("Lorient");
        ligue1List.add("Lille");
        ligue1List.add("Lyon");
        ligue1List.add("Marseille");
        ligue1List.add("Monaco");
        ligue1List.add("Metz");
        ligue1List.add("Montpellier");
        ligue1List.add("Nancy");
        ligue1List.add("Nantes");
        ligue1List.add("OGC Nice");
        ligue1List.add("PSG");
        ligue1List.add("Rennes");
        ligue1List.add("ASSE");
        ligue1List.add("TFC");

        updateTextLabelDate();
        addItemTeamAwaySelector();
        addItemTeamHomeSelector();
    }

    private void updateDate(){
        // Get Current Date
        dateCalendar = Calendar.getInstance();
        mYear = dateCalendar.get(Calendar.YEAR);
        mMonth = dateCalendar.get(Calendar.MONTH);
        mDay = dateCalendar.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                        date = view.getDayOfMonth();
                        month = view.getMonth();

                        years = view.getYear();
                        String displayMonth = String.valueOf(view.getMonth()+1);

                        date_time = date + "/" + displayMonth + "/" + years;
                        date_time_object = new Date(years, view.getMonth(), date);
                        dateView.setText(date_time);
                        //*************Call Time Picker Here ********************
                        updateTime();
                     }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void updateTime(){
        // Get Current Time
        timeCalendar = Calendar.getInstance();
        mHour = timeCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = timeCalendar.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                 new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        hour.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    private void updateTextLabelDate(){


        dateView.setText(formatDateTime.format(dateCalendar.getTime()));
    }



    public void addItemTeamAwaySelector() {
        ArrayAdapter<String> dataAdapterAway = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ligue1List);
        dataAdapterAway.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamAway.setAdapter(dataAdapterAway);
    }

    public void addItemTeamHomeSelector(){
        ArrayAdapter<String> dataAdapterHome = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ligue1List);
        dataAdapterHome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamHome.setAdapter(dataAdapterHome);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinnerHome:
                awayTeam = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinnerAway:
                homeTeam = parent.getItemAtPosition(position).toString();
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void updateGame(){

        ourDate = new Date (years-1900, month, date, mHour,mMinute);
        long prout = ourDate.getTime();
        mydate = new Date (prout);

        DateFormat df = new SimpleDateFormat("yyMMdd");
        reportDate = df.format(date_time_object);

        uploadId = mDatabase.child(reportDate).push().getKey();
        NewGame newGame = new NewGame(uploadId, homeTeam, awayTeam, scoreHome, scoreAway, date_time_object, mHour, mMinute, matchWeek,mydate,reportDate,"OUVERT",winner);

        mDatabase.child(reportDate).child(uploadId).setValue(newGame);
    }

    public void openDialogJourney (){
        new AlertDialog.Builder(this)
                .setTitle("Choisir la journée")
                .setView(numberPicker)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        matchWeek = numberPicker.getValue();
                        choiceMatchWeek.setText(Integer.toString(matchWeek));

                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {

        if(v==hour){
             updateTime();
        }

        if(v==dateView){
            updateDate();
        }

        if(v==saveGame){

            if (homeTeam.equals(awayTeam)){
                Toast.makeText(NewGameActivity.this,"Attention tu as selectionné la même équipe",Toast.LENGTH_LONG).show();
                return;
            }
            updateGame();

        }
        if(v==choiceMatchWeek){
            openDialogJourney();
        }
    }

}




