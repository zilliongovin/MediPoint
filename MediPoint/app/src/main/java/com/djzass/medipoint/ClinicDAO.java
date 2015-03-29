package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deka on 27/3/2015.
 */
public class ClinicDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID
            + " =?";
    public static final String CLINIC_PREFIX = "clinic.";
    public static final String COUNTRY_PREFIX = "country.";

    public ClinicDAO(Context context) throws SQLException {
        super(context);
    }

    /*  CREATE
     */
    public long insertClinic(Clinic clinic){
        ContentValues values = new ContentValues();
        values.put(DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME, clinic.getName());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_COUNTRY, clinic.getCountry());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_ZIPCODE, clinic.getZipCode());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_TEL_NUMBER, clinic.getTelNumber());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_FAX_NUMBER, clinic.getFaxNumber());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_EMAIL, clinic.getEmail());

        // Inserting Row
        return database.insert(DbContract.ClinicEntry.TABLE_NAME, null, values);
    }

    /*  READ
     */
    public List<Clinic> getClinics(String whereclause) {
        List<Clinic> clinics = new ArrayList<Clinic>();

        Cursor cursor = database.query(DbContract.ClinicEntry.TABLE_NAME,
                new String[] { DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID,
                        DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME,
                        DbContract.ClinicEntry.COLUMN_NAME_ADDRESS,
                        DbContract.ClinicEntry.COLUMN_NAME_COUNTRY,
                        DbContract.ClinicEntry.COLUMN_NAME_ZIPCODE,
                        DbContract.ClinicEntry.COLUMN_NAME_TEL_NUMBER,
                        DbContract.ClinicEntry.COLUMN_NAME_FAX_NUMBER,
                        DbContract.ClinicEntry.COLUMN_NAME_EMAIL
                }, whereclause, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Clinic clinic= new Clinic();
            clinic.setId(cursor.getInt(0));
            clinic.setName(cursor.getString(1));
            clinic.setAddress(cursor.getString(2));
            clinic.setCountry(cursor.getString(3));
            clinic.setZipCode(cursor.getInt(4));
            clinic.setTelNumber(cursor.getInt(5));
            clinic.setFaxNumber(cursor.getInt(6));
            clinic.setEmail(cursor.getString(7));
            clinics.add(clinic);
        }

        return clinics;
    }

    public List<Clinic> getAllClinics() {
        return getClinics(null);
    }

    public List<Clinic> getClinicsByID(int id) {
        String whereclause = DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID + " = " + id;
        return getClinics(whereclause);
    }

    public List<Clinic> getClinicsByCountry(String country) {
        String whereclause = DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID + " = " + country;
        return getClinics(whereclause);
    }
    /*
        UPDATE
        returns the number of rows affected by the update
     */
    public long update(Clinic clinic) {
        ContentValues values = new ContentValues();
        values.put(DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME, clinic.getName());

        long result = database.update(DbContract.ClinicEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(clinic.getId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
    */
    public int deleteClinic(Clinic clinic) {
        return database.delete(DbContract.ClinicEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { clinic.getId() + "" });
    }

    /*
        LOAD
    */
    public void loadClinics() {
        List<Clinic> temp= getAllClinics();
        for (Clinic tmp : temp) {
            tmp.print();
        }
    }
}
