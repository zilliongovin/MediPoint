package com.djzass.medipoint.logic_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.djzass.medipoint.DbContract;
import com.djzass.medipoint.entity.Patient;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
        values.put(DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID, patient.getPatientId());
        values.put(DbContract.PatientEntry.COLUMN_NAME_DOB, String.valueOf(patient.getDob()));
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
    public List<Patient> getPatients(String whereclause) {
        List<Patient> patients = new ArrayList<Patient>();

        //MUST JOIN
        Cursor cursor = database.query(DbContract.AccountEntry.TABLE_NAME,
                new String[] {
                    DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID,
                    DbContract.PatientEntry.COLUMN_NAME_DOB,
                    DbContract.PatientEntry.COLUMN_NAME_AGE,
                    DbContract.PatientEntry.COLUMN_NAME_MEDICAL_HISTORY,
                    DbContract.PatientEntry.COLUMN_NAME_ALLERGIES,
                    DbContract.PatientEntry.COLUMN_NAME_TREATMENTS,
                    DbContract.PatientEntry.COLUMN_NAME_MEDICATIONS
                }, whereclause, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Calendar dob = Calendar.getInstance();
            try {
                dob.setTime(formatter.parse(cursor.getString(cursor.getColumnIndex(DbContract.AccountEntry.COLUMN_NAME_DOB))));
            } catch (ParseException e) { dob = null; }

            Patient patient= new Patient();
            patient.setPatientId(cursor.getInt(0));

            String temp = cursor.getString(1);
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal  = Calendar.getInstance();
            try {
                cal.setTime(dateformat.parse(temp));
            } catch (ParseException e) {
                Log.d("DAO", "Date parsing exception");
            }
            patient.setDob(cal);
            patient.setAge(patient.getAge());
            patient.setMedicalHistory(cursor.getString(3));
            patient.setAllergy(cursor.getString(4));
            patient.setListOfTreatments(cursor.getString(5));
            patient.setListOfMedications(cursor.getString(6));

            patients.add(patient);
        }
        return patients;
    }

    public List<Patient> getAllPatients(){
        return getPatients(null);
    }

    public List<Patient> getPatientById(long patientId) {
        String whereclause = DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID + " = " + patientId;
        return getPatients(whereclause);
    }


    /*
        UPDATE
       returns the number of rows affected by the update
     */
    public long update(Patient patient) {
        ContentValues values = new ContentValues();
        values.put(DbContract.PatientEntry.COLUMN_NAME_AGE, patient.getAge());
        values.put(DbContract.PatientEntry.COLUMN_NAME_DOB, String.valueOf(patient.getDob()));
        values.put(DbContract.PatientEntry.COLUMN_NAME_MEDICAL_HISTORY, patient.getMedicalHistory());
        values.put(DbContract.PatientEntry.COLUMN_NAME_ALLERGIES, patient.getAllergy());
        values.put(DbContract.PatientEntry.COLUMN_NAME_TREATMENTS, patient.getListOfTreatments());
        values.put(DbContract.PatientEntry.COLUMN_NAME_MEDICATIONS, patient.getListOfMedications());

        long result = database.update(DbContract.PatientEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(patient.getPatientId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deletePatient(Patient patient) {
        return database.delete(DbContract.PatientEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { patient.getPatientId() + "" });
    }
    /*
        LOAD
        Load the initial values of the patients
     */
    public void loadPatients() {
        List<Patient> temp = getAllPatients();
        for (Patient tmp : temp) {
            tmp.print();
        }
    }

    public int getPatientCount(){
        return getAllPatients().size();
    }

    private void initializeDAO(){
        if (getPatientCount()==0){
        }
    }
}