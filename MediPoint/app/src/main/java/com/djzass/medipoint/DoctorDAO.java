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
        values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME, doctor.getName());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID, doctor.getSpecializationId());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION, doctor.getPracticeDuration());

        return database.insert(DbContract.DoctorEntry.TABLE_NAME, null, values);
    }

    /** READ
     * Getting all doctors from the table
     * returns list of doctors
     * */
    public List<Doctor> getDoctors(String whereclause) {
        List<Doctor> doctors = new ArrayList<Doctor>();

        Cursor cursor = database.query(DbContract.DoctorEntry.TABLE_NAME,
                new String[] { DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID,
                        DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME,
                        DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID,
                        DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION
                }, whereclause, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Doctor doctor = new Doctor();
            doctor.setDoctorId(cursor.getInt(0));
            doctor.setName(cursor.getString(1));
            doctor.setSpecializationId(cursor.getInt(2));
            doctor.setPracticeDuration(cursor.getInt(3));
            doctors.add(doctor);
        }

        return doctors;
    }

    public List<Doctor> getAllDoctors() {
        return getDoctors(null);
    }

    public List<Doctor> getDoctorById(int doctorId) {
        String whereclause = DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID + " = " + doctorId;
        return getDoctors(whereclause);
    }

    public List<Doctor> getDoctorBySpecialization(int specializationId) {
        String whereclause = DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID + " = " + specializationId;
        return getDoctors(whereclause);
    }

    /*  UPDATE
        returns the number of rows affected by the update
     */
    public long update(Doctor doctor) {
        ContentValues values = new ContentValues();
        values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME, doctor.getName());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID, doctor.getSpecializationId());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION, doctor.getPracticeDuration());

        long result = database.update(DbContract.DoctorEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(doctor.getDoctorId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deleteDoctor(Doctor doctor) {
        return database.delete(DbContract.DoctorEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { doctor.getDoctorId() + "" });
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
