package com.example.mathieu.parissportifs;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.Query;

/**
 * Created by apprenti on 03/06/17.
 */

public class GameListAdapter extends Firebaseadapter <NewGame> {

    TextView homeTeam;
    TextView awayTeam;
    TextView hour;




    public GameListAdapter(Query ref, Activity activity, int layout) {
        super(ref, NewGame.class, layout, activity);

    }


    @Override
    protected void populateView(View view, NewGame mNewGame) {

        homeTeam = (TextView) view.findViewById(R.id.textViewHomeTeam);

        homeTeam.setText(String.valueOf(mNewGame.getmHomeTeam()));

        awayTeam = (TextView) view.findViewById(R.id.textViewAwayTeam);

        awayTeam.setText(String.valueOf(mNewGame.getmAwayTeam()));

        hour = (TextView) view.findViewById(R.id.textViewHour);

        hour.setText(String.valueOf(mNewGame.getmHour()) + ":" + String.valueOf(mNewGame.getmMinute()));



    }
}
