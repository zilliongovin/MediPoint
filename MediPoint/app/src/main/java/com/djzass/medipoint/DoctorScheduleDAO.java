package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deka on 28/3/2015.
 */
public class DoctorScheduleDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_SCHEDULE_ID
            + " =?";

    public DoctorScheduleDAO(Context context) {
        super(context);
    }

    /*
        CREATE
         Inserting doctor schedule into doctor schedules table and return the row id if insertion successful,
     otherwise -1 will be returned
     */
    public long insertDoctorSchedule(DoctorSchedule doctorSchedule){
        ContentValues values = new ContentValues();
<<<<<<< HEAD
<<<<<<< HEAD
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID, doctorSchedule.getDoctor().getId());
=======
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID, doctorSchedule.getDoctor().getDID());
>>>>>>> origin/master
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID, doctorSchedule.getClinic().getId());
=======
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID, doctorSchedule.getDoctorId().getDID());
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID, doctorSchedule.getClinicId().getId());
>>>>>>> 49e9b696f1b6c1c2563389694bac34700083f3c5
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DAY, doctorSchedule.getDay());
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_START_TIME, doctorSchedule.getTimeframe().getStartTime());
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_END_TIME, doctorSchedule.getTimeframe().getEndTime());

        // Inserting Row
        return database.insert(DbContract.DoctorScheduleEntry.TABLE_NAME, null, values);
    }

    /*
        READ
      * Getting all doctorSchedules from the table
     * returns list of doctorSchedules
     * */
    public List<DoctorSchedule> getDoctorSchedules() {
        List<DoctorSchedule> doctorSchedules = new ArrayList<DoctorSchedule>();


        //MUST JOIN THE TABLES TOGETHER

        // Select all rows
        // String selectQuery = "SELECT  * FROM " + DbContract.DoctorScheduleEntry.TABLE_NAME;
        Cursor cursor = database.query(DbContract.DoctorScheduleEntry.TABLE_NAME,
                new String[] { DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_SCHEDULE_ID,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_DAY,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_START_TIME,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_END_TIME}, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            DoctorSchedule doctorSchedule= new DoctorSchedule();
            doctorSchedule.setId(cursor.getInt(0));
            Clinic clinic = new Clinic();
            doctorSchedule.setClinicId(clinic);
            Doctor doctor = new Doctor();
            doctorSchedule.setDoctorId(doctor);
            doctorSchedule.setDay(cursor.getString(3));
            doctorSchedule.setTimeframe(new Timeframe(cursor.getInt(4), cursor.getInt(5)));
            doctorSchedules.add(doctorSchedule);
        }

        return doctorSchedules;
    }

    /*
        FETCH BY ID
     */
    //READ SINGLE ROW
    public DoctorSchedule getDoctorScheduleById(long doctorScheduleId) {
        String selectQuery = "SELECT  * FROM " + DbContract.DoctorScheduleEntry.TABLE_NAME + " WHERE "
                + DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_SCHEDULE_ID + " = " + doctorScheduleId;

        Cursor c = database.rawQuery(selectQuery, null);

        //MUST JOIN THE TABLES TOGETHER

        if (c != null)
            c.moveToFirst();

        // Create the class object, then set the attribute from content of the exisiting data in the table
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        //doctorSchedule.setId(c.getInt(c.getColumnIndex(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_SCHEDULE_ID)));
        //doctorSchedule.setName(c.getString(c.getColumnIndex(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_SCHEDULE_NAME)));

        return doctorSchedule;
    }
    /*
        UPDATE
       returns the number of rows affected by the update
     */
<<<<<<< HEAD
    public int update(DoctorSchedule doctorSchedule) {
        ContentValues values = new ContentValues();
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID, doctorSchedule.getDoctor().getId());
=======
    public long update(DoctorSchedule doctorSchedule) {
        ContentValues values = new ContentValues();
<<<<<<< HEAD
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID, doctorSchedule.getDoctor().getDID());
>>>>>>> origin/master
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID, doctorSchedule.getClinic().getId());
=======
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID, doctorSchedule.getDoctorId().getDID());
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID, doctorSchedule.getClinicId().getId());
>>>>>>> 49e9b696f1b6c1c2563389694bac34700083f3c5
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DAY, doctorSchedule.getDay());
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_START_TIME, doctorSchedule.getTimeframe().getStartTime());
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_END_TIME, doctorSchedule.getTimeframe().getEndTime());

        long result = database.update(DbContract.DoctorScheduleEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(doctorSchedule.getId()) });

        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deleteDoctorSchedule(DoctorSchedule doctorSchedule) {
        return database.delete(DbContract.DoctorScheduleEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { doctorSchedule.getId() + "" });
    }
    /*
        LOAD
        Load the initial values of the doctorSchedules
     */
    public void loadDoctorSchedules() {
        DoctorSchedule dS1 = new DoctorSchedule();
        DoctorSchedule dS2 = new DoctorSchedule();
        DoctorSchedule dS3 = new DoctorSchedule();

        List<DoctorSchedule> doctorSchedules = new ArrayList<DoctorSchedule>();
        doctorSchedules.add(dS1);
        doctorSchedules.add(dS2);
        doctorSchedules.add(dS3);
        for (DoctorSchedule dS: doctorSchedules) {
<<<<<<< HEAD
            database.insert(dS);
=======
            insertDoctorSchedule(dS);
>>>>>>> origin/master
        }
    }
}
