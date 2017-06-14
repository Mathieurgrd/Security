package com.example.mathieu.parissportifs;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by mathieu on 22/05/17.
 */

public class UserModel {


    private String userId;
    private String userName;
    private ArrayList<CompetitionModel> userCompetitions;
    private float userScorePerCompetition;
    private String favoriteTeam;
    private String email;

    private UserModel() {
    }


    public String getEmail() {
        return email;
    }

    public UserModel(String userId, String userName, @Nullable ArrayList<CompetitionModel> userCompetitions, float userScorePerCompetition, @Nullable String favoriteTeam, String email) {
        this.userId = userId;
        this.userName = userName;
        this.userCompetitions = userCompetitions;
        this.userScorePerCompetition = userScorePerCompetition;
        this.favoriteTeam = favoriteTeam;
        this.email = email;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserCompetitions(ArrayList<CompetitionModel> userCompetitions) {
        this.userCompetitions = userCompetitions;
    }

    public void setUserScorePerCompetition(float userScorePerCompetition) {
        this.userScorePerCompetition = userScorePerCompetition;
    }

    public void setFavoriteTeam(String favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
