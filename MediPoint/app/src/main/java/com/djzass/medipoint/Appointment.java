package com.djzass.medipoint;

import java.util.Calendar;
import java.lang.String;
import java.util.ArrayList;

public class Appointment {
    private int appointmentId;
    private String patient;
    private String specialty;
    private String clinic;
    private String doctor;
    private Calendar date;
    private Timeframe timeframe;
    private ArrayList<String> preAppointmentActions;
    private boolean active = false; //1= active, 0= inactive

    public Appointment(int appointmentId){
        this.appointmentId = appointmentId;
        active = false;
    }
    public Appointment(int appointmentId, String patient, String specialty, String clinic, String doctor,
                       Calendar date, Timeframe timeframe, ArrayList<String> preAppointmentActions) {
        this.appointmentId = appointmentId;
        this.specialty = specialty;
        this.patient = patient;
        this.clinic = clinic;
        this.doctor = doctor;
        this.date = date;
        this.timeframe = timeframe;
        this.date.set(Calendar.HOUR_OF_DAY,  timeframe.getStartTime() / 2);
        this.date.set(Calendar.MINUTE, 30 * (timeframe.getStartTime() % 2));
        this.preAppointmentActions = new ArrayList<String>();
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Timeframe getTimeframe(){
        return this.timeframe;
    }
    public void setTimeframe(Timeframe timeframe){
        this.timeframe = timeframe;
        this.date.set(Calendar.HOUR_OF_DAY,  timeframe.getStartTime() / 2);
        this.date.set(Calendar.MINUTE, 30 * (timeframe.getStartTime() % 2));
    }

    public ArrayList<String> getPreAppointmentActions() {
        return preAppointmentActions;
    }

    public void setPreAppointmentActions(ArrayList<String> preAppointmentActions) {
        this.preAppointmentActions = preAppointmentActions;
    }

    public boolean isActive(){
        return this.active;
    }

    public void setActive(boolean active){
        this.active = active;
    }
