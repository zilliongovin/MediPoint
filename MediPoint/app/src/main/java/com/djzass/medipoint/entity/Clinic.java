package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.String;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Deka on 25/3/2015.
 */
public class Clinic implements Parcelable {
    private int id;
    private String name;
    private String address;
    private int zipCode;
    private int telNumber;
    private int faxNumber;
    private String email;
    private String country;

    public Clinic(){

    }

    public Clinic(int id, String name, String address, int zipCode, int telNumber, int faxNumber, String email, String country){
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.faxNumber= faxNumber;
        this.telNumber = telNumber;
        this.email = email;
        this.country = country;
    }

    public Clinic(String name, String address, String country, int zipCode, int telNumber, int faxNumber, String email){
        this.name = name;
        this.address = address;
        this.country = country;
        this.zipCode = zipCode;
        this.faxNumber= faxNumber;
        this.telNumber = telNumber;
        this.email = email;
    }

    public String print(){
        String temp = "";
        temp+= id + " ";
        temp+= name + " ";
        temp+= address + " ";
        temp+= zipCode + " ";
        temp+= zipCode + " ";
        temp+= faxNumber + " ";
        temp+= email + " ";
        temp+= country;
        return temp;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    public int getTelNumber() {
        return telNumber;
    }
    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }
    public int getFaxNumber() {
        return faxNumber;
    }
    public void setFaxNumber(int faxNumber) {
        this.faxNumber = faxNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String toString(){
        String tabSpace = "      ";
        if (faxNumber == 0)
            return this.name + "\n" +
                    tabSpace + "Address: " + this.address + ", " + this.country + ". " + this.zipCode + ".\n"+
                    tabSpace + "Tel: " + this.telNumber + " Fax: - \n"+
                    tabSpace + "Email: " + this.email + "\n";
        else
            return this.name + "\n" +
                    tabSpace + "Address: " + this.address + ", " + this.country + ". " + this.zipCode + ".\n"+
                    tabSpace + "Tel: " + this.telNumber + " Fax: " + this.faxNumber + "\n"+
                    tabSpace + "Email: " + this.email + "\n";
    }
    public Clinic(Parcel in){
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
        desc.writeInt(this.zipCode);
        desc.writeString(this.email);
        desc.writeInt(this.telNumber);
        desc.writeInt(this.faxNumber);
        desc.writeString(this.address);
        desc.writeString(this.country);
    }

    public static final Parcelable.Creator<Clinic> CREATOR
            = new Parcelable.Creator<Clinic>() {
        public Clinic createFromParcel(Parcel in) {
            return new Clinic(in);
        }

        public Clinic[] newArray(int size) {
            return new Clinic[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.zipCode = in.readInt();
        this.telNumber = in.readInt();
        this.faxNumber = in.readInt();
        this.address = in.readString();
        this.country = in.readString();
    }
}
