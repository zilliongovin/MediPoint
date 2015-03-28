package com.djzass.medipoint;

import java.util.Calendar;
import java.lang.String;

public class Appointment {
    private int id;
    private Clinic clinic;
    private Patient patient;
    private Doctor doctor;
    private Calendar date;
    private Service service;
    private Specialty specialty;
    private String preAppointmentActions;
    private Timeframe timeframe;
    private boolean active = false; //1= active, 0= inactive



    public Appointment() {
        active = false;
    }

    public Appointment(Patient patient, Specialty specialty, Clinic clinic, Doctor doctor,
                       Calendar date, Timeframe timeframe, String preAppointmentActions) {
        this.id = id;
        this.specialty = specialty;
        this.patient = patient;
        this.clinic = clinic;
        this.doctor = doctor;
        this.date = date;
        this.timeframe = timeframe;
        this.date.set(Calendar.HOUR_OF_DAY, timeframe.getStartTime() / 2);
        this.date.set(Calendar.MINUTE, 30 * (timeframe.getStartTime() % 2));
        this.preAppointmentActions = preAppointmentActions;
    }

    public int getId() {
        return id;
    }

    public void setId(int appointmentId) {
        this.id = appointmentId;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Clinic getClinic() {
        return this.clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Timeframe getTimeframe() {
        return this.timeframe;
    }

    public void setTimeframe(Timeframe timeframe) {
        this.timeframe = timeframe;
        this.date.set(Calendar.HOUR_OF_DAY, timeframe.getStartTime() / 2);
        this.date.set(Calendar.MINUTE, 30 * (timeframe.getStartTime() % 2));
    }

    public String getPreAppointmentActions() {
        return this.preAppointmentActions;
    }

    public void setPreAppointmentActions(String preAppointmentActions) {
        this.preAppointmentActions = preAppointmentActions;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
/*
=======
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Deka on 25/3/2015.
 *//*
public class Appointment implements Parcelable{
    /*private int appoinmentId;
    private Clinic clinic;
    //private Doctor doctor;
    //private Timestamp ;
    //private Service service;
    //private Specialty specialty;
    private String preAppointmentActions;
    private Patient patient;
    private Calendar dateTime;
    private Date startTime;
    private Date endTime;



    public Appointment() {
        super();
    }

    // All the attributes
    public Appointment(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }


    //Give all attributes
    private Department(Parcel in) {
        super();
        //this.id = in.readInt();
        //this.name = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id:" + id + ", name:" + name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getName());
    }

    public static final Parcelable.Creator<Appointment> CREATOR = new Parcelable.Creator<Department>() {
        public Department createFromParcel(Parcel in) {
            return new Department(in);
        }

        public Department[] newArray(int size) {
            return new Department[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Appointment other = (Appointment) obj;
        if (id != other.id)
            return false;
        return true;
    }
}*/

