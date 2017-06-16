package com.example.mathieu.parissportifs;

import java.util.Date;

/**
 * Created by apprenti on 14/06/17.
 */

public class BetGameModel {

    private String mGameId;
    private String mHomeTeam;
    private String mAwayTeam;
    private int mHomeScore;
    private int mAwayScore;
    private int mMatchWeek;
    private String mWinner;
    private String mDateGame;
    private Integer mBetResult;

    public BetGameModel(String mGameId, String mHomeTeam, String mAwayTeam, int mHomeScore, int mAwayScore, int mMatchWeek, String mWinner, String mDateGame) {
        this.mGameId = mGameId;
        this.mHomeTeam = mHomeTeam;
        this.mAwayTeam = mAwayTeam;
        this.mHomeScore = mHomeScore;
        this.mAwayScore = mAwayScore;
        this.mMatchWeek = mMatchWeek;
        this.mWinner = mWinner;
        this.mDateGame = mDateGame;
    }
    public  BetGameModel(){

    }

    public String getmGameId() {
        return mGameId;
    }

    public void setmGameId(String mGameId) {
        this.mGameId = mGameId;
    }

    public String getmHomeTeam() {
        return mHomeTeam;
    }

    public void setmHomeTeam(String mHomeTeam) {
        this.mHomeTeam = mHomeTeam;
    }

    public String getmAwayTeam() {
        return mAwayTeam;
    }

    public void setmAwayTeam(String mAwayTeam) {
        this.mAwayTeam = mAwayTeam;
    }

    public int getmHomeScore() {
        return mHomeScore;
    }

    public void setmHomeScore(int mHomeScore) {
        this.mHomeScore = mHomeScore;
    }

    public int getmAwayScore() {
        return mAwayScore;
    }

    public void setmAwayScore(int mAwayScore) {
        this.mAwayScore = mAwayScore;
    }

    public int getmMatchWeek() {
        return mMatchWeek;
    }

    public void setmMatchWeek(int mMatchWeek) {
        this.mMatchWeek = mMatchWeek;
    }

    public String getmWinner() {
        return mWinner;
    }

    public void setmWinner(String mWinner) {
        this.mWinner = mWinner;
    }

    public String getmDateGame() {
        return mDateGame;
    }

    public void setmDateGame(String mDateGame) {
        this.mDateGame = mDateGame;
    }

    public Integer getmBetResult() {
        return mBetResult;
    }

    public void setmBetResult(Integer mBetResult) {
        this.mBetResult = mBetResult;
    }
}
