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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class HomeCompetition extends Fragment implements AdapterView.OnItemClickListener {

    private ListView playersList;
    private TextView test;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String uId, postKey;


    private Query playerListQuery;
    private DatabaseReference playersRef;


    public static HomeCompetition newInstance () {
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







        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uId = user.getUid();

        database = FirebaseDatabase.getInstance();


           //playersRef = database.getReference("Competitions").child(postkey);
        // playerListQuery = database.


        playersList = (ListView) view.findViewById(R.id.playersList);
        playersList.setOnItemClickListener(this);
























        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}