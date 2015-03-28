package com.djzass.medipoint;


/**
 * Created by Deka on 27/3/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID
            + " =?";

    public SpecialtyDAO(Context context) {
        super(context);
    }

    /* CREATE/SAVE
    Inserting specialty into specialt table and return the row id if insertion successful,
     otherwise -1 will be returned
     */
    public long insertSpecialty(Specialty specialty){
        ContentValues values = new ContentValues();
        values.put(DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME, specialty.getName());

        // Inserting Row
        return database.insert(DbContract.SpecialtyEntry.TABLE_NAME, null, values);
    }
    /** READ
     * Getting all specialties from the table
     * returns list of specialties
     * */
    public List<Specialty> getCountries() {
        List<Specialty> specialties = new ArrayList<Specialty>();

        // Select all rows
        // String selectQuery = "SELECT  * FROM " + DbContract.SpecialtyEntry.TABLE_NAME;
        Cursor cursor = database.query(DbContract.SpecialtyEntry.TABLE_NAME,
                new String[] { DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID,
                        DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Specialty specialty= new Specialty();
            specialty.setId(cursor.getInt(0));
            specialty.setName(cursor.getString(1));
            specialties.add(specialty);
        }

        return specialties;
    }
    //READ SINGLE ROW
    public Specialty getSpecialtyById(int specialtyId) {
        String selectQuery = "SELECT  * FROM " + DbContract.SpecialtyEntry.TABLE_NAME + " WHERE "
                + DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID + " = " + specialtyId;

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        // Create the class object, then set the attribute from content of the exisiting data in the table
        Specialty specialty = new Specialty();
        specialty.setId(c.getInt(c.getColumnIndex(DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID)));
        specialty.setName(c.getString(c.getColumnIndex(DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME)));

        return specialty;
    }

    /*  UPDATE
        returns the number of rows affected by the update
     */
    public int update(Specialty specialty) {
        ContentValues values = new ContentValues();
        values.put(DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME, specialty.getName());

        long result = database.update(DbContract.SpecialtyEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(specialty.getId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deleteSpecialty(Specialty specialty) {
        return database.delete(DbContract.SpecialtyEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { specialty.getId() + "" });
    }
    /*
        LOAD
        load the initial values of the specialties
     */
    public void loadSpecialties() {
        Specialty c1 = new Specialty("ENT");
        Specialty c2 = new Specialty("Women Health Services");
        Specialty c3 = new Specialty("Dental");

        List<Specialty> specialties = new ArrayList<Specialty>();
        specialties.add(c1);
        specialties.add(c2);
        specialties.add(c3);
        for (Specialty c: specialties) {
            ContentValues values = new ContentValues();
            values.put(DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME, c.getName());
            database.insert(DbContract.SpecialtyEntry.TABLE_NAME, null, values);
        }
    }
}
