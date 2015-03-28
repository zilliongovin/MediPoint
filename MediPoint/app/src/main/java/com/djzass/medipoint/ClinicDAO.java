package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
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
        values.put(DbContract.ClinicEntry.COLUMN_NAME_COUNTRY_ID, clinic.getCountry().getName());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_ZIPCODE, clinic.getZipCode());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_TEL_NUMBER, clinic.getTelNumber());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_FAX_NUMBER, clinic.getFaxNumber());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_EMAIL, clinic.getEmail());

        // Inserting Row
        return database.insert(DbContract.ClinicEntry.TABLE_NAME, null, values);
    }

    /*  READ
     */
    public List<Clinic> getClinics() {
        List<Clinic> clinics = new ArrayList<Clinic>();

        /* Select All rows
        //String selectQuery = "SELECT  * FROM " + DbContract.ClinicEntry.TABLE_NAME;
        Cursor cursor = database.query(DbContract.ClinicEntry.TABLE_NAME,
                new String[] { DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID,
                        DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME,
                        DbContract.ClinicEntry.COLUMN_NAME_ADDRESS,
                        DbContract.ClinicEntry.COLUMN_NAME_COUNTRY_ID,
                        DbContract.ClinicEntry.COLUMN_NAME_ZIPCODE,
                        DbContract.ClinicEntry.COLUMN_NAME_TEL_NUMBER,
                        DbContract.ClinicEntry.COLUMN_NAME_FAX_NUMBER,
                        DbContract.ClinicEntry.COLUMN_NAME_EMAIL
                }, null, null, null, null,
                null);
        //Havent finish yet*/

        String query = "Select * FROM " + DbContract.ClinicEntry.TABLE_NAME + ", " + DbContract.CountryEntry.TABLE_NAME
                + " WHERE " + CLINIC_PREFIX + DbContract.ClinicEntry.COLUMN_NAME_COUNTRY_ID + " = " +
                COUNTRY_PREFIX + DbContract.CountryEntry.COLUMN_NAME_COUNTRY_ID;

        Log.d("query", query);
        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Clinic clinic= new Clinic();
            clinic.setId(cursor.getInt(0));
            clinic.setName(cursor.getString(1));
            clinic.setAddress(cursor.getString(2));

            //Country country = new Country();
            //country.setId(cursor.getInt(3));
            //clinic.setCountry(country);

            //clinic.setZipCode(cursor.getString(4));
            //clinic.setTelNumber(cursor.getString(5));
            //clinic.setFaxNumber(cursor.getString(6));
            clinic.setEmail(cursor.getString(7));
            clinics.add(clinic);
        }

        return clinics;
    }

    //READ SINGLE ROW
    public Clinic getClinic(long clinicId) {
        //String selectQuery = "SELECT  * FROM " + DbContract.ClinicEntry.TABLE_NAME + " WHERE "
        //        + DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID + " = " + clinicId;
        //Cursor c = database.rawQuery(selectQuery, null);
        //Log.e(LOG, selectQuery);


        Cursor c = database.query(DbContract.ClinicEntry.TABLE_NAME, new String[]{
                DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID,
                DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME,
                DbContract.ClinicEntry.COLUMN_NAME_ADDRESS,
                DbContract.ClinicEntry.COLUMN_NAME_COUNTRY_ID,
                DbContract.ClinicEntry.COLUMN_NAME_ZIPCODE,
                DbContract.ClinicEntry.COLUMN_NAME_TEL_NUMBER,
                DbContract.ClinicEntry.COLUMN_NAME_FAX_NUMBER,
                DbContract.ClinicEntry.COLUMN_NAME_EMAIL
        },DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID + " = ?",new String[]{
                clinicId+""},null, null, null  );

        if (c != null)
            c.moveToFirst();

        // Create the class object, then set the attribute from content of the exisiting data in the table
        Clinic clinic = new Clinic();
        clinic.setId(c.getInt(c.getColumnIndex(DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID)));
        clinic.setName(c.getString(c.getColumnIndex(DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID)));
        clinic.setAddress(c.getString(c.getColumnIndex(DbContract.ClinicEntry.COLUMN_NAME_ADDRESS)));
        //clinic.setCountry(c.getString(countryDao.getCountryById(c.getColumnIndex(DbContract.ClinicEntry.COLUMN_NAME_COUNTRY_ID))));
        //Country country = new Country();
        //country.setName();


        //clinic.setZipCode(c.getString(c.getColumnIndex(DbContract.ClinicEntry.COLUMN_NAME_ZIPCODE)));
        //clinic.setTelNumber(c.getString(c.getColumnIndex(DbContract.ClinicEntry.COLUMN_NAME_TEL_NUMBER)));
        //clinic.setFaxNumber(c.getString(c.getColumnIndex(DbContract.ClinicEntry.COLUMN_NAME_FAX_NUMBER)));
        clinic.setEmail(c.getString(c.getColumnIndex(DbContract.ClinicEntry.COLUMN_NAME_EMAIL)));

        return clinic;
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
        Clinic c1 = new Clinic();
        Clinic c2 = new Clinic();
        Clinic c3 = new Clinic();

        List<Clinic> clinics = new ArrayList<Clinic>();
        clinics.add(c1);
        clinics.add(c2);
        clinics.add(c3);
        for (Clinic c: clinics) {
            insertClinic(c);
        }
    }
}
