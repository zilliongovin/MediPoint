package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Calendar;

/**
 * Created by Deka on 25/3/2015.
 */

public class Patient implements Parcelable{
    private int patientId;
    private Calendar dob;
    private int age;
    private String allergy;
    private String medicalHistory;
    private String listOfTreatments;
    private String listOfMedications;

    public Patient(){
        super();
    };

    public Patient(int id, Calendar dob, String listOfTreatments, String listOfMedications, String allergy) {
        this.patientId = id;
        this.dob = dob;
        this.age = getAge();
        this.listOfTreatments = listOfTreatments;
        this.listOfMedications = listOfMedications;
        this.allergy = allergy;
    }

    public Patient(Calendar dob, String listOfTreatments, String listOfMedications, String allergy) {
        this.dob = dob;
        this.age = getAge();
        this.listOfTreatments = listOfTreatments;
        this.listOfMedications = listOfMedications;
        this.allergy = allergy;
    }

    public int getPatientId() {
        return this.patientId;
    }

    public void setPatientId(int patientID) {
        this.patientId = patientID;
    }

    public int getAge( ) {
        Calendar now = Calendar.getInstance();
        int age =  now.YEAR - dob.YEAR;
        if ( (dob.MONTH > now.MONTH) || (dob.MONTH == now.MONTH) && (dob.DATE > now.DATE) ){
            age--;
        }
        return age;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public String print(){
        String temp = "";
        temp+= patientId + " ";
        temp+= String.valueOf(dob) + " ";
        temp+= allergy + " ";
        temp+= medicalHistory + " ";
        temp+= listOfTreatments + " ";
        temp+= listOfMedications + " ";
        return temp;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getListOfTreatments() {
        return listOfTreatments;
    }

    public void setListOfTreatments(String listOfTreatments) {
        this.listOfTreatments = listOfTreatments;
    }

    public String getListOfMedications() {
        return listOfMedications;
    }

    public void setListOfMedications(String listOfMedications) {
        this.listOfMedications = listOfMedications;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public Patient(Parcel in){
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel desc, int flags) {
        desc.writeInt(this.patientId);
        desc.writeSerializable(this.dob);
        desc.writeInt(this.age);
        desc.writeString(this.allergy);
        desc.writeString(this.medicalHistory);
        desc.writeString(this.listOfTreatments);
        desc.writeString(this.listOfMedications);
        /*private Timeframe timeframe;*/
    }

    public static final Parcelable.Creator<Patient> CREATOR
            = new Parcelable.Creator<Patient>() {
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.patientId = in.readInt();
        this.dob = (Calendar)in.readSerializable();
        this.age = in.readInt();
        this.allergy = in.readString();
        this.medicalHistory = in.readString();
        this.listOfTreatments = in.readString();
        this.listOfMedications = in.readString();
    }
}
