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
   public int competitionScore;
   public String competitionIdReedeemCode;

    private CompetitionModel() {}

    public CompetitionModel(String competitionName, String chamionshipName, String userAdmin, List<UserModel> userList,  @Nullable String competitionIdReedeemCode) {
        this.competitionName = competitionName;
        this.chamionshipName = chamionshipName;
        this.userAdmin = userAdmin;
        this.userList = userList;
        this.competitionIdReedeemCode = competitionIdReedeemCode;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getChamionshipName() {
        return chamionshipName;
    }

    public void setChamionshipName(String chamionshipName) {
        this.chamionshipName = chamionshipName;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public List<UserModel> getUserList() {
        return userList;
    }

    public void setUserList(List<UserModel> userList) {
        this.userList = userList;
    }

    public int getCompetitionScore() {
        return competitionScore;
    }

    public void setCompetitionScore(int competitionScore) {
        this.competitionScore = competitionScore;
    }

    public String getCompetitionIdReedeemCode() {
        return competitionIdReedeemCode;
    }

    public void setCompetitionIdReedeemCode(String competitionIdReedeemCode) {
        this.competitionIdReedeemCode = competitionIdReedeemCode;
    }
}
