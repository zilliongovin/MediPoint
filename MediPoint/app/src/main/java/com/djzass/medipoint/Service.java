package com.djzass.medipoint;

/**
 * Created by Deka on 26/3/2015.
 */
public class Service {
<<<<<<< HEAD

=======
>>>>>>> origin/master
    private int serviceId;
    private int specialtyId;
    private String name;
    private int duration;
    private String preAppointmentActions;

    public Service(){
        this.duration = 30;
        this.preAppointmentActions = "None";
    }
    public Service(String name, int specialtyId) {
        this.name = name;
        this.specialtyId = specialtyId;

        //The regular timing is 30 minutes
        this.duration = 30;
        this.preAppointmentActions = "None";
    }
    public Service(String name, int specialtyId, int duration) {
        this.name = name;
        this.specialtyId = specialtyId;
        this.duration = duration;
        this.preAppointmentActions = "None";
    }
    public Service(String name, int specialtyId, int duration, String preAppointmentAction) {
        this.name = name;
        this.specialtyId = specialtyId;
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
    public int getSpecialtyId() {
        return specialtyId;
    }
    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }
    public String getPreAppointmentActions() {
        return preAppointmentActions;
    }
    public void setPreAppointmentActions(String preAppointmentActions) {
        this.preAppointmentActions = preAppointmentActions;
    }
<<<<<<< HEAD

=======
>>>>>>> origin/master
}
