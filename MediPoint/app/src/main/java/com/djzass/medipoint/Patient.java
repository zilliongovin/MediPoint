package com.djzass.medipoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Calendar;

/**
 * Created by Deka on 25/3/2015.
 */

public class Patient{
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
}
