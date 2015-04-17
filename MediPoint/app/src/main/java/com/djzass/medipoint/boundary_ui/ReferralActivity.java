package com.djzass.medipoint.boundary_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.djzass.medipoint.R;
import com.djzass.medipoint.entity.Clinic;
import com.djzass.medipoint.entity.Doctor;
import com.djzass.medipoint.entity.Service;
import com.djzass.medipoint.entity.Specialty;
import com.djzass.medipoint.logic_manager.Container;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReferralActivity extends Activity implements AdapterView.OnItemSelectedListener{

    //spinner
    Spinner specialtySpinnerCreate;
    Spinner countrySpinnerCreate;
    Spinner doctorSpinnerCreate;
    Spinner clinicSpinnerCreate;
    List<Specialty> specialities;
    int specialtyId;
    int clinicId;
    int doctorId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral);

        //specialty spinner
        specialities = Container.getSpecialtyManager().getSpecialtys(this);
        specialtySpinnerCreate = (Spinner) findViewById(R.id.CreateApptSpecialty);
        List<String> specialtyNames = new ArrayList<String>();
        for(Specialty s: specialities){
            specialtyNames.add(s.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,specialtyNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        specialtySpinnerCreate.setAdapter(dataAdapter);
        specialtySpinnerCreate.setOnItemSelectedListener(this);

        //country spinner
        countrySpinnerCreate = (Spinner) findViewById(R.id.CreateApptCountries);
        ArrayAdapter countryAdapterCreate = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_dropdown_item);
        countryAdapterCreate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinnerCreate.setAdapter(countryAdapterCreate);
        countrySpinnerCreate.setOnItemSelectedListener(this);


        //doctor spinner
        doctorSpinnerCreate = (Spinner) findViewById(R.id.CreateApptDoctors);

        //clinic spinner
        clinicSpinnerCreate = (Spinner) findViewById(R.id.CreateApptLocations);

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
        switch(parent.getId()) {
            case R.id.CreateApptSpecialty:
                String speciality = String.valueOf(specialtySpinnerCreate.getSelectedItem());
                for (Specialty s : specialities) {
                    if (speciality.equals(s.getName())) {
                        specialtyId = s.getId();
                    }
                }

                List<Doctor> doctors = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(clinicId,specialtyId,this);
                List<String> doctorNames = new ArrayList<String>();
                for (Doctor d : doctors) {
                    doctorNames.add(d.getName());
                }
                ArrayAdapter<String> doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter.notifyDataSetChanged();
                doctorSpinnerCreate.setAdapter(doctorDataAdapter);
                doctorSpinnerCreate.setOnItemSelectedListener(this);
                break;

            case R.id.CreateApptCountries:
                String country = String.valueOf(countrySpinnerCreate.getSelectedItem());
                List<Clinic> clinics = Container.getClinicManager().getClinicsByCountry(country,this);
                List<String> clinicNames = new ArrayList<String>();
                for (Clinic c : clinics) {
                    clinicNames.add(c.getName());
                }
                ArrayAdapter<String> clinicDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, clinicNames);
                clinicDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                clinicDataAdapter.notifyDataSetChanged();
                clinicSpinnerCreate.setAdapter(clinicDataAdapter);
                clinicSpinnerCreate.setOnItemSelectedListener(this);
                String clinic = String.valueOf(clinicSpinnerCreate.getSelectedItem());
                for (Clinic c : clinics) {
                    if (clinic.equals(c.getName())) {
                        clinicId = c.getId();
                    }
                }

                doctors = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(clinicId,specialtyId,this);
                doctorNames = new ArrayList<String>();
                for (Doctor d : doctors) {
                    doctorNames.add(d.getName());
                }
                doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter.notifyDataSetChanged();
                doctorSpinnerCreate.setAdapter(doctorDataAdapter);
                doctorSpinnerCreate.setOnItemSelectedListener(this);
                break;

            case R.id.CreateApptLocations:

                clinic = String.valueOf(clinicSpinnerCreate.getSelectedItem());
                clinics = Container.getClinicManager().getClinics(this);
                for (Clinic c : clinics) {
                    if (clinic.equals(c.getName())) {
                        clinicId = c.getId();
                    }
                }

                doctors = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(clinicId,specialtyId,this);
                doctorNames = new ArrayList<String>();
                for (Doctor d : doctors) {
                    doctorNames.add(d.getName());
                }
                doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter.notifyDataSetChanged();
                doctorSpinnerCreate.setAdapter(doctorDataAdapter);
                doctorSpinnerCreate.setOnItemSelectedListener(this);
                break;

            case R.id.CreateApptDoctors:
                doctors = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(clinicId,specialtyId,this);
                String doctor = String.valueOf(doctorSpinnerCreate.getSelectedItem());
                for (Doctor d : doctors) {
                    if (doctor.equals(d.getName())) {
                        doctorId = d.getDoctorId();
                    }
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void goto_appointment(View view)
    {
        Intent intent = new Intent(ReferralActivity.this, CreateAppointmentActivity.class);
        intent.putExtra("REFERRER_ID",doctorId);
        startActivity(intent);
    }
}
