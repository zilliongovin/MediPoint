package com.djzass.medipoint;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.entity.Clinic;
import com.djzass.medipoint.entity.Doctor;
import com.djzass.medipoint.entity.Service;
import com.djzass.medipoint.entity.Specialty;
import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.logic_database.ClinicDAO;
import com.djzass.medipoint.logic_database.DoctorDAO;
import com.djzass.medipoint.logic_database.ServiceDAO;
import com.djzass.medipoint.logic_database.SpecialtyDAO;
import com.djzass.medipoint.logic_manager.*;
import com.djzass.medipoint.logic_manager.AccountManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Zillion Govin on 17/3/2015.
 */
public class EditAppointmentActivity extends Activity implements AdapterView.OnItemSelectedListener {

    int clinicId = 1;
    int patientId;
    int doctorId;
    Calendar date;
    int serviceId;
    int specialtyId = 1;
    int countryId = 1;
    int duration;
    String preAppointmentActions;
    Timeframe timeframe;

    //Spinner
    Spinner specialtySpinnerCreate;
    Spinner countrySpinnerCreate;
    Spinner serviceSpinnerCreate;
    Spinner doctorSpinnerCreate;
    Spinner clinicSpinnerCreate;
    SpecialtyDAO specialtyDAO;
    List<Specialty> specialities;
    Button confirmButton;
    Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);
        Bundle b = getIntent().getExtras();
        Appointment app = b.getParcelable("appFromView");
        Toast.makeText(this,app.toString(),Toast.LENGTH_LONG).show();


        specialities = Container.getSpecialtyManager().getSpecialtys(this);
        specialtySpinnerCreate = (Spinner) findViewById(R.id.EditApptSpecialty);
        List<String> specialtyNames = new ArrayList<String>();
        for(Specialty s: specialities){
            specialtyNames.add(s.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,specialtyNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        specialtySpinnerCreate.setAdapter(dataAdapter);
        specialtySpinnerCreate.setSelection(dataAdapter.getPosition(Container.getSpecialtyManager().getSpecialtyNameByID(app.getSpecialtyId(),this)));
        specialtySpinnerCreate.setOnItemSelectedListener(this);

        //country spinner and array adapter
        countrySpinnerCreate = (Spinner) findViewById(R.id.EditApptCountries);
        countrySpinnerCreate.setOnItemSelectedListener(this);
        ArrayAdapter countryAdapter_create = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_dropdown_item);
        countryAdapter_create.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinnerCreate.setAdapter(countryAdapter_create);
        countrySpinnerCreate.setSelection(countryAdapter_create.getPosition(Container.getClinicManager().getCountryByClinicId(app.getClinicId(),this)));


        //service spinner
        serviceSpinnerCreate = (Spinner) findViewById(R.id.EditApptType);
        //doctor spinner
        doctorSpinnerCreate = (Spinner) findViewById(R.id.EditApptDoctors);
        //clinic location spinner
        clinicSpinnerCreate = (Spinner) findViewById(R.id.EditApptLocations);

        confirmButton = (Button)findViewById(R.id.ConfirmEditAppt);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                /*EditText usernameBox = (EditText) findViewById(R.id.enterUsernameTextbox);
                EditText passwordBox = (EditText) findViewById(R.id.enterPasswordTextbox);
                String username = usernameBox.getText().toString();
                String password = passwordBox.getText().toString();
                boolean isAuthenticated = Container.GlobalAccountManager.authenticate(username,password);
                if(isAuthenticated==true){
                    Container.GlobalAccountManager.login(username,password);
                    loginSuccessful(username);
                    goToMain();
            }
        });
        cancelButton = (Button)findViewById(R.id.CancelEditAppt);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                /*EditText usernameBox = (EditText) findViewById(R.id.enterUsernameTextbox);
                EditText passwordBox = (EditText) findViewById(R.id.enterPasswordTextbox);
                String username = usernameBox.getText().toString();
                String password = passwordBox.getText().toString();
                boolean isAuthenticated = Container.GlobalAccountManager.authenticate(username,password);
                if(isAuthenticated==true){
                    Container.GlobalAccountManager.login(username,password);
                    loginSuccessful(username);
                    goToMain(); */
            }
        });

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

        //logout menu item selected
        else if(id==R.id.action_logout){
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.deleteLoginSession();
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Bundle b = getIntent().getExtras();
        Appointment app = b.getParcelable("appFromView");
        /** pop up message
         TextView MyTime = (TextView) view;
         Toast.makeText(this, MyTime.getText() + " is selected", Toast.LENGTH_SHORT).show();
         **/
        switch (parent.getId()) {
            case R.id.EditApptSpecialty:
                String speciality = String.valueOf(specialtySpinnerCreate.getSelectedItem());
                int selection = 1;
                for (Specialty s : specialities) {
                    if (speciality.equals(s.getName())) {
                        selection = s.getId();
                    }
                }
                this.specialtyId = selection;

                List<Service> services = Container.getServiceManager().getServicesBySpecialtyID(selection, this);
                List<String> serviceNames = new ArrayList<String>();
                for (Service s : services) {
                    serviceNames.add(s.getName());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, serviceNames);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                serviceSpinnerCreate.setAdapter(dataAdapter);
                serviceSpinnerCreate.setSelection(dataAdapter.getPosition(Container.getServiceManager().getServiceNameByID(app.getServiceId(),this)));
                String service = String.valueOf(serviceSpinnerCreate.getSelectedItem());
                for (Service s : services) {
                    if (service.equals(s.getName())) {
                        this.serviceId = s.getId();
                        this.preAppointmentActions = s.getPreAppointmentActions();
                        this.duration = s.getDuration();
                    }
                }
                //List<Doctor> doctors = ((Container)getApplicationContext()).getGlobalDoctorDAO().getDoctorBySpecialization(selection);
                //List<Doctor> doctors = Container.GlobalDoctorDAO.getDoctorBySpecialization(selection);

                List<Doctor> doctors1 = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(specialtyId, clinicId, this);
                List<String> doctorNames1 = new ArrayList<String>();
                for (Doctor d : doctors1) {
                    doctorNames1.add(d.getName());
                }
                ArrayAdapter<String> doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames1);
                doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter.notifyDataSetChanged();
                doctorSpinnerCreate.setAdapter(doctorDataAdapter);

                String doctor = String.valueOf(doctorSpinnerCreate.getSelectedItem());
                for (Doctor d : doctors1) {
                    if (doctor.equals(d.getName())) {
                        doctorId = d.getDoctorId();
                    }
                }
                break;


            case R.id.EditApptCountries:
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
                clinicSpinnerCreate.setSelection(clinicDataAdapter.getPosition(Container.getClinicManager().getClinicNameByClinicId(app.getClinicId(),this)));
                clinicSpinnerCreate.setOnItemSelectedListener(this);
                String clinic = String.valueOf(clinicSpinnerCreate.getSelectedItem());
                for (Clinic c : clinics) {
                    if (clinic.equals(c.getName())) {
                        clinicId = c.getId();
                    }
                }

                List<Doctor> doctors2 = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(specialtyId,clinicId,this);
                List<String> doctorNames2 = new ArrayList<String>();
                for (Doctor d : doctors2) {
                    doctorNames2.add(d.getName());
                }
                ArrayAdapter<String> doctorDataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames2);
                doctorDataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter2.notifyDataSetChanged();
                doctorSpinnerCreate.setAdapter(doctorDataAdapter2);
                String doctor2 = String.valueOf(doctorSpinnerCreate.getSelectedItem());
                for (Doctor d : doctors2) {
                    if (doctor2.equals(d.getName())) {
                        doctorId = d.getDoctorId();
                    }
                }
                break;


            case R.id.CreateApptLocations:

                String clinic2 = String.valueOf(clinicSpinnerCreate.getSelectedItem());
                try {
                    ClinicDAO clinicDAO = new ClinicDAO(this);
                    int clinicSelection = 1;
                    List<Clinic> clinics2 = clinicDAO.getAllClinics();
                    for (Clinic c : clinics2) {
                        if (clinic2.equals(c.getName())) {
                            clinicSelection = c.getId();
                        }
                    }

                    DoctorDAO doctorDAO = new DoctorDAO(this);
                    List<Doctor> doctors3 = doctorDAO.getDoctorsByClinicAndSpecialization(clinicSelection,specialtyId);
                    List<String> doctorNames3 = new ArrayList<String>();
                    for (Doctor d : doctors3) {
                        doctorNames3.add(d.getName());
                    }
                    ArrayAdapter<String> doctorDataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames3);
                    doctorDataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    doctorDataAdapter3.notifyDataSetChanged();
                    doctorSpinnerCreate.setAdapter(doctorDataAdapter3);
                    String doctor3 = String.valueOf(doctorSpinnerCreate.getSelectedItem());
                    for (Doctor d : doctors3) {
                        if (doctor3.equals(d.getName())) {
                            doctorId = d.getDoctorId();
                        }
                    }

                    //List<Service> services = ((Container)getApplicationContext()).getGlobalServiceDAO().getServicesBySpecialtyID(selection);
                    //List<Service> services = Container.GlobalServiceDAO.getServicesBySpecialtyID(selection);



                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

    }



    public void editAppointment(){

        //calendar changed

        //time changed

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

    /*
    public void showDatePicker(View v){
        FragmentManager manager = getFragmentManager();
        DatePickerFragment datepicker = new DatePickerFragment();
        datepicker.show(manager, "Datepicker");
    }
    */

    public void onDateButtonSelected(View v){
        int id = v.getId();
        Bundle bundle = new Bundle();
        bundle.putInt("VIEW_ID",id);
        FragmentManager manager = getFragmentManager();
        DatePickerFragment datepicker = new DatePickerFragment();
        datepicker.setArguments(bundle);
        datepicker.show(manager, "Datepicker");
    }
}

