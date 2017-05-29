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
    String favoriteTeam;

    private UserModel() {
    }


    public UserModel(String userId, String userName, @Nullable ArrayList<CompetitionModel> userCompetitions, float userScorePerCompetition, @Nullable String favoriteTeam) {
        this.userId = userId;
        this.userName = userName;
        this.userCompetitions = userCompetitions;
        this.userScorePerCompetition = userScorePerCompetition;
        this.favoriteTeam = favoriteTeam;
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

    public String getFavoriteTeam() {
        return favoriteTeam;
    }

    public float getUserScorePerCompetition() {

        return userScorePerCompetition;
    }
}
