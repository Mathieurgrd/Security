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
    public TextView textViewTag;
    public Challenge mChallenge;
    private String CompetitionKey;
    private int Count;

    public String competitionId;






    public ChallengeAdapter(Query ref, Activity activity, int layout, String competitionId) {
        super(ref, CompetitionModel.class, layout, activity);

        this.competitionId = competitionId;


    }


    @Override
    protected void populateView(View view, CompetitionModel competition, int position) {


        textViewPosition = (TextView) view.findViewById(R.id.textViewPosition);
        textViewPoints = (TextView) view.findViewById(R.id.textViewPlayerRank);
        textViewNameCompetition = (TextView) view.findViewById(R.id.textViewNameCompetition);
        textViewTag = (TextView) view.findViewById(R.id.textViewTag);

        textViewPosition.setText(String.valueOf(getItemId(position+1)));
        textViewTag.setText(competition.getCompetitionIdReedeemCode());
        textViewTag.setVisibility(View.INVISIBLE);

        textViewNameCompetition.setText(competition.getCompetitionName());
        textViewPoints.setText(String.valueOf(competition.getCompetitionScore()));









    }








}
