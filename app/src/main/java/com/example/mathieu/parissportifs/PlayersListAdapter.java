package com.example.mathieu.parissportifs;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

/**
 * Created by mathieu on 15/06/17.
 */

public class PlayersListAdapter extends Firebaseadapter<UserModel> {

    private TextView playerName;
    private TextView playerRank;
    private TextView playerScore;
    private de.hdodenhof.circleimageview.CircleImageView playerPic;



    public PlayersListAdapter(Query ref, Activity activity, int layout) {
        super(ref, UserModel.class, layout, activity);

    }


    @Override
    protected void populateView(View v, UserModel model) {

        playerName = (TextView) v.findViewById(R.id.playerName);
        playerRank = (TextView) v.findViewById(R.id.playerRank);
        playerScore = (TextView) v.findViewById(R.id.playerScore);
        //playerPic = (de.hdodenhof.circleimageview.CircleImageView) v.findViewById(R.id.playerpic);

        playerName.setText(model.getUserName());
        playerScore.setText(String.valueOf(model.getUserScorePerCompetition()));
        playerRank.setText("Makumé");

    }
}
