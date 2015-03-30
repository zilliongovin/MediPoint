package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Deka on 28/3/2015.
 */
public class PatientDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID
            + " =?";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);
    public static final String PATIENT_ID_WITH_PREFIX = "patient.";
    public static final String ACCOUNT_ID_WITH_PREFIX = "account.";

    public PatientDAO(Context context) throws SQLException {
        super(context);
    }

    /*
    CREATE
     Inserting doctor schedule into doctor schedules table and return the row id if insertion successful,
     otherwise -1 will be returned
    IMPORTANT: For doctor & patient, ID is received in the passed object, not auto-increment
     */
    public long insertPatient(Patient patient){
        ContentValues values = new ContentValues();
        values.put(DbContract.PatientEntry.COLUMN_NAME_AGE, patient.getAge());
        values.put(DbContract.PatientEntry.COLUMN_NAME_AGE, patient.getAge());
        values.put(DbContract.PatientEntry.COLUMN_NAME_MEDICAL_HISTORY, patient.getMedicalHistory());
        values.put(DbContract.PatientEntry.COLUMN_NAME_ALLERGIES, patient.getAllergy());
        values.put(DbContract.PatientEntry.COLUMN_NAME_TREATMENTS, patient.getListOfTreatments());
        values.put(DbContract.PatientEntry.COLUMN_NAME_MEDICATIONS, patient.getListOfMedications());

        // Inserting Row
        return database.insert(DbContract.PatientEntry.TABLE_NAME, null, values);
    }

    /*
        READ
      * Getting all patients from the table
     * returns list of patients
     * */
    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<Patient>();

        //MUST JOIN
        String query = "SELECT " + ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_NAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_NRIC +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_EMAIL +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_CONTACTNO +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_ADDRESS +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_DOB +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_GENDER +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_PASSWORD +
                PATIENT_ID_WITH_PREFIX + DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID +
                PATIENT_ID_WITH_PREFIX + DbContract.PatientEntry.COLUMN_NAME_ACCOUNT_ID +
                PATIENT_ID_WITH_PREFIX + DbContract.PatientEntry.COLUMN_NAME_AGE +
                PATIENT_ID_WITH_PREFIX + DbContract.PatientEntry.COLUMN_NAME_MEDICAL_HISTORY +
                PATIENT_ID_WITH_PREFIX + DbContract.PatientEntry.COLUMN_NAME_ALLERGIES +
                PATIENT_ID_WITH_PREFIX + DbContract.PatientEntry.COLUMN_NAME_TREATMENTS +
                PATIENT_ID_WITH_PREFIX + DbContract.PatientEntry.COLUMN_NAME_MEDICATIONS +
                " FROM " + DbContract.PatientEntry.TABLE_NAME + " patient, " +
                DbContract.AccountEntry.TABLE_NAME + " account, WHERE " + PATIENT_ID_WITH_PREFIX +
                DbContract.PatientEntry.COLUMN_NAME_ACCOUNT_ID + " = " + ACCOUNT_ID_WITH_PREFIX +
                DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID ;

        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            /*public Patient(   int Pid, String username,        String password,        String name,        String nric,
                                String email,           String phoneNumber,     String gender,      String address,
                                String maritalStatus,   Calendar dob,           String citizenship, String countryOfResidence,
                                String listOfTreatments,    String listOfMedications,   String allergy) {
            */
            Calendar dob = Calendar.getInstance();
            try {
                dob.setTime(formatter.parse(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_DOB))));
            } catch (ParseException e) { dob = null; }

            Patient patient= new Patient(cursor.getInt(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID)),
                                        cursor.getInt(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_USERNAME)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_PASSWORD)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_NAME)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_NRIC)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_EMAIL)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_CONTACTNO)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_GENDER)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_ADDRESS)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS)),
                                        dob,
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_TREATMENTS)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_MEDICATIONS)),
                                        cursor.getString(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_ALLERGIES))
                    );

            patients.add(patient);
        }


        return patients;
    }

    /*
        FETCH BY ID
     */
    //READ SINGLE ROW
    //MUST JOIN
    public Patient getPatientById(long patientId) {
        String selectQuery = "SELECT  * FROM " + DbContract.PatientEntry.TABLE_NAME + " WHERE "
                + DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID + " = " + patientId;

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        // Create the class object, then set the attribute from content of the exisiting data in the table
        Patient patient = new Patient();
        patient.setId(c.getInt(c.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID)));


        return patient;
    }
    /*
        UPDATE
       returns the number of rows affected by the update
     */

    public long update(Patient patient) {
        ContentValues values = new ContentValues();
        values.put(DbContract.PatientEntry.COLUMN_NAME_AGE, patient.getAge());
        values.put(DbContract.PatientEntry.COLUMN_NAME_MEDICAL_HISTORY, patient.getMedicalHistory());
        values.put(DbContract.PatientEntry.COLUMN_NAME_ALLERGIES, patient.getAllergy());
        values.put(DbContract.PatientEntry.COLUMN_NAME_TREATMENTS, patient.getListOfTreatments());
        values.put(DbContract.PatientEntry.COLUMN_NAME_MEDICATIONS, patient.getListOfMedications());

        long result = database.update(DbContract.PatientEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(patient.getId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deletePatient(Patient patient) {
        return database.delete(DbContract.PatientEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { patient.getId() + "" });
    }
    /*
        LOAD
        Load the initial values of the patients
     */
    public void loadPatients() {
    }
    private void initializeDAO(){
    }
}
/*
            patient.setUsername(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_USERNAME)));
            patient.setPassword(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_PASSWORD)));
            patient.setName(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_NAME)));
            patient.setNric(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_NRIC)));
            patient.setEmail(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_EMAIL)));
            patient.setPhoneNumber(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_CONTACTNO)));
            patient.setGender(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_GENDER)));
            patient.setAddress(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_ADDRESS)));
            patient.setMaritalStatus(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS)));
            patient.setCitizenship(DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP);
            patient.setCountryOfResidence(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE)));
            patient.setAge(cursor.getInt(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_AGE)));
            patient.setAllergy(cursor.getString(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_ALLERGIES)));
            patient.setMedicalHistory(cursor.getString(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_MEDICAL_HISTORY)));
            patient.setListOfTreatments(cursor.getString(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_TREATMENTS)));
            patient.setListOfMedications(cursor.getString(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_MEDICATIONS)));
            patient.setPatientId(cursor.getInt(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID)));

            patient.setId(cursor.getInt(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID)));*/