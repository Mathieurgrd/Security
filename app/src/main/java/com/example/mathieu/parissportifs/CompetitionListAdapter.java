package com.example.mathieu.parissportifs;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.Query;


public class CompetitionListAdapter extends Firebaseadapter<CompetitionModel> {

    TextView CompetitionName;
    TextView ChampionshipName;
    ImageView CaptainBrassard;




    public CompetitionListAdapter(Query ref, Activity activity, int layout) {
        super(ref, CompetitionModel.class, layout, activity);

    }


    @Override
    protected void populateView(View view, CompetitionModel mCompetition) {

        CompetitionName = (TextView) view.findViewById(R.id.eTextCompetitionName);

        CompetitionName.setText(mCompetition.getCompetitionName());

        ChampionshipName = (TextView) view.findViewById(R.id.eTextChampionnatName);

        ChampionshipName.setText(String.valueOf(mCompetition.getChamionshipName()));



    }
}




