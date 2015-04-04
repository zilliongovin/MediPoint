package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.lang.String;
import java.util.Comparator;

public class Appointment implements Parcelable{
    private int appointmentId;
    private int clinicId;
    private int patientId;
    private int doctorId;
    private Calendar date;
    private int service;
    private int specialtyId;
    private String preAppointmentActions;
    private Timeframe timeframe;



    public Appointment() {}

    public Appointment(int patientId, int specialtyId, int clinicId, int doctorId,
                       Calendar date, Timeframe timeframe, String preAppointmentActions) {
        //this.appointmentId = appointmentId;
        this.specialtyId = specialtyId;
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.doctorId = doctorId;
        this.date = date;
        this.timeframe = timeframe;
        this.date.set(Calendar.HOUR_OF_DAY, timeframe.getStartTime() / 2);
        this.date.set(Calendar.MINUTE, 30 * (timeframe.getStartTime() % 2));
        this.preAppointmentActions = preAppointmentActions;
    }

    public String print(){
        String temp = "";
        temp+= appointmentId + " ";
        temp+= clinicId + " ";
        temp+= patientId + " ";
        temp+= doctorId + " ";
        temp+= String.valueOf(date) + " ";
        temp+= service + " ";
        temp+= specialtyId + " ";
        temp+= preAppointmentActions + " ";
        temp+= timeframe.getStartTime() + "-" + timeframe.getEndTime();
        return temp;
    }

    public int getId() {
        return appointmentId;
    }

    public void setId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return this.patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public int getClinicId() {
        return this.clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public int getDoctorId() {
        return this.doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
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

    public static Comparator<Appointment> AppSortByDate
            = new Comparator<Appointment>() {
        public int compare(Appointment app1, Appointment app2) {
            return app1.getDate().compareTo(app2.getDate());
            //returns negative is app1's date < app2's date
            //sort by earliest
        }
    };

    public static Comparator<Appointment> AppSortBySpecialty
            = new Comparator<Appointment>() {
        public int compare(Appointment app1, Appointment app2) {
            return app1.getSpecialtyId() - app2.getSpecialtyId();
            //returns negative is app1.specialtyID < app2.specialtyID
            //sort ascening
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.appointmentId);
        dest.writeInt(this.specialtyId);
        dest.writeInt(this.patientId);
        dest.writeInt(this.clinicId);
        dest.writeInt(this.doctorId);

        long cal = this.getDate().getTimeInMillis();

        dest.writeLong(cal);
        dest.writeInt(this.getTimeframe().getStartTime());
        dest.writeInt(this.getTimeframe().getEndTime());
        dest.writeString(this.preAppointmentActions);
    }

    public static final Parcelable.Creator<Appointment> CREATOR
            = new Parcelable.Creator<Appointment>() {
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    public Appointment(Parcel in) {
        this.appointmentId = in.readInt();
        this.specialtyId = in.readInt();
        this.patientId = in.readInt();
        this.clinicId = in.readInt();
        this.doctorId = in.readInt();

        this.date = Calendar.getInstance();
        this.date.setTimeInMillis(in.readLong());

        this.timeframe = new Timeframe(in.readInt(),in.readInt());
        this.preAppointmentActions = in.readString();
    }

    public void readFromParcel(Parcel in){
        this.appointmentId = in.readInt();
        this.specialtyId = in.readInt();
        this.patientId = in.readInt();
        this.clinicId = in.readInt();
        this.doctorId = in.readInt();

        this.date = Calendar.getInstance();
        this.date.setTimeInMillis(in.readLong());

        this.timeframe = new Timeframe(in.readInt(),in.readInt());
        this.preAppointmentActions = in.readString();
    }
}