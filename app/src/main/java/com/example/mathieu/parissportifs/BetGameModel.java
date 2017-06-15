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

    public BetGameModel(String gameId, String homeTeam, String awayTeam, int homeScore, int awayScore, int matchWeek, String winner){
        mGameId = gameId;
        mHomeTeam = homeTeam;
        mAwayTeam = awayTeam;
        mHomeScore = homeScore;
        mAwayScore = awayScore;
        mMatchWeek = matchWeek;
        mWinner = winner;
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
}
