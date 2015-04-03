package com.djzass.medipoint.entity;

/**
 * Created by Deka on 26/3/2015.
 */

public class DoctorSchedule {
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

}
