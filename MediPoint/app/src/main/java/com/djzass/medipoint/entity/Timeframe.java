package com.djzass.medipoint.entity;
/**
 * Created by Joshua on 25/3/2015.
 *
 * This class represents time of day (hour/min) in form of integer (0..47)
 * 0 represents 0:00, 47 represents 23:30
 * Used to simplify things since the app calculates time in 30-min increments
 */

public class Timeframe {
    private int startTime;
    private int endTime;

    public Timeframe(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setStartTime (int startTime){
        this.startTime = startTime;
    }

    public void setEndTime (int endTime){
        this.endTime = endTime;
    }

    public int getStartTime(){
        return startTime;
    }

    public int getEndTime(){
        return endTime;
    }

    public String getStartTimeString(){
        if (startTime % 2 == 0)
            return String.format("%02d:%02d", startTime/2, 00);
        else
            return String.format("%02d:%02d", startTime/2, 30);
    }

    public String getEndTimeString(){
        if (endTime % 2 == 0)
            return String.format("%02d:%02d", endTime/2, 00);
        else
            return String.format("%02d:%02d", endTime/2, 30);
    }

    public String getTimeLine(){
        return getStartTimeString() + " - " + getEndTimeString();
    }
}
