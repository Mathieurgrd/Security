    package com.example.mathieu.parissportifs;

    import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
    import android.util.Log;
    import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.database.ChildEventListener;
    import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.MutableData;
    import com.google.firebase.database.Transaction;
    import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;

    import static com.example.mathieu.parissportifs.Constants.COMPET;
    import static com.example.mathieu.parissportifs.Constants.USER;

    public class CreateCompetitionActivity extends AppCompatActivity implements
            View.OnClickListener, AdapterView.OnItemSelectedListener {

        private static final String TAG = "CreateCompetitionActy";

        private FirebaseUser mUser;
        private FirebaseDatabase database;
        private DatabaseReference mUserRef;
        private DatabaseReference competitionRef;
        private Spinner championShipSelector;
        private ImageView frenchFlag;
        private List<String> championshipList;
        private EditText etnameCompetition;
        private DatabaseReference myRef, finalPush;
        private List<UserModel> userfornewCompetitionList;
        private int scaleVictory, scaleScore;
        private FirebaseAuth mAuth;
        private FirebaseAuth.AuthStateListener mAuthListener;

        private String competitionName, championHShipName, mGroupId, checkKey;
        private Intent intent;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_competition);


            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();
            database = FirebaseDatabase.getInstance();
            competitionRef = database.getReference(COMPET);
            mUserRef = database.getReference(USER).child(mUser.getUid());


            if (mAuth.getCurrentUser() != null) {
                Toast.makeText(CreateCompetitionActivity.this,
                        "Yay you're logged !", Toast.LENGTH_LONG).show();
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


                final String competitionName = etnameCompetition.getText().toString();

                if (competitionName.length() == 0) {
                    Toast.makeText(CreateCompetitionActivity.this, "You must enter a competition name",
                            Toast.LENGTH_SHORT).show();
                }

                // Write a message to the database



                String UserId = mUser.getUid().toString();


                final CompetitionModel userCompetition = new CompetitionModel(competitionName,
                        championHShipName, UserId, userfornewCompetitionList, null);


                final DatabaseReference pushedPostRf = competitionRef.push();
                pushedPostRf.setValue(userCompetition);
                mUserRef.runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        UserModel currentUser = mutableData.getValue(UserModel.class);
                        HashMap<String, Integer> newHash = currentUser.getUserScorePerCompetition();
                        if (newHash == null){
                            newHash = new HashMap<String, Integer>();
                        }
                        newHash.put(pushedPostRf.getKey(), 0);
                        currentUser.setUserScorePerCompetition(newHash);
                        mutableData.setValue(currentUser);
                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                        if (databaseError != null) {
                            Log.d(TAG, databaseError.getMessage());
                        }
                    }
                });



                // ----------------------------------------------


                competitionRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot child : dataSnapshot.getChildren()) {


                            mGroupId = child.getKey();
                            DatabaseReference checkChild = competitionRef.child(mGroupId);

                            checkChild.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    CompetitionModel competitionModel = dataSnapshot.getValue(CompetitionModel.class);
                                    if (competitionModel.getCompetitionName().equals(competitionName)){


                                        //MgroupId renvoie au child qui correspond a l'ID de la competition qui vient d'être crée par l'user
                                        userCompetition.setCompetitionIdReedeemCode(mGroupId);
                                        competitionRef.child(mGroupId).setValue(userCompetition);


                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });




                        }


                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                UserModel currentUser = dataSnapshot.getValue(UserModel.class);


                                finalPush = competitionRef.child(mGroupId).child("Members :/")
                                        .child(currentUser.getUserId());

                                finalPush.setValue(currentUser);
                                intent = new Intent(CreateCompetitionActivity.this,
                                        PickContactActivity.class);

                                intent.putExtra("oui", mGroupId);

                                //competitionRef.removeEventListener(this);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });




                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }



                });






                finish();


            }
            // Spinners selection methods
        }
    }


