package com.example.mathieu.parissportifs;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

/**
 * Created by apprenti on 25/06/17.
 */

public class ChallengeAdapter extends Firebaseadapter<CompetitionModel> {

    public TextView textViewPosition;
    public TextView textViewPoints;
    public TextView textViewNameCompetition;
    public Challenge mChallenge;




    public ChallengeAdapter(Query ref, Activity activity, int layout) {
        super(ref, CompetitionModel.class, layout, activity);


    }


    @Override
    protected void populateView(View view, CompetitionModel competition, int position) {

        textViewPosition = (TextView) view.findViewById(R.id.textViewPosition);
        textViewPoints = (TextView) view.findViewById(R.id.textViewPoints);
        textViewNameCompetition = (TextView) view.findViewById(R.id.textViewNameCompetition);


        textViewPosition.setText(String.valueOf(getItemId(position+1)));
        textViewNameCompetition.setText(competition.getCompetitionName());
        textViewPoints.setText(String.valueOf(competition.getCompetitionScore()));





    }






}
