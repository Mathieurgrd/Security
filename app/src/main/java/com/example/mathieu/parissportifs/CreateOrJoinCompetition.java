package com.example.mathieu.parissportifs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateOrJoinCompetition extends AppCompatActivity {

    private ListView mCompetitionListView;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_join_competition);

        mDatabase = FirebaseDatabase.getInstance().getReference("Itineraries"); // APPELLE LA BASE DE DONNEES

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
}
