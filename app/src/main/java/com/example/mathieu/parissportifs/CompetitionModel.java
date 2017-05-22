package com.example.mathieu.parissportifs;

import java.util.ArrayList;

/**
 * Created by mathieu on 22/05/17.
 */

public class CompetitionModel {


    String competitionName;
    String userAdmin;
    ArrayList<UserModel> userList;
    int ratingMatchScore;
    int ratingMatchResult;
    float competitionIdReedeemCode;

    private CompetitionModel() {}

    public CompetitionModel(String competitionName, String userAdmin, ArrayList<UserModel> userList, int ratingMatchScore, int ratingMatchResult, float competitionIdReedeemCode) {
        this.competitionName = competitionName;
        this.userAdmin = userAdmin;
        this.userList = userList;
        this.ratingMatchScore = ratingMatchScore;
        this.ratingMatchResult = ratingMatchResult;
        this.competitionIdReedeemCode = competitionIdReedeemCode;
    }


    public String getCompetitionName() {
        return competitionName;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public ArrayList<UserModel> getUserList() {
        return userList;
    }

    public int getRatingMatchScore() {
        return ratingMatchScore;
    }

    public int getRatingMatchResult() {
        return ratingMatchResult;
    }

    public float getCompetitionIdReedeemCode() {
        return competitionIdReedeemCode;
    }






}
