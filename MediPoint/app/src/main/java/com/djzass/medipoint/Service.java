package com.djzass.medipoint;

/**
 * Created by Deka on 26/3/2015.
 */
public class Service {
    private int serviceId;
    private Specialty specialty;
    private String name;
    private int duration;
    private String preAppointmentActions;

    public Service(){
        this.duration = 30;
        this.preAppointmentActions = "None";
    }
    public Service(String name, Specialty specialty) {
        this.name = name;
        this.specialty = specialty;

        //The regular timing is 30 minutes
        this.duration = 30;
        this.preAppointmentActions = "None";
    }
    public Service(String name, Specialty specialty, int duration) {
        this.name = name;
        this.specialty = specialty;
        this.duration = duration;
        this.preAppointmentActions = "None";
    }
    public Service(String name, Specialty specialty, int duration, String preAppointmentAction) {
        this.name = name;
        this.specialty = specialty;
        this.duration = duration;
        this.preAppointmentActions = preAppointmentAction;
    }
    public int getId() {
        return serviceId;
    }
    public void setId(int serviceId) {
        this.serviceId = serviceId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public Specialty getSpecialty() {
        return specialty;
    }
    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
    public String getPreAppointmentActions() {
        return preAppointmentActions;
    }
    public void setPreAppointmentActions(String preAppointmentActions) {
        this.preAppointmentActions = preAppointmentActions;
    }
}
