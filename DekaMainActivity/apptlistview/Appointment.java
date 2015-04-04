package com.djzass.mediapp.apptlistview;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Deka on 29/3/2015.
 */
public class Appointment implements Parcelable {
    private int id;
    private String name;
    private String status;
    private Calendar dateTime;
    private String clinic;
    private String country;

    public Appointment(int id, String name, String status, Calendar dateTime, String clinic, String country) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.dateTime = dateTime;
        this.clinic = clinic;
        this.country = country;
    }

    public Appointment(Parcel in){
        readFromParcel(in);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel destination, int flags){
        destination.writeInt(this.id);
        destination.writeString(this.name);
        //destination.writeSerializable(this.dob);
        destination.writeString(this.status);
        destination.writeLong(this.dateTime.getTimeInMillis());
        destination.writeString(this.clinic);
        destination.writeString(this.country);
    }
    public void readFromParcel(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        //this.dob = (Calendar) in.readSerializable();
        this.status = in.readString();
        Calendar inst = Calendar.getInstance();
        inst.setTimeInMillis(in.readLong());
        this.dateTime = inst;
        this.clinic = in.readString();
        this.country = in.readString();
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Appointment createFromParcel(Parcel in) { return new Appointment(in);
        }
        public Appointment[] newArray(int size) { return new Appointment[size]; }
    };

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDateString(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        return sdfDate.format(this.dateTime.getTime());
    }

    public String getTimeString(){
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm - HH:mm");
        return sdfTime.format(this.dateTime.getTime());
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String toString(){
        return "Id: " + this.id + "\n" +
                "Name: " + this.name + "\n" +
                "Date: " + this.getDateString() + "\n" +
                "Time: " + this.getTimeString() + "\n" +
                "Clinic: " + this.getClinic() + "\n" +
                "Country: " + this.getCountry()   ;
    }
}
