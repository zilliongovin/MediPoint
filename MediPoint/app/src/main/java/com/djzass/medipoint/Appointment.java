package com.djzass.medipoint;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Deka on 25/3/2015.
 */
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

    public static final String TABLE_NAME = "appointment";
    public static final String COLUMN_NAME_APPOINTMENT_ID = "appoinmentId";
    public static final String COLUMN_NAME_CLINIC_ID = "clinicId";
    public static final String COLUMN_NAME_PATIENT_ID = "patientId";
    public static final String COLUMN_NAME_DOCTOR_ID = "doctorId";
    public static final String COLUMN_NAME_DATE_TIME = "dateTime";
    public static final String COLUMN_NAME_SERVICE_ID = "service";
    public static final String COLUMN_NAME_SPECIALTY_ID = "specialty";
    public static final String COLUMN_NAME_PREAPPOINTMENT_ACTIONS = "preAppointmentActions";
    public static final String COLUMN_NAME_START_TIME = "startTime";
    public static final String COLUMN_NAME_END_TIME = "endTime";


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
    }*/
}