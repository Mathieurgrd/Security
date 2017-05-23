package com.example.mathieu.parissportifs;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by mathieu on 22/05/17.
 */

public class UserModel {



    String userId;
    String userName;
    ArrayList<CompetitionModel> userCompetitions;
   float userScorePerCompetition;

    private UserModel() {
    }


    public UserModel(String userId, String userName, @Nullable ArrayList<CompetitionModel> userCompetitions, float userScorePerCompetition) {
        this.userId = userId;
        this.userName = userName;
        this.userCompetitions = userCompetitions;
        this.userScorePerCompetition = userScorePerCompetition;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<CompetitionModel> getUserCompetitions() {
        return userCompetitions;
    }

    public float getUserScorePerCompetition() {
        return userScorePerCompetition;
    }
}
