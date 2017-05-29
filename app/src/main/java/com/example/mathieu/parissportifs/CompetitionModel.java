package com.example.mathieu.parissportifs;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by mathieu on 22/05/17.
 */

public class CompetitionModel {


    String competitionName;
    String chamionshipName;
    String userAdmin;
    List<UserModel> userList;
    int ratingMatchScore;
    int ratingMatchResult;
    String competitionIdReedeemCode;

    private CompetitionModel() {}

    public CompetitionModel(String competitionName, String chamionshipName, String userAdmin, List<UserModel> userList, int ratingMatchScore, int ratingMatchResult,  @Nullable String competitionIdReedeemCode) {
        this.competitionName = competitionName;
        this.chamionshipName = chamionshipName;
        this.userAdmin = userAdmin;
        this.userList = userList;
        this.ratingMatchScore = 3;
        this.ratingMatchResult = 1;
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

    public String getChamionshipName() {
        return chamionshipName;
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
