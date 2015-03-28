package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends DbDAO {
    private static final String WHERE_ID_EQUALS = DbContract.CountryEntry.COLUMN_NAME_COUNTRY_ID
            + " =?";

    public CountryDAO(Context context) throws SQLException {
        super(context);
    }

    /* CREATE/SAVE
    Inserting country into countries table and return the row id if insertion successful,
     otherwise -1 will be returned
     */
    public long insertCountry(Country country){
        ContentValues values = new ContentValues();
        values.put(DbContract.CountryEntry.COLUMN_NAME_COUNTRY_NAME, country.getName());
          
        // Inserting Row
        return database.insert(DbContract.CountryEntry.TABLE_NAME, null, values);
    }
     
    /** READ
     * Getting all countries from the table
     * returns list of countries
     * */
    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<Country>();

        // Select all rows
        // String selectQuery = "SELECT  * FROM " + DbContract.CountryEntry.TABLE_NAME;
        Cursor cursor = database.query(DbContract.CountryEntry.TABLE_NAME,
                new String[] { DbContract.CountryEntry.COLUMN_NAME_COUNTRY_ID,
                        DbContract.CountryEntry.COLUMN_NAME_COUNTRY_NAME }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            //Country country= new Country();
            //country.setId(cursor.getInt(0));
            //country.setName(cursor.getString(1));
            //countries.add(country);
        }

        return countries;
    }
    //READ SINGLE ROW
    public Country getCountryById(long countryId) {
        String selectQuery = "SELECT  * FROM " + DbContract.CountryEntry.TABLE_NAME + " WHERE "
                + DbContract.CountryEntry.COLUMN_NAME_COUNTRY_ID + " = " + countryId;

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        // Create the class object, then set the attribute from content of the exisiting data in the table
        Country country = new Country();
        country.setId(c.getInt(c.getColumnIndex(DbContract.CountryEntry.COLUMN_NAME_COUNTRY_ID)));
        country.setName(c.getString(c.getColumnIndex(DbContract.CountryEntry.COLUMN_NAME_COUNTRY_NAME)));

        return country;
    }

    /*  UPDATE
        returns the number of rows affected by the update
     */
    public long update(Country country) {
        ContentValues values = new ContentValues();
        values.put(DbContract.CountryEntry.COLUMN_NAME_COUNTRY_NAME, country.getName());

        long result = database.update(DbContract.CountryEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(country.getId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deleteCountry(Country country) {
        return database.delete(DbContract.CountryEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { country.getId() + "" });
    }
    /*
        LOAD
        Load the initial values of the countries
     */

    public void loadCountries() {
        Country c1 = new Country("Singapore");
        Country c2 = new Country("Malaysia");
        Country c3 = new Country("Thailand");

        List<Country> countries = new ArrayList<Country>();
        countries.add(c1);
        countries.add(c2);
        countries.add(c3);
        for (Country c: countries) {
            insertCountry(c);
        }
    }

}