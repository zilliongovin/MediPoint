package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deka on 28/3/2015.
 */
public class PatientDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID
            + " =?";

    public static final String PATIENT_ID_WITH_PREFIX = "patient.";
    public static final String ACCOUNT_ID_WITH_PREFIX = "account.";

    public PatientDAO(Context context) throws SQLException {
        super(context);
    }

    /*
        CREATE
         Inserting doctor schedule into doctor schedules table and return the row id if insertion successful,
     otherwise -1 will be returned
     */
    public long insertPatient(Patient patient){
        ContentValues values = new ContentValues();
        values.put(DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID_STRING, patient.getPatientIdString());
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
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                ACCOUNT_ID_WITH_PREFIX + DbContract.AccountEntry.COLUMN_NAME_USERNAME +


                " FROM " + DbContract.PatientEntry.TABLE_NAME + " patient, " +
                DbContract.AccountEntry.TABLE_NAME + " account, WHERE " + PATIENT_ID_WITH_PREFIX +
                DbContract.PatientEntry.COLUMN_NAME_ACCOUNT_ID + " = " + ACCOUNT_ID_WITH_PREFIX +
                DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID ;

        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Patient patient= new Patient();
            patient.setId(cursor.getInt(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_ACCOUNT_ID)));
            patient.setUsername(cursor.getString(cursor.getColumnIndex(DbContract.PatientEntry.)));
            patient.setPassword(cursor.getString());
            patient.setName();
            patient.setNric();
            patient.setEmail();
            patient.setPhoneNumber();
            patient.setGender();
            patient.setAddress();
            patient.setMaritalStatus();
            patient.setDob();
            patient.setCitizenship();
            patient.setCountryOfResidence();
            patient.setAge();
            patient.setAllergy();
            patient.setMedicalHistory();
            patient.setListOfTreatments();
            patient.setListOfMedications();
            patient.setId(cursor.getInt(cursor.getColumnIndex(DbContract.PatientEntry.COLUMN_NAME_ACCOUNT_ID)));
            patient.setName(cursor.getString());
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
        values.put(DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID_STRING, patient.getPatientIdString());
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
        Patient p1 = new Patient();
        Patient p2 = new Patient();
        Patient p3 = new Patient();

        List<Patient> patients = new ArrayList<Patient>();
        patients.add(p1);
        patients.add(p2);
        patients.add(p3);
        for (Patient p: patients) {
            insertPatient(p);
        }
    }
}
