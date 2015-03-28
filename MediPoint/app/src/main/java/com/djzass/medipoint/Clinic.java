package com.djzass.medipoint;

import java.lang.String;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Deka on 25/3/2015.
 */
public class Clinic {
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

    public Clinic(String name, String address, int zipCode, int telNumber, int faxNumber, String email, String country){
        this.name = name;
        this.address = address;
        this.country = country;
        this.zipCode = zipCode;
        this.faxNumber= faxNumber;
        this.telNumber = telNumber;
        this.email = email;
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
}
