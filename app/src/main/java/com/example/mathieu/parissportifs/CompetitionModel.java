package com.example.mathieu.parissportifs;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by mathieu on 22/05/17.
 */

public class CompetitionModel {


    String competitionName;
    String userAdmin;
    List<UserModel> userList;
    int ratingMatchScore;
    int ratingMatchResult;
    String competitionIdReedeemCode;

    private CompetitionModel() {}

    public CompetitionModel(String competitionName, String userAdmin, List<UserModel> userList, int ratingMatchScore, int ratingMatchResult,  @Nullable String competitionIdReedeemCode) {
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

    public List<UserModel> getUserList() {
        return userList;
    }

    public int getRatingMatchScore() {
        return ratingMatchScore;
    }

    public int getRatingMatchResult() {
        return ratingMatchResult;
    }

    public String getCompetitionIdReedeemCode() {
        return competitionIdReedeemCode;
    }






}
