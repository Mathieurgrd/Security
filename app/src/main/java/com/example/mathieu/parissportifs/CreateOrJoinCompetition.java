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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CreateOrJoinCompetition extends AppCompatActivity implements View.OnClickListener {

    private ListView mCompetitionListView;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Button createCompetition;
    private Button joinCompettion;
    private Spinner championshipSelector;
    private String pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_join_competition);

        findViewById(R.id.button_create_competition).setOnClickListener(this);
        findViewById(R.id.button_join_competition).setOnClickListener(this);
        mCompetitionListView = (ListView) findViewById(R.id.CompetitionList);
        final EditText input = new EditText(CreateOrJoinCompetition.this);


        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("Competitions");

        mCompetitionListView = (ListView) findViewById(R.id.CompetitionList);

        CompetitionListAdapter mCompetitionResultAdapter = new CompetitionListAdapter(mDatabase, this, R.layout.competitions_list_items); // APPELLE L'ADAPTER

        mCompetitionListView.setAdapter(mCompetitionResultAdapter); //FUSION LIST ET ADAPTER

        mCompetitionResultAdapter.notifyDataSetChanged();


        mCompetitionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(CreateOrJoinCompetition.this, Navigation.class));


            }
        });
    }


    /** public boolean getCompetitionsList(String password) {

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


               ArrayList Competitionlist = new ArrayList<String>();


                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                    Competitionlist.add(String.valueOf(dsp.getValue())); //add result into array list


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    } */

    public boolean isGoodCode(){

        final EditText input = new EditText(CreateOrJoinCompetition.this);

        final String competitionPassword = input.getText().toString();
        Query competitionQuery = mDatabase.child("competitionIdReedeemCode").equalTo(competitionPassword);
        pass = "";

        competitionQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pass = dataSnapshot.getKey();

                if (!pass.equals(competitionPassword)){
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                return;

            }
        });
     return true;
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
            alertDialog.setTitle("PASSWORD");
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
                            Query competitionQuery = mDatabase.child("competitionIdReedeemCode").equalTo(competitionPassword);
                             pass = "";

                            competitionQuery.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                         // To change !!!!!!!!!! en dessous
                                    pass = dataSnapshot.getKey();

                                    if (pass.equals(competitionPassword)){

                                        for (int i = 0; i < 5 ; i ++) {
                                            Toast.makeText(CreateOrJoinCompetition.this, "Yeah", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            if (competitionPassword.compareTo("") == 0) {
                                if (pass.equals(competitionPassword)) {
                                    Toast.makeText(getApplicationContext(),
                                            "Password Matched", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Wrong Password!", Toast.LENGTH_SHORT).show();
                                }
                            }
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








