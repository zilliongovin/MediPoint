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
public class DoctorScheduleDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_SCHEDULE_ID
            + " =?";

    public DoctorScheduleDAO(Context context) throws SQLException {
        super(context);
    }

    /*
        CREATE
         Inserting doctor schedule into doctor schedules table and return the row id if insertion successful,
     otherwise -1 will be returned
     */
    public long insertDoctorSchedule(DoctorSchedule doctorSchedule){
        ContentValues values = new ContentValues();
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID, doctorSchedule.getDoctorId());
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID, doctorSchedule.getClinicId());
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
    public List<DoctorSchedule> getDoctorSchedules(String whereclause) {
        List<DoctorSchedule> doctorSchedules = new ArrayList<DoctorSchedule>();

        Cursor cursor = database.query(DbContract.DoctorScheduleEntry.TABLE_NAME,
                new String[] { DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_SCHEDULE_ID,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_DAY,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_START_TIME,
                        DbContract.DoctorScheduleEntry.COLUMN_NAME_END_TIME}, whereclause, null, null, null,
                null);

        while (cursor.moveToNext()) {
            DoctorSchedule doctorSchedule= new DoctorSchedule();
            doctorSchedule.setId(cursor.getInt(0));
            doctorSchedule.setDoctorId(cursor.getInt(1));
            doctorSchedule.setClinicId(cursor.getInt(2));
            doctorSchedule.setDay(cursor.getString(3));
            doctorSchedule.setTimeframe(new Timeframe(cursor.getInt(4), cursor.getInt(5)));
            doctorSchedules.add(doctorSchedule);
        }

        return doctorSchedules;
    }

    public List<DoctorSchedule> getAllDoctorSchedules() {
        return getDoctorSchedules(null);
    }

    public List<DoctorSchedule> getDoctorSchedulesByID(int id) {
        String whereclause = DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_SCHEDULE_ID + " = " + id;
        return getDoctorSchedules(whereclause);
    }

    public List<DoctorSchedule> getDoctorSchedulesByDoctorID(int doctorId) {
        String whereclause = DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID + " = " + doctorId;
        return getDoctorSchedules(whereclause);
    }

    public List<DoctorSchedule> getDoctorSchedulesByClinicID(int clinicId) {
        String whereclause = DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID + " = " + clinicId;
        return getDoctorSchedules(whereclause);
    }
    /*
        UPDATE
       returns the number of rows affected by the update
     */
    public long update(DoctorSchedule doctorSchedule) {
        ContentValues values = new ContentValues();
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID, doctorSchedule.getDoctorId());
        values.put(DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID, doctorSchedule.getClinicId());
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
            insertDoctorSchedule(dS);
        }
    }
}
