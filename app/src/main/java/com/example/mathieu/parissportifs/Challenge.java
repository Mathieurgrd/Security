package com.example.mathieu.parissportifs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Challenge extends Fragment {

    private DatabaseReference mDatabase;
    private ListView mListViewRanking;
    private ChallengeAdapter mChallengeAdapter;
    private String mCompetitionId;
    private int prout = 0;

    public static Challenge newInstance (String competitionId) {
        Bundle bundle = new Bundle();
        bundle.putString(CreateOrJoinCompetition.COMPETITION_ID, competitionId);
        Challenge fragment = new Challenge();
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCompetitionId = getArguments().getString(CreateOrJoinCompetition.COMPETITION_ID);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_challenge, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.COMPET);
        Query query = mDatabase.orderByChild("competitionScore");
        mListViewRanking = (ListView) view.findViewById(R.id.listViewRanking);

        mChallengeAdapter = new ChallengeAdapter(query, getActivity(),
                R.layout.challenge_items, mCompetitionId);

        mListViewRanking.setAdapter(mChallengeAdapter);

        mChallengeAdapter.notifyDataSetChanged();

        return view;
    }

}