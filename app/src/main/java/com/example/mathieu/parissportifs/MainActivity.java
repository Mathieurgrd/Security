package com.example.mathieu.parissportifs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.mathieu.parissportifs.Constants.TEAM;
import static com.example.mathieu.parissportifs.Constants.USER;

public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {



    private EditText editTextModifyPseudo;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private Spinner favoriteTeamSelector;
    private String equipefavorite, favoriteTeam;
    private ProgressDialog progressDialog;
    private FirebaseUser user;
    private Button buttonGo;


    private static String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(USER).child(user.getUid());
        isOnFirebase();
        // Spinner
        favoriteTeamSelector = (Spinner) findViewById(R.id.spinner_favorite_team);
        favoriteTeamSelector.setOnItemSelectedListener(this);
        // Nickname
        editTextModifyPseudo = (EditText) findViewById(R.id.editTextModifyPseudo);
        // Button
        buttonGo = (Button) findViewById(R.id.buttonGo);
        progressDialog = new ProgressDialog(this);
        //pseudo = user.getDisplayName();
        addItemFavoriteTeamSelector();


        email= user.getEmail();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {

                    UserModel userActuel = dataSnapshot.getValue(UserModel.class);
                    editTextModifyPseudo.setText(userActuel.getUserName());
                }


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionItent();
            }
        });







    }


    public void changeProfil() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newPseudo = editTextModifyPseudo.getText().toString().trim();

        //Uri newPhoto = userImg.

        //Update Pseudo & Photo
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(newPseudo)
                .build();

        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                }
            }
        });
    }

    public void addItemFavoriteTeamSelector() {


        List<String> ligue1List = new ArrayList<String>();
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

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ligue1List);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        favoriteTeamSelector.setAdapter(dataAdapter);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
        favoriteTeam = parent.getItemAtPosition(position).toString();


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


            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
    };


    public void conditionItent() {

        if (editTextModifyPseudo.getText().length() == 0 || favoriteTeam.equals("Select your Favorite Team !")) {
            Toast.makeText(MainActivity.this, favoriteTeam, Toast.LENGTH_LONG).show();
            return;
        }
        else{



            /** FirebaseDatabase database = FirebaseDatabase.getInstance();
             DatabaseReference myRef = database.getReference("Competitions");*/

            String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

            String userName = editTextModifyPseudo.getText().toString();
            UserModel user = new UserModel(UserId, userName, null, 0, favoriteTeam, email);
            mDatabase.setValue(user);

            Intent intent = new Intent(MainActivity.this, CreateOrJoinCompetition.class);
            startActivity(intent);
            finish();
        }
    }

    void isOnFirebase(){
        mDatabase.child(TEAM).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    Intent intent = new Intent(MainActivity.this, CreateOrJoinCompetition.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}









