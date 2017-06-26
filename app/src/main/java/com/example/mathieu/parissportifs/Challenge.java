package com.example.mathieu.parissportifs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Challenge extends Fragment {

    private DatabaseReference mDatabase;
    private ListView mListViewRanking;
    private ChallengeAdapter mChallengeAdapter;
    private String mCompetitionId;

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
                R.layout.challenge_items); // APPELLE L'ADAPTER

        mListViewRanking.setAdapter(mChallengeAdapter);//FUSION LIST ET ADAPTER

        mChallengeAdapter.notifyDataSetChanged();

        return view;
    }

    public String focus(){
        return mCompetitionId;
    }
}