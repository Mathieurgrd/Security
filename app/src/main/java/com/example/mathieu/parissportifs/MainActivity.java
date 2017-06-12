package com.example.mathieu.parissportifs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.facebook.Profile;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText editTextModifyPseudo;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private FirebaseDatabase userDatabase;
    private Spinner favoriteTeamSelector;
    private String equipefavorite, favoriteTeam;
    private ProgressDialog progressDialog;
    private String userName;
    private FirebaseUser user;

    private static String email;
    private static final String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        // Spinner
        favoriteTeamSelector = (Spinner) findViewById(R.id.spinner_favorite_team);
        favoriteTeamSelector.setOnItemSelectedListener(this);
        // Nickname
        editTextModifyPseudo = (EditText) findViewById(R.id.editTextModifyPseudo);
        // Button
        findViewById(R.id.buttonGo).setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        userName = editTextModifyPseudo.getText().toString().trim();
        //pseudo = user.getDisplayName();
        addItemFavoriteTeamSelector();


        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // User is signed in
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    String uid = user.getUid();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]
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

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.buttonGo) {
            userName = editTextModifyPseudo.getText().toString();
            if (editTextModifyPseudo.length() == 0) {
                Toast.makeText(MainActivity.this, "You must enter an Username", Toast.LENGTH_SHORT).show();
            }



            Query userInformation = userDatabase.getReference("users/").child(user.getUid());

            userInformation.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        UserModel userActuel = dataSnapshot.getValue(UserModel.class);
                        email = userActuel.getEmail();

                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

                    /** FirebaseDatabase database = FirebaseDatabase.getInstance();
                     DatabaseReference myRef = database.getReference("Competitions");*/
                    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
            this.userDatabase = FirebaseDatabase.getInstance(); //APPELLE LA BASE DE DONNEES
            DatabaseReference myRef = this.userDatabase.getReference("users/");
            UserModel user = new UserModel(UserId, userName, null, 0, favoriteTeam, email);
            myRef.child(UserId).setValue(user);

            Intent intent = new Intent(MainActivity.this, CreateOrJoinCompetition.class);
            startActivity(intent);
            finish();
        }

        }
    }

