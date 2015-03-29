package com.djzass.medipoint;

/**
 * Created by Deka on 26/3/2015.
 */

<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> origin/master
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;

=======
>>>>>>> 49e9b696f1b6c1c2563389694bac34700083f3c5
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
