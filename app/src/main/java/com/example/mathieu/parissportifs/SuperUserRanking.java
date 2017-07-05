package com.example.mathieu.parissportifs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SuperUserRanking extends Fragment {


    private DatabaseReference mDatabase;
    private ListView mListViewRankingSuperUser;
    private SuperUserRankingAdapter mSuperUserRankingAdapter;

    public static SuperUserRanking newInstance () {
        SuperUserRanking fragment = new SuperUserRanking();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_super_user_ranking, container, false);




        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.COMPET);
        Query query = mDatabase.orderByChild("competitionScore");
        mListViewRankingSuperUser = (ListView) view.findViewById(R.id.listViewRankingSuperUser);


        mSuperUserRankingAdapter = new SuperUserRankingAdapter(query, getActivity(),
                R.layout.ranking_all_items); // APPELLE L'ADAPTER

        mListViewRankingSuperUser.setAdapter(mSuperUserRankingAdapter); //FUSION LIST ET ADAPTER

        mSuperUserRankingAdapter.notifyDataSetChanged();


        return view;
    }


}
