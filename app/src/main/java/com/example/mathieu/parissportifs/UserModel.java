package com.example.mathieu.parissportifs;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mathieu on 22/05/17.
 */

public class UserModel {


    private String userId;
    private String userName;
    private int userScorePerCompetition;
    private ArrayList<String> userCompetitions;
    private String favoriteTeam;
    private String email;
    private HashMap<String, BetGameModel> usersBets;

    private UserModel() {
    }

    public UserModel(String userId, String userName, @Nullable  ArrayList<String> userCompetitions, int userScorePerCompetition, String favoriteTeam, String email, @Nullable HashMap<String, BetGameModel> usersBets) {
        this.userId = userId;
        this.userName = userName;
        this.userCompetitions = userCompetitions;
        this.userScorePerCompetition = userScorePerCompetition;
        this.favoriteTeam = favoriteTeam;
        this.email = email;
        this.usersBets = usersBets;
    }

    public String getEmail() {
        return email;
    }


    public String getUserId() {

        return userId;
    }

    public String getUserName() {

        return userName;
    }

    public ArrayList<String> getUserCompetitions() {
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

    public void setUserCompetitions(ArrayList<String> userCompetitions) {
        this.userCompetitions = userCompetitions;
    }

    public void setUserScorePerCompetition(int userScorePerCompetition) {
        this.userScorePerCompetition = userScorePerCompetition;
    }

    public void setFavoriteTeam(String favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, BetGameModel> getUsersBets() {
        return usersBets;
    }

    public void setUsersBets(HashMap<String, BetGameModel> usersBets) {
        this.usersBets = usersBets;
    }
}
