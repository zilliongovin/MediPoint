package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Deka on 26/3/2015.
 */
public class Service implements Parcelable {
    private int serviceId;
    private int specialtyId;
    private String name;
    private int duration;
    private String preAppointmentActions;

    public Service(){
        this.duration = 1;
        this.preAppointmentActions = "None";
    }
    public Service(String name, int specialtyId) {
        this.name = name;
        this.specialtyId = specialtyId;

        //The regular timing is 30 minutes
        this.duration = 1;
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

    public String print(){
        String temp = "";
        temp+= serviceId + " ";
        temp+= specialtyId + " ";
        temp+= name + " ";
        temp+= duration + " ";
        temp+= preAppointmentActions + " ";
        return temp;
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

    public Service(Parcel in){
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel desc, int flags) {
        desc.writeInt(this.serviceId);
        desc.writeInt(this.specialtyId);
        desc.writeString(this.name);
        desc.writeInt(this.duration);
        desc.writeString(this.preAppointmentActions);
        /*private Timeframe timeframe;*/
    }

    public static final Parcelable.Creator<Service> CREATOR
            = new Parcelable.Creator<Service>() {
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.serviceId = in.readInt();
        this.specialtyId = in.readInt();
        this.name = in.readString();
        this.duration = in.readInt();
        this.preAppointmentActions = in.readString();
    }
}
