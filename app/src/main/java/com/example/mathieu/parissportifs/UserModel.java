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
    private HashMap<String, Integer> userScorePerCompetition;
    private String favoriteTeam;
    private String email;
    private HashMap<String, BetGameModel> usersBets;




    private UserModel() {
    }

    public UserModel(String userId, String userName, @Nullable HashMap userScorePerCompetition, String favoriteTeam, String email, @Nullable HashMap<String, BetGameModel> usersBets) {
        this.userId = userId;
        this.userName = userName;
        this.userScorePerCompetition = userScorePerCompetition;
        this.favoriteTeam = favoriteTeam;
        this.email = email;
        this.usersBets = usersBets;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashMap<String, Integer> getUserScorePerCompetition() {
        return userScorePerCompetition;
    }

    public void setUserScorePerCompetition(HashMap<String, Integer> userScorePerCompetition) {
        this.userScorePerCompetition = userScorePerCompetition;
    }

    public String getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(String favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    public String getEmail() {
        return email;
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
