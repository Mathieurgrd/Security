package com.example.mathieu.parissportifs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;

public class CreateCompetitionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private FirebaseDatabase database;
    private DatabaseReference competitionRef;
    private FirebaseDatabase competitionDatabase;
    private Spinner championShipSelector;
    private ImageView frenchFlag;
    private List<String> championshipList;
    private EditText nameYourCompetition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_competition);

        championShipSelector = (Spinner) findViewById(R.id.championship_spinner);
        championShipSelector.setOnItemSelectedListener(this);
        findViewById(R.id.button_create_competition).setOnClickListener(this);
        EditText nameYourCompetition = (EditText) findViewById(R.id.eTextNameYourCompetition);

        FirebaseAuth.getInstance().getCurrentUser().getUid();
        String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        //ArrayList To create the Competition Object
        List<UserModel> userfornewCompetitionList = new ArrayList<UserModel>();
        userfornewCompetitionList.add();


        List<String> championshipList = new ArrayList<String>();
        championshipList.add("Select your Championship");
        championshipList.add("Ligue 1");
        championshipList.add("Barclays League");
        championshipList.add("Bundesliga");


        addItemOnChampionShipSelector();
    }

    public void addItemOnChampionShipSelector() {



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, championshipList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        championShipSelector.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        int i =v.getId();

        if (i == R.id.button_create_competition){

            String competitionName = nameYourCompetition.getText().toString();

            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Competitions");
            FirebaseAuth.getInstance().getCurrentUser().getUid();
            String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

            competitionDatabase = FirebaseDatabase.getInstance(); //APPELLE LA BASE DE DONNEES
            myRef = competitionDatabase.getReference("Itineraries");

            CompetitionModel userCompetition = new CompetitionModel(competitionName, UserId, userfo)
            myRef.push().setValue();

            startActivity(new Intent(CreateCompetitionActivity.this, CreateOrJoinCompetition.class));
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
}
