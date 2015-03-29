package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deka on 26/3/2015.
 */
public class DoctorDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID
            + " =?";
    //private SpecialtyDAO specialtyDao;

    public DoctorDAO(Context context) throws SQLException {
        super(context);
    }

    /* CREATE/SAVE
    Inserting doctor into doctors table and return the row id if insertion successful,
    otherwise -1 will be returned
     */
    public long insertDoctor(Doctor doctor){
        ContentValues values = new ContentValues();
        values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID_STRING, doctor.getDoctorId());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME, doctor.getName());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID, doctor.getSpecializationId());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION, doctor.getPracticeDuration());

        // Inserting Row
        return database.insert(DbContract.DoctorEntry.TABLE_NAME, null, values);
    }

    /** READ
     * Getting all doctors from the table
     * returns list of doctors
     * */
    public List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<Doctor>();

        // MUST JOIN THE TABLES BETWEEN DOCTOR AND SPECIALTIES
        // Select all rows
        // String selectQuery = "SELECT  * FROM " + DbContract.DoctorEntry.TABLE_NAME;
        Cursor cursor = database.query(DbContract.DoctorEntry.TABLE_NAME,
                new String[] { DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID,
                        DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID_STRING,
                        DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME,
                        DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID,
                        DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION
                }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Doctor doctor = new Doctor();
            doctor.setId(cursor.getInt(0));
            doctor.setDoctorId(cursor.getString(1));
            doctor.setName(cursor.getString(2));
            //doctor.setSpecialization(cursor.getInt(3));
            doctor.setPracticeDuration(cursor.getInt(4));
            doctors.add(doctor);
        }

        return doctors;
    }
    //READ SINGLE ROW
    public Doctor getDoctorById(int doctorId) {
        //String selectQuery = "SELECT  * FROM " + DbContract.DoctorEntry.TABLE_NAME + " WHERE "
        //        + DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID + " = " + doctorId;

        //Cursor c = database.rawQuery(selectQuery, null);
        Cursor c = database.query(DbContract.DoctorEntry.TABLE_NAME,
                new String[] { DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID,
                        DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME,
                        DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID,
                        DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION
                }, DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID + " = ?", new String[]{doctorId+""}, null, null,
                null);

        if (c != null)
            c.moveToFirst();

        // Create the class object, then set the attribute from content of the exisiting data in the table
        Doctor doctor = new Doctor();
        doctor.setId(c.getInt(c.getColumnIndex(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID)));
        doctor.setName(c.getString(c.getColumnIndex(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME)));
        //doctor.setSpecialization(specialtyDao.getSpecialtyById(c.getInt(c.getColumnIndex(DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID))));
        doctor.setSpecializationId(c.getColumnIndex(DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID));
        doctor.setPracticeDuration(c.getInt(c.getColumnIndex(DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION)));

        return doctor;
    }

    /*  UPDATE
        returns the number of rows affected by the update
     */
    public long update(Doctor doctor) {
        ContentValues values = new ContentValues();
        values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID_STRING, doctor.getDoctorId());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME, doctor.getName());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID, doctor.getSpecializationId());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION, doctor.getPracticeDuration());

        long result = database.update(DbContract.DoctorEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(doctor.getDID()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deleteDoctor(Doctor doctor) {
        return database.delete(DbContract.DoctorEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { doctor.getDID() + "" });
    }
    /*
        LOAD
        Load the initial values of the doctors
     */
    public void loadDoctors() {
        Doctor d1 = new Doctor();
        Doctor d2 = new Doctor();
        Doctor d3 = new Doctor();

        List<Doctor> doctors = new ArrayList<Doctor>();
        doctors.add(d1);
        doctors.add(d2);
        doctors.add(d3);
        for (Doctor d: doctors) {
            insertDoctor(d);
        }
    }
}
