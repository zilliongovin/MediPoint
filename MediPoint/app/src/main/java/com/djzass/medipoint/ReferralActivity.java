package com.djzass.medipoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.djzass.medipoint.entity.Clinic;
import com.djzass.medipoint.entity.Doctor;
import com.djzass.medipoint.entity.Service;
import com.djzass.medipoint.entity.Specialty;

import java.util.ArrayList;
import java.util.List;

public class ReferralActivity extends Activity implements AdapterView.OnItemSelectedListener{

    //spinner
    Spinner specialtySpinner_create;
    Spinner countrySpinner_create;
    Spinner doctorSpinner_create;
    Spinner clinicSpinner_create;
    List<Specialty> specialities = Container.GlobalSpecialtyDAO.getAllSpecialties();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral);

        //specialty spinner
        specialtySpinner_create = (Spinner) findViewById(R.id.CreateApptSpecialty);
        List<String> specialtyNames = new ArrayList<String>();
        for(Specialty s: specialities){
            specialtyNames.add(s.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,specialtyNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        specialtySpinner_create.setAdapter(dataAdapter);
        specialtySpinner_create.setOnItemSelectedListener(this);

        //country spinner
        countrySpinner_create = (Spinner) findViewById(R.id.CreateApptCountries);
        ArrayAdapter countryAdapter_create = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_dropdown_item);
        countryAdapter_create.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner_create.setAdapter(countryAdapter_create);
        countrySpinner_create.setOnItemSelectedListener(this);


        //doctor spinner
        doctorSpinner_create = (Spinner) findViewById(R.id.CreateApptDoctors);

        //clinic spinner
        clinicSpinner_create = (Spinner) findViewById(R.id.CreateApptLocations);

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
                String speciality = String.valueOf(specialtySpinner_create.getSelectedItem());
                int selection = 0;
                for (Specialty s : specialities) {
                    if (speciality.equals(s.getName())) {
                        selection = s.getId();
                    }
                }

                List<Doctor> doctors = Container.GlobalDoctorDAO.getDoctorBySpecialization(selection);
                List<String> doctorNames = new ArrayList<String>();
                for (Doctor d : doctors) {
                    doctorNames.add(d.getName());
                }
                ArrayAdapter<String> doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter.notifyDataSetChanged();
                doctorSpinner_create.setAdapter(doctorDataAdapter);
                break;

            case R.id.CreateApptCountries:
                String country = (String) parent.getAdapter().getItem(position);
                List<Clinic> clinics = Container.GlobalClinicDAO.getClinicsByCountry(country);
                List<String> clinicNames = new ArrayList<String>();
                for (Clinic c : clinics) {
                    clinicNames.add(c.getName());
                }
                ArrayAdapter<String> clinicDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, clinicNames);
                clinicDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                clinicDataAdapter.notifyDataSetChanged();
                clinicSpinner_create.setAdapter(clinicDataAdapter);
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void goto_appointment(View view)
    {
        Intent intent = new Intent(ReferralActivity.this, CreateAppointmentActivity.class);
        startActivity(intent);
    }
}
