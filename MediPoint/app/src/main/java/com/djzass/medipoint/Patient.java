package com.djzass.medipoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Calendar;

/**
 * Created by Deka on 25/3/2015.
 */

public class Patient extends Account{
    private int patientId;
    private int age;
    private String allergy;
    private String medicalHistory;
    private String listOfTreatments;
    private String listOfMedications;

    public Patient(){
        super();
    };

    public Patient(String username, String password, String name, String nric, String email, String phoneNumber, String gender,
                   String address, String maritalStatus, Calendar dob, String citizenship, String countryOfResidence,String listOfTreatments, String listOfMedications, String allergy) {
        super(username, password, name, nric, email, phoneNumber, gender, address, maritalStatus, dob, citizenship, countryOfResidence);
        this.age = getAge();
        this.listOfTreatments = listOfTreatments;
        this.listOfMedications = listOfMedications;
        this.allergy = allergy;
    }

    //to read from database
    public Patient(int accountId, int patientId, String username, String password, String name, String nric, String email, String phoneNumber, String gender,
                   String address, String maritalStatus, Calendar dob, String citizenship, String countryOfResidence,String listOfTreatments, String listOfMedications, String allergy) {
        super(accountId, username, password, name, nric, email, phoneNumber, gender, address, maritalStatus, dob, citizenship, countryOfResidence);
        this.patientId = patientId;
        this.age = getAge();
        this.listOfTreatments = listOfTreatments;
        this.listOfMedications = listOfMedications;
        this.allergy = allergy;
    }

    public Patient(String name, String nric, String email, String phoneNumber, String address, String listOfTreatments, String listOfMedications, String allergy) {
        super(name, nric, email, phoneNumber, address);
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

    public int getAge() {
        Calendar dob = this.getDob();
        Calendar now = Calendar.getInstance();
        int age =  now.YEAR - dob.YEAR;

        if ( (dob.MONTH > now.MONTH) || (dob.MONTH == now.MONTH) && (dob.DATE > now.DATE) ){
            age--;
        }

        return age;
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
