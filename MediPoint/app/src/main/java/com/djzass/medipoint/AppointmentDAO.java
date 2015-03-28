package com.djzass.medipoint;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
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
    }

    public long insertAppointment(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appointment.getClinic().getId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID, appointment.getPatient().getPID());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DOCTOR_ID, appointment.getDoctor().getDID());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME, String.valueOf(appointment.getDate()));
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID, appointment.getService().getId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SPECIALTY_ID,appointment.getSpecialty().getId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS, appointment.getPreAppointmentActions());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_START_TIME, appointment.getTimeframe().getStartTime());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_END_TIME, appointment.getTimeframe().getEndTime());

        return database.insert(DbContract.AppointmentEntry.TABLE_NAME, null, values);
    }


    /*
        READ
      * Getting all appointments from the table
     * returns list of appointments
     * */
    public List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<Appointment>();

        //MUST JOIN
        // Select all rows
        // String selectQuery = "SELECT  * FROM " + DbContract.AppointmentEntry.TABLE_NAME;
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
                         }, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Appointment appointment= new Appointment();

            appointments.add(appointment);
        }

        return appointments;
    }

    /*
        FETCH BY ID
     */
    //READ SINGLE ROW
    //MUST JOIN
    public Appointment getAppointmentById(long appointmentId) {
        String selectQuery = "SELECT  * FROM " + DbContract.AppointmentEntry.TABLE_NAME + " WHERE "
                + DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID + " = " + appointmentId;

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        // Create the class object, then set the attribute from content of the exisiting data in the table
        Appointment appointment = new Appointment();
        appointment.setId(c.getInt(c.getColumnIndex(DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID)));


        return appointment;
    }
    /*
        UPDATE
       returns the number of rows affected by the update
     */
    public long update(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appointment.getClinic().getId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID, appointment.getPatient().getPID());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DOCTOR_ID, appointment.getDoctor().getDID());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME, String.valueOf(appointment.getDate()));
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID, appointment.getService().getId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_SPECIALTY_ID,appointment.getSpecialty().getId());
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
        Load the initial values of the appointments
     */
    public void loadAppointments() {
        Appointment a1 = new Appointment();
        Appointment a2 = new Appointment();
        Appointment a3 = new Appointment();

        List<Appointment> appointments = new ArrayList<Appointment>();
        appointments.add(a1);
        appointments.add(a2);
        appointments.add(a3);
        for (Appointment appt: appointments) {
            insertAppointment(appt);
        }
    }

}
