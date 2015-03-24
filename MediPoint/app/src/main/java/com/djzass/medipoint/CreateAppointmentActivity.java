package com.djzass.medipoint;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Calendar;

public class CreateAppointmentActivity extends Activity implements AdapterView.OnItemSelectedListener{

    //spinner
    Spinner timeSpinner_create;
    Spinner specialtySpinner_create;
    Spinner countrySpinner_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        //time spinner and array adapter
        timeSpinner_create = (Spinner) findViewById(R.id.CreateApptTimeSpinner);
        ArrayAdapter timeAdapter = ArrayAdapter.createFromResource(this, R.array.time_slot, android.R.layout.simple_spinner_dropdown_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner_create.setAdapter(timeAdapter);
        timeSpinner_create.setOnItemSelectedListener(this);

        //specialty spinner and array adapter
        specialtySpinner_create = (Spinner) findViewById(R.id.CreateApptSpecialty);
        ArrayAdapter specialtyAdapter_create = ArrayAdapter.createFromResource(this, R.array.specialty, android.R.layout.simple_spinner_dropdown_item);
        specialtyAdapter_create.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialtySpinner_create.setAdapter(specialtyAdapter_create);
        specialtySpinner_create.setOnItemSelectedListener(this);

        //country spinner and array adapter
        countrySpinner_create = (Spinner) findViewById(R.id.CreateApptCountries);
        ArrayAdapter countryAdapter_create = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_dropdown_item);
        countryAdapter_create.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner_create.setAdapter(countryAdapter_create);
        countrySpinner_create.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
