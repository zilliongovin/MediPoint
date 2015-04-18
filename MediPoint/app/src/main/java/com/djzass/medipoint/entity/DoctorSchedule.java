package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Deka on 26/3/2015.
 *
 * @author Joshua
 * @since 2015
 * @version 1.0
 */

public class DoctorSchedule implements Parcelable {
    private int scheduleId;
    private int doctorId;
    private int clinicId;
    private String day;
    private Timeframe timeframe;

    public DoctorSchedule(){

    }

    public DoctorSchedule(int doctorId, int clinicId, String day) {
        this.doctorId = doctorId;
        this.clinicId = clinicId;
        this.day = day;
    }

    public DoctorSchedule(int doctorId, int clinicId, String day, Timeframe timeframe) {
        this.doctorId = doctorId;
        this.clinicId = clinicId;
        this.day = day;
        this.timeframe = timeframe;
    }

    public String print(){
        String temp = "";
        temp+= scheduleId + " ";
        temp+= doctorId + " ";
        temp+= clinicId + " ";
        temp+= day + " ";
        temp+= timeframe.getStartTime() + "-" + timeframe.getEndTime();
        return temp;
    }

    public int getId() {
        return scheduleId;
    }

    public void setId(int id) {
        this.scheduleId = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Timeframe getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(Timeframe timeframe) {
        this.timeframe = timeframe;
    }

    public DoctorSchedule(Parcel in){
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel desc, int flags) {
        desc.writeInt(this.doctorId);
        desc.writeInt(this.scheduleId);
        desc.writeInt(this.clinicId);
        desc.writeString(this.day);
        /*private Timeframe timeframe;*/
    }

    public static final Parcelable.Creator<DoctorSchedule> CREATOR
            = new Parcelable.Creator<DoctorSchedule>() {
        public DoctorSchedule createFromParcel(Parcel in) {
            return new DoctorSchedule(in);
        }

        public DoctorSchedule[] newArray(int size) {
            return new DoctorSchedule[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.doctorId = in.readInt();
        this.scheduleId = in.readInt();
        this.clinicId = in.readInt();
        this.day = in.readString();
    }
}
