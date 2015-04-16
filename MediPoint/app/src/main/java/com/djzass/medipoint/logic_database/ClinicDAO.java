package com.djzass.medipoint.logic_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.djzass.medipoint.entity.Clinic;

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
        initializeDAO();
    }

    /*  CREATE
     */
    public long insertClinic(Clinic clinic){
        ContentValues values = new ContentValues();
        //values.put(DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID, getClinicCount());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME, clinic.getName());
        values.put(DbContract.ClinicEntry.COLUMN_NAME_ADDRESS, clinic.getAddress());
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
        String whereclause = DbContract.ClinicEntry.COLUMN_NAME_COUNTRY + " = " + "\"" +country + "\"";
        return getClinics(whereclause);
    }

    public List<Clinic> getClinicsByName(String name) {
        String whereclause = DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME + " = " + "\"" +name + "\"";
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



    public int getClinicCount(){
        return getAllClinics().size();
    }

    private void initializeDAO(){
        if (getAllClinics().size()==0){
          insertClinic(new Clinic("DjZass Boonlay Care", "Boonlay Way 123", "Singapore", 612345, 655512, 655513, "djzass.boonlay@medipoint.com"));
          insertClinic(new Clinic("DjZass Changi Clinic", "Changi Street 321", "Singapore", 654321, 655521, 655531, "djzass.changi@medipoint.com"));
          insertClinic(new Clinic("DjZass Kuala Lumpur Care", "Syah Ali Road 123", "Malaysia", 712345, 755512, 755513, "djzass.kl@medipoint.com"));
          insertClinic(new Clinic("DjZass Johor Baru Clinic", "Jalan Syed Baro 321", "Malaysia", 754321, 755521, 755531, "djzass.jb@medipoint.com"));
          insertClinic(new Clinic("DjZass Bangkok Care", "Bangkok St. 123", "Thailand", 812345, 855512, 855513, "djzass.bangkok@medipoint.com"));
          insertClinic(new Clinic("DjZass Krabi Clinic", "City Square 321", "Thailand", 854321, 855521, 855531, "djzass.krabi@medipoint.com"));
        }
    }
}
