package com.djzass.medipoint.logic_database;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.lang.String;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.djzass.medipoint.Container;
import com.djzass.medipoint.DbContract;
import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.entity.Appointment;

/**
 * Created by Deka on 26/3/2015.
 */
public class AppointmentDAO extends DbDAO{
    public static final String CLINIC_WITH_PREFIX = "clinic.";
    public static final String APPOINTMENT_WITH_PREFIX = "appointment.";
    public static final String DOCTOR_WITH_PREFIX = "doctor.";
    public static final String SPECIALTY_WITH_PREFIX = "specialty.";
    public static final String SERVICE_WITH_PREFIX = "service.";
    private static final String WHERE_ID_EQUALS = DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID
            + " =?";

    public AppointmentDAO(Context context) throws SQLException {
        super(context);
        initializeDAO();
    }

    public long insertAppointment(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID, getAppointmentCount());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appointment.getClinicId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID, appointment.getPatientId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DOCTOR_ID, appointment.getDoctorId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME, String.valueOf(appointment.getDate()));
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID, appointment.getService());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SPECIALTY_ID,appointment.getSpecialtyId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS, appointment.getPreAppointmentActions());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_START_TIME, appointment.getTimeframe().getStartTime());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_END_TIME, appointment.getTimeframe().getEndTime());

        return database.insert(DbContract.AppointmentEntry.TABLE_NAME, null, values);
    }


    /* READ
     * Getting all appointments from the table using whereclause as where clause
     * Null means get all
     * */
    public List<Appointment> getAppointments(String whereclause) {
        List<Appointment> appointments = new ArrayList<Appointment>();

        Cursor cursor = database.query(DbContract.AppointmentEntry.TABLE_NAME,
                new String[] {DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_DOCTOR_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME,
                        DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_SPECIALTY_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS,
                        DbContract.AppointmentEntry.COLUMN_NAME_START_TIME,
                        DbContract.AppointmentEntry.COLUMN_NAME_END_TIME
                         }, whereclause, null, null, null, null);

        while (cursor.moveToNext()) {
            Appointment appointment= new Appointment();
            appointment.setClinicId(cursor.getInt(0));
            appointment.setPatientId(cursor.getInt(1));
            appointment.setDoctorId(cursor.getInt(2));

            String temp = cursor.getString(3);
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal  = Calendar.getInstance();
            try {
                cal.setTime(dateformat.parse(temp));
            } catch (ParseException e) {
                Log.d("DAO", "Date parsing exception");
            }
            appointment.setDate(cal);
            appointment.setService(cursor.getInt(4));
            appointment.setSpecialtyId(cursor.getInt(5));
            appointment.setPreAppointmentActions(cursor.getString(6));
            Timeframe timeframe= new Timeframe(cursor.getInt(7),cursor.getInt(8));
            appointment.setTimeframe(timeframe);
            appointments.add(appointment);
        }
        return appointments;
    }


    public List<Appointment> getAllAppointments() {
        return getAppointments(null);
    }

    public List<Appointment> getAppointmentsByID(int id) {
        String whereclause = DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID + " = " + id;
        return getAppointments(whereclause);
    }
    public List<Appointment> getAppointmentsByPatientID(int pid) {
        String whereclause = DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID + " = " + pid;
        return getAppointments(whereclause);
    }
    public List<Appointment> getAppointmentsByDoctorID(int did) {
        String whereclause = DbContract.AppointmentEntry.COLUMN_NAME_DOCTOR_ID + " = " + did;
        return getAppointments(whereclause);
    }
    /*
        UPDATE
        returns the number of rows affected by the update
     */
    public long update(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appointment.getClinicId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID, appointment.getPatientId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DOCTOR_ID, appointment.getDoctorId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME, String.valueOf(appointment.getDate()));
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID, appointment.getService());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SPECIALTY_ID,appointment.getSpecialtyId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS, appointment.getPreAppointmentActions());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_START_TIME, appointment.getTimeframe().getStartTime());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_END_TIME, appointment.getTimeframe().getEndTime());


        long result = database.update(DbContract.AppointmentEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(appointment.getId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deleteAppointment(Appointment appointment) {
        return database.delete(DbContract.AppointmentEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { appointment.getId() + "" });
    }
    /*
        LOAD
        Load the initial values of the appointments ??
     */
    public void loadAppointments() {
        List<Appointment> temp= getAllAppointments();
        for (Appointment tmp : temp) {
            tmp.print();
        }
    }

    public int getAppointmentCount(){
        return getAllAppointments().size();
    }
    private void initializeDAO(){
        if (getAllAppointments().size()==0){
        }
    }
}