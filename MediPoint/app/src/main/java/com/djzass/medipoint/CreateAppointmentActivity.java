package com.djzass.medipoint;

import android.app.Activity;
import android.app.DialogFragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateAppointmentActivity extends Activity implements AdapterView.OnItemSelectedListener{

    //spinner
    Spinner specialtySpinner_create;
    Spinner countrySpinner_create;
    Spinner serviceSpinner_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

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

        //service spinner
        serviceSpinner_create = (Spinner) findViewById(R.id.CreateApptServices);

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

        //logout menu item selected
        else if(id==R.id.action_logout){
            AccountManager acctMgr = new AccountManager(this);
            acctMgr.logout();
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String speciality= String.valueOf(specialtySpinner_create.getSelectedItem());

        if(speciality.contentEquals("Dental")) {
            List<String> list = new ArrayList<String>();
            list.add("Routine Scaling");
            list.add("Polishing");
            list.add("Root Canal");
            list.add("Fillings");
            list.add("Tooth Extraction");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            serviceSpinner_create.setAdapter(dataAdapter);
        }
        if(speciality.contentEquals("Ears, Nose, and Throat(ENT)")) {
            List<String> list = new ArrayList<String>();
            list.add("General");
            list.add("Paediatric ENT");
            list.add("Obstructive Sleep Apnea (OSA)");
            list.add("Otology");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            serviceSpinner_create.setAdapter(dataAdapter2);
        }

        if(speciality.contentEquals("Women Health")) {
            List<String> list = new ArrayList<String>();
            list.add("Gynecologist");
            list.add("Obstetricians");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            serviceSpinner_create.setAdapter(dataAdapter2);
        }

        if(speciality.contentEquals("General Medicine")) {
            List<String> list = new ArrayList<String>();
            list.add("Dietetic Services");
            list.add("Physiotherapy");
            list.add("Child Care");
            list.add("Chronic Care");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            serviceSpinner_create.setAdapter(dataAdapter2);
        }

    }
    public void showDatePickerDialog(View v) {
        DialogFragment date = new DatePickerFragment();
        date.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void showTimepicker(View v){
        FragmentManager manager = getFragmentManager();
        TimePickerFragment timepicker = new TimePickerFragment();
        timepicker.show(manager, "TimePicker");
    }

    public void showDatePicker(View v){
        FragmentManager manager = getFragmentManager();
        DatePickerFragment datepicker = new DatePickerFragment();
        datepicker.show(manager, "Datepicker");
    }
}
