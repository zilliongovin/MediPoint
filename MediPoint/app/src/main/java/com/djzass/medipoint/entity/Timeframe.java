package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Joshua on 25/3/2015.
 *
 * This class represents time of day (hour/min) in form of integer (0..47)
 * 0 represents 0:00, 47 represents 23:30
 * Used to simplify things since the app calculates time in 30-min increments
 */

public class Timeframe implements Parcelable{
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

    public static int getHour(int time) {
        return time/2;
    }

    public static int getMinute(int time) {
        return 30*(time%2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel time, int flags) {
        time.writeInt(this.startTime);
        time.writeInt(this.endTime);
    }

    public Timeframe(Parcel in){
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Timeframe> CREATOR
            = new Parcelable.Creator<Timeframe>() {
        public Timeframe createFromParcel(Parcel in) {
            return new Timeframe(in);
        }

        public Timeframe[] newArray(int size) {
            return new Timeframe[size];
        }
    };

    public void readFromParcel(Parcel intime) {
        this.startTime = intime.readInt();
        this.endTime = intime.readInt();
    }

}
