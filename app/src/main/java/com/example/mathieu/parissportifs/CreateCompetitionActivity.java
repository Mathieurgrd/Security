    package com.example.mathieu.parissportifs;

    import android.app.Activity;
    import android.content.BroadcastReceiver;
    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.telephony.SmsManager;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.EditText;
    import android.widget.ImageView;
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

    public class CreateCompetitionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

        private FirebaseDatabase database;
        private DatabaseReference competitionRef;
        private FirebaseDatabase competitionDatabase;
        private Spinner championShipSelector;
        private ImageView frenchFlag;
        private List<String> championshipList;
        private EditText etnameCompetition;
        private DatabaseReference myRef;
        private List<UserModel> userfornewCompetitionList;
        private int scaleVictory, scaleScore;
        private FirebaseAuth mAuth;
        private FirebaseAuth.AuthStateListener mAuthListener;
        private String competitionName, championHShipName;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_competition);


            mAuth = FirebaseAuth.getInstance();


            FirebaseUser user = mAuth.getCurrentUser();

            if (mAuth.getCurrentUser() != null) {
                Toast.makeText(CreateCompetitionActivity.this, "Yay you're logged !", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(CreateCompetitionActivity.this, "FAIL !", Toast.LENGTH_LONG).show();

            }


            championShipSelector = (Spinner) findViewById(R.id.championship_spinner);
            championShipSelector.setOnItemSelectedListener(this);

            findViewById(R.id.button_validate_mycompetition).setOnClickListener(this);

            etnameCompetition = (EditText) findViewById(R.id.eTextNameYourCompetition);

            FirebaseAuth.getInstance().getCurrentUser();
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

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show();

            championHShipName = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


        private BroadcastReceiver receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String message = null;

                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        message = "Message sent!";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        message = "Error. Message not sent.";
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        message = "Error: No service.";
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        message = "Error: Null PDU.";
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        message = "Error: Radio off.";
                        break;
                }


                Toast.makeText(CreateCompetitionActivity.this, message, Toast.LENGTH_LONG).show();
            }
        };



        @Override
        public void onClick(View v) {
            int i = v.getId();

            if (i == R.id.button_validate_mycompetition) {


                String competitionName = etnameCompetition.getText().toString();

                if (competitionName.length() == 0) {
                    Toast.makeText(CreateCompetitionActivity.this, "You must enter a competition name", Toast.LENGTH_SHORT).show();
                }

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

                CompetitionModel userCompetition = new CompetitionModel(competitionName, championHShipName, UserId, userfornewCompetitionList,
                        scaleScore, scaleVictory, null);

                String mGroupId = competitionRef.push().getKey();
                competitionRef.child(mGroupId).setValue(new CompetitionModel(competitionName, championHShipName, UserId, userfornewCompetitionList,
                        scaleScore, scaleVictory, mGroupId));
                competitionRef.push().setValue(userCompetition);

                Intent intent = new Intent(CreateCompetitionActivity.this, PickContactActivity.class);
                intent.putExtra("oui", mGroupId);




                    startActivity(intent);


                    finish();
                }
            }


            // Spinners selection methods


        }


