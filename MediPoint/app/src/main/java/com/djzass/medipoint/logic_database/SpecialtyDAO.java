package com.djzass.medipoint.logic_database;


/**
 * Created by Deka on 27/3/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.djzass.medipoint.DbContract;
import com.djzass.medipoint.entity.Specialty;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID
            + " =?";

    public SpecialtyDAO(Context context) throws SQLException {
        super(context);
        initializeDAO();
    }

    /* CREATE/SAVE
    Inserting specialty into specialt table and return the row id if insertion successful,
     otherwise -1 will be returned
     */
    public long insertSpecialty(Specialty specialty){
        ContentValues values = new ContentValues();
        //values.put(DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID, getSpecialtyCount());
        values.put(DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME, specialty.getName());

        return database.insert(DbContract.SpecialtyEntry.TABLE_NAME, null, values);
    }
    /** READ
     * Getting all specialties from the table
     * returns list of specialties
     * */
    public List<Specialty> getSpecialties(String whereclause) {
        List<Specialty> specialties = new ArrayList<Specialty>();

        Cursor cursor = database.query(DbContract.SpecialtyEntry.TABLE_NAME,
                new String[] { DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID,
                        DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME }, whereclause, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Specialty specialty= new Specialty();
            specialty.setId(cursor.getInt(0));
            specialty.setName(cursor.getString(1));
            specialties.add(specialty);
        }

        return specialties;
    }

    public List<Specialty> getAllSpecialties() {
        return getSpecialties(null);
    }

    public List<Specialty> getSpecialtiesByID(int id) {
        String whereclause = DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID + " = " + id;
        return getSpecialties(whereclause);
    }

    public String getSpecialtyNameByID(int specialtyId) {
        List<Specialty> templist =  getSpecialtiesByID(specialtyId);
        if (templist.size()>0)
            return templist.get(0).getName();
        else return "";
    }

    public List<Specialty> getSpecialtiesByName(String specialtyName){
        String whereClause = DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME + "=" + specialtyName;
        return getSpecialties(whereClause);
    }

    /*  UPDATE
        returns the number of rows affected by the update
     */
    public long update(Specialty specialty) {
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
        List<Specialty> temp= getAllSpecialties();
        for (Specialty tmp : temp) {
            tmp.print();
        }
    }

    public int getSpecialtyCount(){
        return getAllSpecialties().size();
    }

    private void initializeDAO(){
        if (getSpecialtyCount()==0){
            insertSpecialty(new Specialty("ENT")); //0
            insertSpecialty(new Specialty("Dental Services")); //1
            insertSpecialty(new Specialty("Women's Health")); //2
            insertSpecialty(new Specialty("General Medicine")); //3
        }
    }
}
