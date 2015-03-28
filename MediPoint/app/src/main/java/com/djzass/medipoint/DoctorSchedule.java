package com.djzass.medipoint;

/**
 * Created by Deka on 26/3/2015.
 */

import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;

public class DoctorSchedule {
    private int id;
    private Doctor doctor;
    private Clinic clinic;
    private String day;

    private Timeframe timeframe;

    public DoctorSchedule(){

    }

    public DoctorSchedule(Doctor doctor, Clinic clinic, String day) {
        this.doctor = doctor;
        this.clinic = clinic;
        this.day = day;
    }

    public DoctorSchedule(Doctor doctor, Clinic clinic, String day, Timeframe timeframe) {
        this.doctor = doctor;
        this.clinic = clinic;
        this.day = day;
        this.timeframe = timeframe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
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
