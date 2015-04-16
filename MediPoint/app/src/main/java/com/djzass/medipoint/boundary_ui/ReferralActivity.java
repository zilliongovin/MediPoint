package com.djzass.medipoint.boundary_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.djzass.medipoint.R;
import com.djzass.medipoint.entity.Clinic;
import com.djzass.medipoint.entity.Doctor;
import com.djzass.medipoint.entity.Specialty;
import com.djzass.medipoint.logic_database.ClinicDAO;
import com.djzass.medipoint.logic_database.DoctorDAO;
import com.djzass.medipoint.logic_database.SpecialtyDAO;
import com.djzass.medipoint.logic_manager.Container;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReferralActivity extends Activity implements AdapterView.OnItemSelectedListener{

    //spinner
    Spinner specialtySpinner_create;
    Spinner countrySpinner_create;
    Spinner doctorSpinner_create;
    Spinner clinicSpinner_create;
    SpecialtyDAO specialtyDAO;
    List<Specialty> specialities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral);

        //specialty spinner
        try {
            specialtyDAO = new SpecialtyDAO(this);
            specialities = specialtyDAO.getAllSpecialties();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                try {
                int selection = 0;
                for (Specialty s : specialities) {
                    if (speciality.equals(s.getName())) {
                        selection = s.getId();
                    }
                }
                DoctorDAO doctorDAO = new DoctorDAO(this);
                List<Doctor> doctors = doctorDAO.getDoctorBySpecialization(selection);
                //List<Doctor> doctors = Container.GlobalDoctorDAO.getDoctorBySpecialization(selection);
                List<String> doctorNames = new ArrayList<String>();
                for (Doctor d : doctors) {
                    doctorNames.add(d.getName());
                }
                ArrayAdapter<String> doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter.notifyDataSetChanged();
                doctorSpinner_create.setAdapter(doctorDataAdapter);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.CreateApptCountries:
                String country = (String) parent.getAdapter().getItem(position);
                try {
                    ClinicDAO clinicDAO = new ClinicDAO(this);
                    List<Clinic> clinics = clinicDAO.getClinicsByCountry(country);

                List<String> clinicNames = new ArrayList<String>();
                for (Clinic c : clinics) {
                    clinicNames.add(c.getName());
                }
                ArrayAdapter<String> clinicDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, clinicNames);
                clinicDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                clinicDataAdapter.notifyDataSetChanged();
                clinicSpinner_create.setAdapter(clinicDataAdapter);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
        String clinicName = String.valueOf(clinicSpinner_create.getSelectedItem());
        String specialtyName = String.valueOf(specialtySpinner_create.getSelectedItem());
        String doctorName = String.valueOf(doctorSpinner_create.getSelectedItem());

        Clinic clinic = Container.getClinicManager().getClinicsByName(clinicName,this).get(0);
        Specialty specialty = Container.getSpecialtyManager().getSpecialtiesByName(specialtyName,this).get(0);

        List<Doctor> doctors = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(specialty.getId(),clinic.getId(),this);
        int doctorId = 0;
        for(Doctor d:doctors){
            if(doctorName.equals(d.getName())){
                doctorId = d.getDoctorId();
            }
        }

        Intent intent = new Intent(ReferralActivity.this, CreateAppointmentActivity.class);
        intent.putExtra("REFERRER_ID",doctorId);
        startActivity(intent);
    }
}
