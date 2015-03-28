package com.djzass.medipoint;

/**
 * Created by Deka on 26/3/2015.
 */

<<<<<<< HEAD

=======
>>>>>>> origin/master
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;

public class DoctorSchedule {
<<<<<<< HEAD

    private Clinic clinic;
    private Day day;
    private TimeFrame timeFrame;

    public DoctorSchedule(Clinic clinic, Day day, TimeFrame timeFrame) {
        this.clinic = clinic;
        this.day = day;
        this.timeFrame = timeFrame;
=======
=======
>>>>>>> origin/master
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
<<<<<<< HEAD
    public String getDay() {
        return day.getDay();
    }
    public void setDay(Day day) {
        this.day = day;
    }

    public String toString(){
        return "Clinic: " + this.clinic.getName() + "\n" +
                "Day: " + this.day.getDay() + "\n" +
                "Time: " + String.format("%02d", this.timeFrame.getStartHour()) + ":" + String.format("%02d", this.timeFrame.getStartMinute()) +
                "-" + String.format("%02d", this.timeFrame.getEndHour()) + ":" + String.format("%02d", this.timeFrame.getEndMinute());
=======

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
>>>>>>> origin/master
    }

    public Timeframe getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(Timeframe timeframe) {
        this.timeframe = timeframe;
    }

}
