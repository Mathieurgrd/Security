package com.example.mathieu.parissportifs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateOrJoinCompetition extends AppCompatActivity implements View.OnClickListener {

    private ListView mCompetitionListView;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Button createCompetition;
    private Button joinCompettion;
    private Spinner championshipSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_join_competition);

        findViewById(R.id.button_create_competition).setOnClickListener(this);
        findViewById(R.id.button_join_competition).setOnClickListener(this);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference("CompetitionList");

        CompetitionListAdapter mTripResultAdapter = new CompetitionListAdapter(mDatabase, this, R.layout.competitions_list_items ); // APPELLE L'ADAPTER

        mCompetitionListView.setAdapter(mTripResultAdapter); //FUSION LIST ET ADAPTER






        mCompetitionListView = (ListView) findViewById(R.id.CompetitionList);
        mCompetitionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(CreateOrJoinCompetition.this, "Coucou ca marche", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_create_competition) {
            startActivity(new Intent(CreateOrJoinCompetition.this, CreateCompetitionActivity.class));
        }

        if (i == R.id.button_join_competition) {


        }
    }
}
