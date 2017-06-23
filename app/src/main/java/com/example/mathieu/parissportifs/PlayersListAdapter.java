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

        /*if (model.getUserScorePerCompetition() < 5) {
        if (model.getUserScorePerCompetition() < 5) {
            playerRank.setText("Polisseur de banc");
        }else if (model.getUserScorePerCompetition() < 10){
            playerRank.setText("Presseur d'orange");
        }else if (model.getUserScorePerCompetition() < 15){
            playerRank.setText("Porteur de gourde");
        }else if (model.getUserScorePerCompetition() < 20){
            playerRank.setText("Ventre mou");
        }else if (model.getUserScorePerCompetition() < 25){
            playerRank.setText("Jeune talent");
        }else if (model.getUserScorePerCompetition() < 30){
            playerRank.setText("Zlatan");
        }*/

        }

    }
}
