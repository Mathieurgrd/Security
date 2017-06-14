package com.example.mathieu.parissportifs;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by mathieu on 22/05/17.
 */

public class CompetitionModel {


   public String competitionName;
   public String chamionshipName;
   public String userAdmin;
   public List<UserModel> userList;
   public int ratingMatchScore;
   public int ratingMatchResult;
   public String competitionIdReedeemCode;

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

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setChamionshipName(String chamionshipName) {
        this.chamionshipName = chamionshipName;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public void setUserList(List<UserModel> userList) {
        this.userList = userList;
    }

    public void setRatingMatchScore(int ratingMatchScore) {
        this.ratingMatchScore = ratingMatchScore;
    }

    public void setRatingMatchResult(int ratingMatchResult) {
        this.ratingMatchResult = ratingMatchResult;
    }

    public int getRatingMatchResult() {
        return ratingMatchResult;
    }

    public String getCompetitionIdReedeemCode() {
        return competitionIdReedeemCode;
    }

    public void setCompetitionIdReedeemCode(String competitionIdReedeemCode) {
        this.competitionIdReedeemCode = competitionIdReedeemCode;
    }






}
