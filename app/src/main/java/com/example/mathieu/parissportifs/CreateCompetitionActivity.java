package com.example.mathieu.parissportifs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateCompetitionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private FirebaseDatabase database;
    private DatabaseReference competitionRef;
    private FirebaseDatabase competitionDatabase;
    private Spinner championShipSelector;
    private ImageView frenchFlag;
    private List<String> championshipList;
    private EditText etnameCompetition;
    private DatabaseReference myRef;
    private List<UserModel> userfornewCompetitionList;
    private RadioButton rButton1ptSE, rButton2ptSE, rButton3ptSE, rButton4ptSE, rButton1pt1N2,
            rButton2pt1N2, rButton3pt1N2, rButton4pt1N2;
    private int scaleVictory, scaleScore;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String competitionName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_competition);


        mAuth = FirebaseAuth.getInstance();


        FirebaseUser user = mAuth.getCurrentUser();

        if (mAuth.getCurrentUser() !=  null){
            Toast.makeText(CreateCompetitionActivity.this, "Yay you're logged !", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(CreateCompetitionActivity.this, "FAIL !", Toast.LENGTH_LONG).show();

        }


        rButton1ptSE = (RadioButton) findViewById(R.id.radioButton);
        rButton1ptSE.setOnCheckedChangeListener(this);

        rButton2ptSE = (RadioButton) findViewById(R.id.radioButton2);
        rButton2ptSE.setOnCheckedChangeListener(this);

        rButton3ptSE = (RadioButton) findViewById(R.id.radioButton3);
        rButton3ptSE.setOnCheckedChangeListener(this);

        rButton4ptSE = (RadioButton) findViewById(R.id.radioButton4);
        rButton4ptSE.setOnCheckedChangeListener(this);

        rButton1pt1N2 = (RadioButton) findViewById(R.id.radioButton5);
        rButton1pt1N2.setOnCheckedChangeListener(this);

        rButton2pt1N2 = (RadioButton) findViewById(R.id.radioButton6);
        rButton2pt1N2.setOnCheckedChangeListener(this);

        rButton3pt1N2 = (RadioButton) findViewById(R.id.radioButton7);
        rButton3pt1N2.setOnCheckedChangeListener(this);

        rButton4pt1N2 = (RadioButton) findViewById(R.id.radioButton8);
        rButton4pt1N2.setOnCheckedChangeListener(this);


        championShipSelector = (Spinner) findViewById(R.id.championship_spinner);
        championShipSelector.setOnItemSelectedListener(this);

        findViewById(R.id.button_validate_mycompetition).setOnClickListener(this);

         etnameCompetition = (EditText) findViewById(R.id.eTextNameYourCompetition);

        FirebaseAuth.getInstance().getCurrentUser().getUid();
        String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();



        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        myRef = database.child("users/" + UserId);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                UserModel userProfile = dataSnapshot.getValue(UserModel.class);


                //ArrayList To create the Competition Object
                List<UserModel> userfornewCompetitionList = new ArrayList<>();
                userfornewCompetitionList.add(userProfile);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });







        addItemOnChampionShipSelector();
    }

    public void addItemOnChampionShipSelector() {


        List<String> championshipList = new ArrayList<String>();
        championshipList.add("Select your Championship");
        championshipList.add("Ligue 1");
        championshipList.add("Barclays League");
        championshipList.add("Bundesliga");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, championshipList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        championShipSelector.setAdapter(dataAdapter);
    }

    public void onRadioButtonClicked(View view){
        int scaleVictory = 0;
        int scaleScore = 0;

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButton:
                if (checked){scaleVictory = 1;}

                    break;
            case R.id.radioButton2:
                if (checked){scaleVictory = 2;}
                    // Ninjas rule
                    break;
            case R.id.radioButton3:
                if (checked){scaleVictory = 3;}
                    // Pirates are the best
                    break;
            case R.id.radioButton4:
                if (checked){scaleVictory = 4;}
                    // Pirates are the best
                    break;
            case R.id.radioButton5:
                if (checked){scaleScore = 1;}
                    // Pirates are the best
                    break;
            case R.id.radioButton6:
                if (checked){scaleScore = 2;}
                    // Pirates are the best
                    break;
            case R.id.radioButton7:
                if (checked){scaleScore = 3;}
                    // Pirates are the best
                    break;
            case R.id.radioButton8:
                if (checked){scaleScore = 4;}
                    // Pirates are the best
                    break;
        }


    }

    @Override
    public void onClick(View v) {
        int i =v.getId();

        if (i == R.id.button_validate_mycompetition){



            String competitionName = etnameCompetition.getText().toString();

            // Write a message to the database

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference mRef = database.getReference("users");
            database = FirebaseDatabase.getInstance(); //APPELLE LA BASE DE DONNEES
            competitionRef = database.getReference("Competitions");



            /** FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Competitions");*/
            FirebaseAuth.getInstance().getCurrentUser().getUid();
            String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

            competitionDatabase = FirebaseDatabase.getInstance(); //APPELLE LA BASE DE DONNEES
            competitionRef = competitionDatabase.getReference("Competitions");

            CompetitionModel userCompetition = new CompetitionModel(competitionName, UserId, userfornewCompetitionList,
                    scaleScore, scaleVictory, null);
            competitionRef.push().setValue(userCompetition);

            startActivity(new Intent(CreateCompetitionActivity.this, CreateOrJoinCompetition.class));
            CreateCompetitionActivity.this.finish();
        }
    }


    // Spinners selection methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Button createButton = (Button) findViewById(R.id.button_create_competition);
        createButton.setEnabled(false);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.radioButton) {

                rButton2ptSE.setChecked(false);
                rButton3ptSE.setChecked(false);
                rButton4ptSE.setChecked(false);
            }
            if (buttonView.getId() == R.id.radioButton2) {
                rButton1ptSE.setChecked(false);
                rButton3ptSE.setChecked(false);
                rButton4ptSE.setChecked(false);
            }
            if (buttonView.getId() == R.id.radioButton3) {
                rButton1ptSE.setChecked(false);
                rButton2ptSE.setChecked(false);
                rButton4ptSE.setChecked(false);
            }
            if (buttonView.getId() == R.id.radioButton4) {
                rButton2ptSE.setChecked(false);
                rButton1ptSE.setChecked(false);
                rButton3ptSE.setChecked(false);
            }
            if (buttonView.getId() == R.id.radioButton5) {
                rButton2pt1N2.setChecked(false);
                rButton3pt1N2.setChecked(false);
                rButton4pt1N2.setChecked(false);

            }
            if (buttonView.getId() == R.id.radioButton6) {
                rButton1pt1N2.setChecked(false);
                rButton3pt1N2.setChecked(false);
                rButton4pt1N2.setChecked(false);
            }
            if (buttonView.getId() == R.id.radioButton7) {
                rButton2pt1N2.setChecked(false);
                rButton4pt1N2.setChecked(false);
                rButton1pt1N2.setChecked(false);
            }
            if (buttonView.getId() == R.id.radioButton8) {
                rButton1pt1N2.setChecked(false);
                rButton2pt1N2.setChecked(false);
                rButton3pt1N2.setChecked(false);
            }
        }

    }
}
