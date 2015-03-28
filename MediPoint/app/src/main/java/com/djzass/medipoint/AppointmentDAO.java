package com.djzass.medipoint;

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

    private static final String WHERE_ID_EQUALS = DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID
            + " =?";

    public AppointmentDAO(Context context) {
        super(context);
    }

    public long save(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.NAME_COLUMN, appointment.getName());

        return database.insert(DbHelper.DEPARTMENT_TABLE, null, values);
    }

    public long update(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.NAME_COLUMN, appointment.getName());

        long result = database.update(DbHelper.DEPARTMENT_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(department.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int deleteDept(Department department) {
        return database.delete(DataBaseHelper.DEPARTMENT_TABLE,
                WHERE_ID_EQUALS, new String[] { department.getId() + "" });
    }

    public ArrayList<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<Appointment>();
        Cursor cursor = database.query(DbContract.AppointmentEntry.TABLE_NAME,
                new String[] {DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME,
                        DbContract.AppointmentEntry.COLUMN_NAME_DOCTOR_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_END_TIME,
                        DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_START_TIME,
                        DbContract.AppointmentEntry.COLUMN_NAME_SPECIALTY_ID,
                        DbContract.AppointmentEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS
                        }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Appointment department = new Appointment();
            department.setId(cursor.getInt(0));
            department.setName(cursor.getString(1));
            departments.add(department);
        }
        return departments;
    }


    public void loadAppointments() {

        /*
        Appointment app1 = new Appointment();
        Appointment app2 = new Appointment();
        Appointment app3 = new Appointment();

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        appointments.add(app1);
        appointments.add(app2);
        appointments.add(app3);

        for (Appointment appt : appointments) {
            ContentValues values = new ContentValues();
            values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appt.getClinicId());
            values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appt.getClinicId());
            values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appt.getClinicId());
            values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appt.getClinicId());
            values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appt.getClinicId());
            values.put(DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID, appt.getClinicId());
            database.insert(DbContract.AppointmentEntry.TABLE_NAME, null, values);
        }*/
    }

}
