package com.djzass.medipoint;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Deka on 25/3/2015.
 */

public class DatabaseCreator extends Activity {

    SQLiteDatabase mydb;
    private   static String DBNAME = "TEST.db";
    private static String TABLE = "APPOINTMENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        createTable();
        insertIntoTable();

        ArrayList<String> countries = new ArrayList<String>();
        countries.add("Singapore");
        countries.add("Malaysia");
        countries.add("Thailand");
        //my_array = getTableValues();

        Spinner country_spinner = (Spinner) findViewById(R.id.CreateApptCountries);
        ArrayAdapter adapter_countries = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,
                countries);
        country_spinner.setAdapter(adapter_countries);
    }

    // CREATE TABLE IF NOT EXISTS
    public void createTable() {
        try {
            mydb = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("CREATE TABLE IF  NOT EXISTS " + TABLE
                    + " (ID INTEGER PRIMARY KEY, NAME TEXT, PLACE TEXT);");
            mydb.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in creating table",
                    Toast.LENGTH_LONG);
        }
    }

    // THIS FUNCTION INSERTS DATA TO THE DATABASE
    public void insertIntoTable() {
        try {
            mydb = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("INSERT INTO " + TABLE
                    + "(NAME, PLACE) VALUES('CODERZHEAVEN','GREAT INDIA')");
            mydb.execSQL("INSERT INTO " + TABLE
                    + "(NAME, PLACE) VALUES('ANTHONY','USA')");
            mydb.execSQL("INSERT INTO " + TABLE
                    + "(NAME, PLACE) VALUES('SHUING','JAPAN')");
            mydb.execSQL("INSERT INTO " + TABLE
                    + "(NAME, PLACE) VALUES('JAMES','INDIA')");
            mydb.execSQL("INSERT INTO " + TABLE
                    + "(NAME, PLACE) VALUES('SOORYA','INDIA')");
            mydb.execSQL("INSERT INTO " + TABLE
                    + "(NAME, PLACE) VALUES('MALIK','INDIA')");
            mydb.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Error in inserting into table", Toast.LENGTH_LONG);
        }
    }

    // THIS FUNCTION SHOWS DATA FROM THE DATABASE
    public ArrayList<String> getTableValues() {

        ArrayList<String> my_array = new ArrayList<String>();
        try {
            mydb = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            Cursor allrows = mydb.rawQuery("SELECT * FROM " + TABLE, null);
            System.out.println("COUNT : " + allrows.getCount());

            if (allrows.moveToFirst()) {
                do {

                    String ID = allrows.getString(0);
                    String NAME = allrows.getString(1);
                    String PLACE = allrows.getString(2);
                    my_array.add(NAME);

                } while (allrows.moveToNext());
            }
            allrows.close();
            mydb.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }
        return my_array;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
