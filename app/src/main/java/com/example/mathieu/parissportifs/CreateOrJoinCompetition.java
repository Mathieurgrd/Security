package com.example.mathieu.parissportifs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CreateOrJoinCompetition extends AppCompatActivity implements View.OnClickListener {

    private ListView mCompetitionListView;
    private FirebaseDatabase database;
    private DatabaseReference mDatabaseCompetitionRef, mDatabaseUserRef;
    private FirebaseUser user;
    private Button createCompetition;
    private Button joinCompettion;
    private Spinner championshipSelector;
    private String pass, userId;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private List<UserModel> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_join_competition);

        findViewById(R.id.button_create_competition).setOnClickListener(this);
        findViewById(R.id.button_join_competition).setOnClickListener(this);
        mCompetitionListView = (ListView) findViewById(R.id.CompetitionList);
        final EditText input = new EditText(CreateOrJoinCompetition.this);

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(CreateOrJoinCompetition.this, LoginActivity.class));
                }else{
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    userId = user.getUid();
                }
            }
        };


        database = FirebaseDatabase.getInstance();
        mDatabaseCompetitionRef = database.getReference("Competitions");
        mDatabaseUserRef = database.getReference("users");

        mCompetitionListView = (ListView) findViewById(R.id.CompetitionList);

        CompetitionListAdapter mCompetitionResultAdapter = new CompetitionListAdapter(mDatabaseCompetitionRef, this, R.layout.competitions_list_items); // APPELLE L'ADAPTER

        mCompetitionListView.setAdapter(mCompetitionResultAdapter); //FUSION LIST ET ADAPTER

        mCompetitionResultAdapter.notifyDataSetChanged();


        mCompetitionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(CreateOrJoinCompetition.this, Navigation.class));


            }
        });
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_create_competition) {
            startActivity(new Intent(CreateOrJoinCompetition.this, CreateCompetitionActivity.class));
            CreateOrJoinCompetition.this.finish();
        }

        if (i == R.id.button_join_competition) {


            AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateOrJoinCompetition.this);
            alertDialog.setTitle("Join Competition");
            alertDialog.setMessage("Enter Password");
            final EditText input = new EditText(CreateOrJoinCompetition.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input);


            alertDialog.setPositiveButton("Validate",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {




                            final String competitionPassword = input.getText().toString();
                            Query competitionQuery = mDatabaseCompetitionRef;
                            Query userQuery = mDatabaseUserRef.child(userId);


                            userQuery.addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {



                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });








                            pass = "";

                            competitionQuery.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    long allItems = dataSnapshot.getChildrenCount();
                                    int maxNum = (int) allItems;

                                    for (int i = 0; i <= maxNum; i++) {

                                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                            CompetitionModel competitionToJoin = dataSnapshot.getValue(CompetitionModel.class);
                                            pass = postSnapshot.getKey();

                                            if (competitionPassword.length() != 0 ) {
                                                if (pass.equals(competitionPassword)) {
                                                    setTitle("Succesful I DID IT !");
                                                    userList = competitionToJoin.getUserList();
                                                    Toast.makeText(getApplicationContext(),
                                                            "Password Matched", Toast.LENGTH_SHORT).show();
                                                    return;
                                                } else {

                                                    Toast.makeText(getApplicationContext(),
                                                            "Wrong Password!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }





                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }
                    });

            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alertDialog.show();
        }
    }
}








