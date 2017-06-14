package com.example.mathieu.parissportifs;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by apprenti on 31/05/17.
 */

public class NewGame implements Serializable {

    private String mHomeTeam;
    private String mAwayTeam;
    private int mScoreHomeTeam;
    private int mScoreAwayTeam;
    private Date mDate;
    private int mHour;
    private int mMinute;
    private int mMatchWeek;
    private String mIdGame;
    private Date mOurDate;
    private String mReportDate;
    private String mStatus;
    private String mWinner;

    private NewGame () {
    }

    public NewGame (String idGame, String homeTeam, String awayTeam, int scoreHomeTeam, int scoreAwayTeam, Date date, int hour, int minute, int matchWeek, Date ourDate, String reportDate, String status,String winner){
        mHomeTeam = homeTeam;
        mAwayTeam = awayTeam;
        mScoreHomeTeam = scoreHomeTeam;
        mScoreAwayTeam = scoreAwayTeam;
        mDate = date;
        mHour = hour;
        mMinute = minute;
        mMatchWeek =  matchWeek;
        mIdGame = idGame;
        mOurDate = ourDate;
        mReportDate = reportDate;
        mStatus = status;
        mWinner = winner;


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

    public int getmScoreHomeTeam() {
        return mScoreHomeTeam;
    }

    public void setmScoreHomeTeam(int mScoreHomeTeam) {
        this.mScoreHomeTeam = mScoreHomeTeam;
    }

    public int getmScoreAwayTeam() {
        return mScoreAwayTeam;
    }

    public void setmScoreAwayTeam(int mScoreAwayTeam) {
        this.mScoreAwayTeam = mScoreAwayTeam;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public int getmHour() {
        return mHour;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }
    public int getmMinute() {
        return mMinute;
    }

    public void setmMinute(int mMinute) {
        this.mMinute = mMinute;
    }
    public int getmMatchWeek() {
        return mMatchWeek;
    }

    public void setmMatchWeek(int mMatchWeek) {
        this.mMatchWeek = mMatchWeek;
    }

    public String getmIdGame() {
        return mIdGame;
    }

    public void setmIdGame(String mIdGame) {
        this.mIdGame = mIdGame;
    }

    public Date getmOurDate() {
        return mOurDate;
    }

    public void setmOurDate(Date mOurDate) {
        this.mOurDate = mOurDate;
    }

    public String getmReportDate() {
        return mReportDate;
    }

    public void setmReportDate(String mReportDate) {
        this.mReportDate = mReportDate;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmWinner() {
        return mWinner;
    }

    public void setmWinner(String mWinner) {
        this.mWinner = mWinner;
    }
}
