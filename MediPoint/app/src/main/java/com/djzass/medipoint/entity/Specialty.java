package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Deka on 26/3/2015.
 */
public class Specialty implements Parcelable{
    private int id;
    private String name;

    public Specialty(){
    }
    public Specialty(String name){
        this.name = name;
    }

    public String print(){
        String temp = "";
        temp+= id + " ";
        temp+= name + " \n";
        return temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Specialty(Parcel in){
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel desc, int flags) {
        desc.writeInt(this.id);
        desc.writeString(this.name);
    }

    public static final Parcelable.Creator<Specialty> CREATOR
            = new Parcelable.Creator<Specialty>() {
        public Specialty createFromParcel(Parcel in) {
            return new Specialty(in);
        }

        public Specialty[] newArray(int size) {
            return new Specialty[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
    }


}
