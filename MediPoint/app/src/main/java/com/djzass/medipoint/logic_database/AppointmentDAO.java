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
import android.widget.Toast;

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
        //initializeDAO();
    }

    public long insertAppointment(Appointment appointment) {
        ContentValues values = new ContentValues();
        //values.put(DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID, getAppointmentCount());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appointment.getClinicId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID, appointment.getPatientId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DOCTOR_ID, appointment.getDoctorId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_REFERRER_ID, appointment.getReferrerId());
       // values.put(DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME, appointment.getDateString());
       Log.d("CalendarCreateC",appointment.getDate().toString());

        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME, appointment.getDate().getTimeInMillis());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID, appointment.getServiceId());
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
                        DbContract.AppointmentEntry.COLUMN_NAME_REFERRER_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME,
                        DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_SPECIALTY_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS,
                        DbContract.AppointmentEntry.COLUMN_NAME_START_TIME,
                        DbContract.AppointmentEntry.COLUMN_NAME_END_TIME
                         }, whereclause, null, null, null, null);

        while (cursor.moveToNext()) {
            Appointment appointment= new Appointment();
            appointment.setId(cursor.getInt(0));
            appointment.setClinicId(cursor.getInt(1));
            appointment.setPatientId(cursor.getInt(2));
            appointment.setDoctorId(cursor.getInt(3));
            appointment.setReferrerId(cursor.getInt(4));

            /*String temp = cursor.getString(5);
            DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
            Calendar cal  = Calendar.getInstance();
            try {
                cal.setTime(dateformat.parse(temp));
            } catch (ParseException e) {
                Log.d("AppointmentDAO", "Date parsing exception");
            }*/
            Calendar cal = Calendar.getInstance();
            Long c = cursor.getLong(5);
            cal.setTimeInMillis(c);
            Log.d("CalendarGetDAO",cal.toString());

            appointment.setDate(cal);
            appointment.setServiceId(cursor.getInt(6));
            appointment.setSpecialtyId(cursor.getInt(7));
            appointment.setPreAppointmentActions(cursor.getString(8));
            Timeframe timeframe= new Timeframe(cursor.getInt(9),cursor.getInt(10));
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
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_REFERRER_ID, appointment.getReferrerId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME, appointment.getDate().getTimeInMillis());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID, appointment.getServiceId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SPECIALTY_ID,appointment.getSpecialtyId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS, appointment.getPreAppointmentActions());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_START_TIME, appointment.getTimeframe().getStartTime());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_END_TIME, appointment.getTimeframe().getEndTime());


        long result = database.update(DbContract.AppointmentEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(appointment.getId()) });

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
            tmp.toString();
        }
    }

    public int getAppointmentCount(){
        return getAllAppointments().size();
    }
    private void initializeDAO(){
        if (getAllAppointments().size()==0){
        }
    }

    public String getStringFromID(String tableName,String columnName,String columnID,int id){
       /* String query = "SELECT " + columnName +
                " FROM " + tableName +
                " WHERE " + columnID + "=?";
        String[] selArgs = {""+id};
        Cursor cursor = database.rawQuery(query,selArgs);*/
        String query = "SELECT " + tableName + "." + columnName +
                " FROM " + tableName +
                " WHERE " + columnID + " = " + id;
        Cursor cursor = database.rawQuery(query, null);
        if(cursor!=null && cursor.moveToFirst())
            return cursor.getString(0);
        Log.d("numOfRows", cursor.getCount()+"");
        if(cursor==null)
            Log.d("NullCursorApptdao", "Null Cursor");
        if(!cursor.moveToFirst())
            Log.d("MoveTFApptDAo", "Move To first fail");
        return "Cursor not found";
    }

}