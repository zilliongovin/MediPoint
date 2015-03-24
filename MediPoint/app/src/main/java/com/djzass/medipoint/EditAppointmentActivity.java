package com.djzass.medipoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Zillion Govin on 17/3/2015.
 */
public class EditAppointmentActivity extends Activity implements AdapterView.OnItemSelectedListener {

    //Spinner
    Spinner timeSpinner_edit;
    Spinner specialtySpinner_edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        //time spinner and array adapter
        timeSpinner_edit = (Spinner) findViewById(R.id.EditApptTimeSpinner);
        ArrayAdapter timeAdapter = ArrayAdapter.createFromResource(this, R.array.time_slot, android.R.layout.simple_spinner_dropdown_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner_edit.setAdapter(timeAdapter);
        timeSpinner_edit.setOnItemSelectedListener(this);

        //specialty spinner and array adapter
        specialtySpinner_edit = (Spinner) findViewById(R.id.EditApptSpecialty);
        ArrayAdapter specialtyAdapter_edit = ArrayAdapter.createFromResource(this, R.array.specialty, android.R.layout.simple_spinner_dropdown_item);
        specialtyAdapter_edit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialtySpinner_edit.setAdapter(specialtyAdapter_edit);
        specialtySpinner_edit.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

        /** pop up message
         TextView MyTime = (TextView) view;
         Toast.makeText(this, MyTime.getText() + " is selected", Toast.LENGTH_SHORT).show();
         **/




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Button Methods
    public void onCancelEdit(View view){
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Appointment not edited", Toast.LENGTH_SHORT).show();
    }

    public void onConfirmEdit(View view){
        //add to database

        //if successful
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Appointment successfully edited", Toast.LENGTH_SHORT).show();

        //if not successful
        // Toast.makeText(this, "Failed to edit appointment, please try again", Toast.LENGTH_SHORT).show();
    }

}

