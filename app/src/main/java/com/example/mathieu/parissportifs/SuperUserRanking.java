package com.example.mathieu.parissportifs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SuperUserRanking extends Fragment {

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

        return view;
    }
}
