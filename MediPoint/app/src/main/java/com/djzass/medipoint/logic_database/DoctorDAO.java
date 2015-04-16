package com.djzass.medipoint.logic_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.djzass.medipoint.entity.Doctor;

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
        initializeDAO();
    }

    /* CREATE/SAVE
    Inserting doctor into doctors table and return the row id if insertion successful,
    otherwise -1 will be returned
    IMPORTANT: For doctor & patient, ID is received in the passed object, not auto-increment
     */
    public long insertDoctor(Doctor doctor){
        ContentValues values = new ContentValues();
        //values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID, doctor.getDoctorId());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME, doctor.getName());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID, doctor.getSpecializationId());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION, doctor.getPracticeDuration());
        values.put(DbContract.DoctorEntry.COLUMN_NAME_CLINIC_ID, doctor.getClinicId());

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
                        DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION,
                        DbContract.DoctorEntry.COLUMN_NAME_CLINIC_ID
                }, whereclause, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Doctor doctor = new Doctor();
            doctor.setDoctorId(cursor.getInt(0));
            doctor.setName(cursor.getString(1));
            doctor.setSpecializationId(cursor.getInt(2));
            doctor.setPracticeDuration(cursor.getInt(3));
            doctor.setClinicId(cursor.getInt(4));
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

    public List<Doctor> getDoctorsByClinicAndSpecialization(int clinicId, int specializationId){
        String whereclause = DbContract.DoctorEntry.COLUMN_NAME_CLINIC_ID + " = " + clinicId + " AND " + DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID + " = " + specializationId;
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
        values.put(DbContract.DoctorEntry.COLUMN_NAME_CLINIC_ID, doctor.getClinicId());

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

    public int getDoctorCount(){
        return getAllDoctors().size();
    }
    /*
        LOAD
        Load the initial values of the doctors
     */
    public void loadDoctors() {
        List<Doctor> temp= getAllDoctors();
        for (Doctor tmp : temp) {
            tmp.print();
        }
    }
    private void initializeDAO(){
        if (getAllDoctors().size()==0){
            insertDoctor(new Doctor("Dr. Stefan",1,2,1));
            insertDoctor(new Doctor("Dr. Zillion", 2, 3,1));
            insertDoctor(new Doctor("Dr. Deka", 3, 4,1));
            insertDoctor(new Doctor("Dr. Ankur", 1, 5,1));
            insertDoctor(new Doctor("Dr. Aristo", 4, 6,1));
            insertDoctor(new Doctor("Dr. Shreyas", 2, 4,1));

            insertDoctor(new Doctor("Dr. Raul",1,2,2));
            insertDoctor(new Doctor("Dr. John", 2, 3,2));
            insertDoctor(new Doctor("Dr. Alice", 3, 4,2));
            insertDoctor(new Doctor("Dr. Noopur", 1, 5,2));
            insertDoctor(new Doctor("Dr. Stuart", 4, 6,2));
            insertDoctor(new Doctor("Dr. Sanjana", 2, 4,2));

            insertDoctor(new Doctor("Dr. Siddharth",1,2,3));
            insertDoctor(new Doctor("Dr. Divesh", 2, 3,3));
            insertDoctor(new Doctor("Dr. Loh Wao", 3, 4,3));
            insertDoctor(new Doctor("Dr. Annie", 1, 5,3));
            insertDoctor(new Doctor("Dr. Andy", 4, 6,3));
            insertDoctor(new Doctor("Dr. Isabelle", 2, 4,3));

            insertDoctor(new Doctor("Dr. Joshua",1,2,4));
            insertDoctor(new Doctor("Dr. Kevin", 2, 3,4));
            insertDoctor(new Doctor("Dr. Mark", 3, 4,4));
            insertDoctor(new Doctor("Dr. Peter", 1, 5,4));
            insertDoctor(new Doctor("Dr. Arya", 4, 6,4));
            insertDoctor(new Doctor("Dr. Bingsheng", 2, 4,4));

            insertDoctor(new Doctor("Dr. Samantha",1,2,5));
            insertDoctor(new Doctor("Dr. Akash", 2, 3,5));
            insertDoctor(new Doctor("Dr. Sandeep", 3, 4,5));
            insertDoctor(new Doctor("Dr. Robert", 1, 5,5));
            insertDoctor(new Doctor("Dr. Limanto", 4, 6,5));
            insertDoctor(new Doctor("Dr. Chandra", 2, 4,5));

            insertDoctor(new Doctor("Dr. Ava",1,2,6));
            insertDoctor(new Doctor("Dr. Jessica", 2, 3,6));
            insertDoctor(new Doctor("Dr. Harvey", 3, 4,6));
            insertDoctor(new Doctor("Dr. Donna", 1, 5,6));
            insertDoctor(new Doctor("Dr. Rachael", 4, 6,6));
            insertDoctor(new Doctor("Dr. Dana", 2, 4,6));
        }
    }
}
