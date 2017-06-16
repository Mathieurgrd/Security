package com.example.mathieu.parissportifs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class HomeCompetition extends Fragment implements  AdapterView.OnItemClickListener {

    private ListView playersList;
    private TextView test, competitionNametv;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String uId, postKey, competitionTitle, competitionKey;
    private PlayersListAdapter aPlayersListAdapter;


    private Query playerListQuery;
    private DatabaseReference playersRef;


    public static HomeCompetition newInstance() {
        HomeCompetition fragment = new HomeCompetition();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home_competition, container, false);







         test = (TextView) view.findViewById(R.id.test);

        String strtext = ((Navigation)getActivity()).getKey();

        test.setText(strtext);




        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uId = user.getUid();

        playersList = (ListView) view.findViewById(R.id.playersList);
        competitionNametv = (TextView) view.findViewById(R.id.competitionName);


        database = FirebaseDatabase.getInstance();


        playersRef = database.getReference("Competitions")
                .child("-KmkI1GOZgPhUK3GwY_7").child("Members :/");
        playerListQuery = playersRef;

        Query competitionQuery = database.getReference("Competitions")
                .child("-KmkI1GOZgPhUK3GwY_7");
        competitionQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CompetitionModel competitionModel = dataSnapshot.getValue(CompetitionModel.class);
                competitionTitle = competitionModel.getCompetitionName();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        competitionNametv.setText(competitionTitle);
        aPlayersListAdapter = new PlayersListAdapter(playerListQuery.
                orderByChild("userScorePerCompetition").limitToFirst(25),
                HomeCompetition.this.getActivity(), R.layout.players_items_list);

        playersList.setAdapter(aPlayersListAdapter);


        playersList.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }







    }
