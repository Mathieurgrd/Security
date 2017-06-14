package com.example.mathieu.parissportifs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class CreateOrJoinCompetition extends AppCompatActivity implements View.OnClickListener {

    private ListView mCompetitionListView;
    private Query mDatabaseCompetitionRef, AdapterQuery, mDatabaseUserRef;
    private FirebaseUser user;
    private Button createCompetition;
    private Button joinCompettion;
    private Spinner championshipSelector;
    private String pass, userId, competitionKey;
    private FirebaseDatabase database;
    private DatabaseReference competitionRef, myRef, finalPush;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private List<UserModel> userList;
    private ArrayList<CompetitionModel> competitionsList;
    private Button goModifyProfil;
    private String uId;
    private CompetitionListAdapter mCompetitionResultAdapter;
    private static final String ADMIN_USER = "H3KtahUU6nREMuaTpJyqoVoZcT02";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_join_competition);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uId = user.getUid();
        if (uId.equals(ADMIN_USER)) {
            startActivity(new Intent(CreateOrJoinCompetition.this, AdminGames.class));
            finish();
        }
        if (user == null) {
            startActivity(new Intent(CreateOrJoinCompetition.this, LoginActivity.class));
        }

        findViewById(R.id.button_create_competition).setOnClickListener(this);
        findViewById(R.id.button_join_competition).setOnClickListener(this);
        goModifyProfil = (Button) findViewById(R.id.goModifyProfil);
        goModifyProfil.setOnClickListener(this);
        final EditText input = new EditText(CreateOrJoinCompetition.this);




        database = FirebaseDatabase.getInstance();
        // CHeck if user is in competition ArrayList, for(datasnap) ;
        /**
        mDatabaseCompetitionRef = database.getReference("Competitions");
        mDatabaseCompetitionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int childCount = (int) dataSnapshot.getChildrenCount();
                for(int i = 0; i <= childCount; i++) {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                     CompetitionModel usersCompetitionforList = dataSnapshot.getValue(CompetitionModel.class);
                        competitionKey = usersCompetitionforList.getCompetitionIdReedeemCode();

                        AdapterQuery = mDatabaseCompetitionRef.orderByChild("Members :").equalTo(uId);





                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
         */



        mDatabaseCompetitionRef = database.getReference("Competitions");

        mDatabaseUserRef = database.getReference("users");

        mCompetitionListView = (ListView) findViewById(R.id.CompetitionList);

        mCompetitionResultAdapter = new CompetitionListAdapter(mDatabaseCompetitionRef, this,
                R.layout.competitions_list_items); // APPELLE L'ADAPTER

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

        if (i == R.id.goModifyProfil) {
            startActivity(new Intent(CreateOrJoinCompetition.this, ModifyProfile.class));
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
                            final Query competitionQuery = mDatabaseCompetitionRef;
                            // final Query userQuery = database.getReference("users/").child(userId);
//                            Query userQuery = mDatabaseUserRef.child(userId);


                            /**   userQuery.addValueEventListener(new ValueEventListener() {

                            @Override public void onDataChange(DataSnapshot dataSnapshot) {



                            }

                            @Override public void onCancelled(DatabaseError databaseError) {

                            }
                            });
                             */
                            pass = "";

                            competitionQuery.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    long allItems = dataSnapshot.getChildrenCount();
                                    int maxNum = (int) allItems;

                                    for (int i = 0; i <= maxNum; i++) {

                                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                            final CompetitionModel competitionToJoin = dataSnapshot
                                                    .getValue(CompetitionModel.class);
                                            pass = postSnapshot.getKey();

                                            if (competitionPassword.length() != 0) {
                                                if (pass.equals(competitionPassword)) {


                                                    competitionRef = database.getReference("Competitions");

                                                    DatabaseReference database = FirebaseDatabase
                                                            .getInstance().getReference();

                                                    myRef = database.child("users/" + uId);


                                                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                                            UserModel currentUser = dataSnapshot.getValue(UserModel.class);


                                                            finalPush = competitionRef.child(pass).child("Members :")
                                                                    .child(currentUser.getUserId());

                                                            finalPush.setValue(currentUser);

                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    });
























                                                    //  Query addUserQuery = competitionRef.child(pass);
                                                           // .orderByChild("competitionIdReedeemCode").equalTo(pass);


                                                   /** addUserQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                                            CompetitionModel competitionModel = dataSnapshot.getValue(CompetitionModel.class);

                                                            DatabaseReference database = FirebaseDatabase
                                                                    .getInstance().getReference();

                                                            myRef = database.child("users/" + uId);


                                                            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {

                                                                    UserModel currentUser = dataSnapshot.getValue(UserModel.class);


                                                                    finalPush = competitionRef.child(pass).child("Members :/")
                                                                            .child(currentUser.getUserId());

                                                                    finalPush.setValue(currentUser);

                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {

                                                                }
                                                            });
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    }); */













                                                    /**   competitionRef.child(pass)
                                                            .addChildEventListener(new ChildEventListener() {
                                                                @Override
                                                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                                                    DatabaseReference database = FirebaseDatabase
                                                                            .getInstance().getReference();

                                                                    myRef = database.child("users/" + uId);


                                                                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                                            UserModel currentUser = dataSnapshot.getValue(UserModel.class);
                                                                            finalPush = competitionRef.child(pass).child("Members :")
                                                                                    .child(currentUser.getUserId());

                                                                            finalPush.setValue(currentUser);
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(DatabaseError databaseError) {

                                                                        }
                                                                    });

                                                                }

                                                                @Override
                                                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                                                                }

                                                                @Override
                                                                public void onChildRemoved(DataSnapshot dataSnapshot) {

                                                                }

                                                                @Override
                                                                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {

                                                                }
                                                            }); */


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








