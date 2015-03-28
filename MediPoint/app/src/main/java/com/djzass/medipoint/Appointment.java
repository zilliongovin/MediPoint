package com.djzass.medipoint;


import java.util.Date;
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

    public Appointment(int appointmentId) {
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
        this.date.set(Calendar.HOUR_OF_DAY, timeframe.getStartTime() / 2);
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

    public Timeframe getTimeframe() {
        return this.timeframe;
    }

    public void setTimeframe(Timeframe timeframe) {
        this.timeframe = timeframe;
        this.date.set(Calendar.HOUR_OF_DAY, timeframe.getStartTime() / 2);
        this.date.set(Calendar.MINUTE, 30 * (timeframe.getStartTime() % 2));
    }

    public ArrayList<String> getPreAppointmentActions() {
        return preAppointmentActions;
    }

    public void setPreAppointmentActions(ArrayList<String> preAppointmentActions) {
        this.preAppointmentActions = preAppointmentActions;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
/*import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Deka on 25/3/2015.
public class Appointment implements Parcelable{
    private int appoinmentId;
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

    }
}
*/