package com.example.mathieu.parissportifs;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by apprenti on 31/05/17.
 */

public class NewGame {

    private String mHomeTeam;
    private String mAwayTeam;
    private int mScoreHomeTeam;
    private int mScoreAwayTeam;
    private Date mDate;
    private int mHour;
    private int mMinute;

    private NewGame () {
    }


    public NewGame (String homeTeam, String awayTeam, int scoreHomeTeam, int scoreAwayTeam, Date date, int hour, int minute){
        mHomeTeam = homeTeam;
        mAwayTeam = awayTeam;
        mScoreHomeTeam = scoreHomeTeam;
        mScoreAwayTeam = scoreAwayTeam;
        mDate = date;
        mHour = hour;
        mMinute = minute;

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
}
