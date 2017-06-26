package com.example.mathieu.parissportifs;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.HashMap;

/**
 * Created by mathieu on 15/06/17.
 */

public class PlayersListAdapter extends Firebaseadapter<UserModel> {

    private TextView playerName;
    private TextView playerRank;
    private TextView playerScore;
    private int scoreValue;
    private String CompetitionKey;
    DatabaseReference checkIfUserhasCompetition;
    private de.hdodenhof.circleimageview.CircleImageView playerPic;


    public PlayersListAdapter(Query ref, Activity activity, int layout) {

        super(ref, UserModel.class, layout, activity);

    }

    public PlayersListAdapter(Query ref, Activity activity, int layout, String CompetKey) {

        super(ref, UserModel.class, layout, activity);

        this.CompetitionKey = CompetKey;

    }


    @Override
    protected void populateView(View v, UserModel model, int position) {


        playerName = (TextView) v.findViewById(R.id.playerName);
        playerRank = (TextView) v.findViewById(R.id.playerRank);
        playerScore = (TextView) v.findViewById(R.id.playerScore);



        if (model.getUserScorePerCompetition() == null) {
            playerScore.setText(String.valueOf(0));
        }else {
            HashMap<String, Integer> playerScoreMap = model.getUserScorePerCompetition();
            playerScore.setText(playerScoreMap.get(CompetitionKey).toString());
        }

        playerName.setText(model.getUserName());




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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

       
        TextView tvScore = (TextView) view.findViewById(R.id.playerScore);





        return super.getView(i, view, viewGroup);


          }


}

