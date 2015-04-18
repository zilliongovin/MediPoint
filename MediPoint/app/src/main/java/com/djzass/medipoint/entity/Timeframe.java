package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Joshua on 25/3/2015.
 *
 * @author Joshua
 * @since 2015
 * @version 1.0
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

    public Timeframe(String inp){
        int s1,s2,e1,e2;
        s1 = Integer.parseInt(inp.split(" - ")[0].split(":")[0]);
        s2 = Integer.parseInt(inp.split(" - ")[0].split(":")[1]);
        e1 = Integer.parseInt(inp.split(" - ")[1].split(":")[0]);
        e2 = Integer.parseInt(inp.split(" - ")[1].split(":")[1]);
        this.startTime  = s1*2 + (s2/30);
        this.endTime    = e1*2 + (e2/30);
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

    public static String getString(int tim){
        if (tim % 2 == 0)
            return String.format("%02d:%02d", tim/2, 00);
        else
            return String.format("%02d:%02d", tim/2, 30);
    }

    public String getTimeLine(){
        return getString(startTime) + " - " + getString(endTime);
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
