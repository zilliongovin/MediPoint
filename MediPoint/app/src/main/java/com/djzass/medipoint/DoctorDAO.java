package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deka on 26/3/2015.
 */
public class DoctorDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID
            + " =?";

    public DoctorDAO(Context context) {
        super(context);
    }

    /* CREATE/SAVE
    Inserting doctor into countries table and return the row id if insertion successful,
     otherwise -1 will be returned
     */
    public long insertDoctor(Doctor doctor){
        ContentValues values = new ContentValues();
        values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME, doctor.getName());

        // Inserting Row
        return database.insert(DbContract.DoctorEntry.TABLE_NAME, null, values);
    }

    /** READ
     * Getting all countries from the table
     * returns list of countries
     * */
    public List<Doctor> getCountries() {
        List<Doctor> countries = new ArrayList<Doctor>();

        // Select all rows
        // String selectQuery = "SELECT  * FROM " + DbContract.DoctorEntry.TABLE_NAME;
        Cursor cursor = database.query(DbContract.DoctorEntry.TABLE_NAME,
                new String[] { DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID,
                        DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Doctor doctor= new Doctor();
            doctor.setId(cursor.getInt(0));
            doctor.setName(cursor.getString(1));
            countries.add(doctor);
        }

        return countries;
    }
    //READ SINGLE ROW
    public Doctor getDoctorById(long doctorId) {
        String selectQuery = "SELECT  * FROM " + DbContract.DoctorEntry.TABLE_NAME + " WHERE "
                + DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID + " = " + doctorId;

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        // Create the class object, then set the attribute from content of the exisiting data in the table
        Doctor doctor = new Doctor();
        doctor.setId(c.getInt(c.getColumnIndex(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID)));
        doctor.setName(c.getString(c.getColumnIndex(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME)));

        return doctor;
    }

    /*  UPDATE
        returns the number of rows affected by the update
     */
    public int update(Doctor doctor) {
        ContentValues values = new ContentValues();
        values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME, doctor.getName());

        long result = database.update(DbContract.DoctorEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(doctor.getId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deleteDoctor(Doctor doctor) {
        return database.delete(DbContract.DoctorEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { doctor.getId() + "" });
    }
    /*
        LOAD
        Load the initial values of the countries
     */

    public void loadCountries() {
        Doctor c1 = new Doctor("Singapore");
        Doctor c2 = new Doctor("Malaysia");
        Doctor c3 = new Doctor("Thailand");

        List<Doctor> countries = new ArrayList<Doctor>();
        countries.add(c1);
        countries.add(c2);
        countries.add(c3);
        for (Doctor c: countries) {
            database.insert(c);
        }
    }
}
