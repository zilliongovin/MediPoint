package com.djzass.medipoint;


import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.database.Cursor;
import android.os.Bundle;

import android.os.Parcel;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.djzass.medipoint.entity.Account;
import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.entity.Clinic;
import com.djzass.medipoint.entity.Doctor;
import com.djzass.medipoint.entity.Service;
import com.djzass.medipoint.entity.Specialty;
import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.logic_database.AccountDAO;
import com.djzass.medipoint.logic_database.AppointmentDAO;
import com.djzass.medipoint.logic_database.ClinicDAO;
import com.djzass.medipoint.logic_database.DoctorDAO;
import com.djzass.medipoint.logic_database.ServiceDAO;
import com.djzass.medipoint.logic_database.SpecialtyDAO;
import com.djzass.medipoint.logic_manager.AccountManager;
import com.djzass.medipoint.logic_manager.Container;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateAppointmentActivity extends onDataPass implements AdapterView.OnItemSelectedListener, SelectionListener{

    //appointment atrribute selections
    int clinicId = 1;
    int patientId;
    int doctorId;
    int referrerId;
    Calendar date;
    int serviceId;
    int specialtyId = 1;
    int countryId = 1;
    int duration;
    String preAppointmentActions;
    Timeframe timeframe;
    long accountId;


    //spinner
    Spinner specialtySpinnerCreate;
    Spinner countrySpinnerCreate;
    Spinner serviceSpinnerCreate;
    Button confirmButton;
    Button cancelButton;
    Spinner doctorSpinnerCreate;
    Spinner clinicSpinnerCreate;
    SpecialtyDAO specialtyDAO;
    List<Specialty> specialities;
    AppointmentDAO appointmentDAO;
    //List<Specialty> specialities = ((Container)getApplicationContext()).getGlobalSpecialtyDAO().getAllSpecialties();

    //List<Specialty> specialities = Container.GlobalSpecialtyDAO.getAllSpecialties();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        referrerId = getIntent().getIntExtra("REFERRER_ID",-1);

        //specialty spinner and array adapter
        try {
            SessionManager sessionManager = new SessionManager(this);
            this.patientId = (int)sessionManager.getAccountId();
            //Toast.makeText(this,(String) "" + patientId,Toast.LENGTH_SHORT).show();

            specialtyDAO = new SpecialtyDAO(this);
            specialities = specialtyDAO.getAllSpecialties();
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
            accountId = sessionManager.getAccountId();
            specialtySpinnerCreate.setAdapter(dataAdapter);
            specialtySpinnerCreate.setOnItemSelectedListener(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //country spinner and array adapter
        countrySpinnerCreate = (Spinner) findViewById(R.id.CreateApptCountries);
        countrySpinnerCreate.setOnItemSelectedListener(this);
        ArrayAdapter countryAdapter_create = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_dropdown_item);
        countryAdapter_create.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinnerCreate.setAdapter(countryAdapter_create);


        //service spinner
        serviceSpinnerCreate = (Spinner) findViewById(R.id.CreateApptServices);
        //doctor spinner
        doctorSpinnerCreate = (Spinner) findViewById(R.id.CreateApptDoctors);
        //clinic location spinner
        clinicSpinnerCreate = (Spinner) findViewById(R.id.CreateApptLocations);

        confirmButton = (Button)findViewById(R.id.ConfirmCreateAppt);
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
                    */
                onClickCreateAppointment();
            }
        });

        cancelButton = (Button)findViewById(R.id.CancelCreateAppt);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
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
            //((Container)getApplicationContext()).getGlobalAccountManager().logout();
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.deleteLoginSession();
            //Container.GlobalAccountManager.logout();
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()) {
            case R.id.CreateApptSpecialty:
                String speciality = String.valueOf(specialtySpinnerCreate.getSelectedItem());
                try {
                    int selection = 1;
                    for (Specialty s : specialities) {
                        if (speciality.equals(s.getName())) {
                            selection = s.getId();
                        }
                    }
                    this.specialtyId = selection;

                    //List<Service> services = ((Container)getApplicationContext()).getGlobalServiceDAO().getServicesBySpecialtyID(selection);
                    //List<Service> services = Container.GlobalServiceDAO.getServicesBySpecialtyID(selection);
                    ServiceDAO serviceDAO = new ServiceDAO(this);
                    List<Service> services = serviceDAO.getServicesBySpecialtyID(selection);
                    List<String> serviceNames = new ArrayList<String>();
                    for (Service s : services) {
                        serviceNames.add(s.getName());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, serviceNames);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    serviceSpinnerCreate.setAdapter(dataAdapter);
                    String service = String.valueOf(serviceSpinnerCreate.getSelectedItem());
                    for (Service s : services) {
                        if (service.equals(s.getName())) {
                            this.serviceId = s.getId();
                            this.preAppointmentActions = s.getPreAppointmentActions();
                            this.duration = s.getDuration();
                            Log.d("Service", this.serviceId + " " + this.duration);
                        }
                    }
                    //List<Doctor> doctors = ((Container)getApplicationContext()).getGlobalDoctorDAO().getDoctorBySpecialization(selection);
                    //List<Doctor> doctors = Container.GlobalDoctorDAO.getDoctorBySpecialization(selection);
                    DoctorDAO doctorDAO = new DoctorDAO(this);
                    List<Doctor> doctors = doctorDAO.getDoctorsByClinicAndSpecialization(clinicId,specialtyId);
                    List<String> doctorNames = new ArrayList<String>();
                    for (Doctor d : doctors) {
                        doctorNames.add(d.getName());
                    }
                    ArrayAdapter<String> doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                    doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    doctorDataAdapter.notifyDataSetChanged();
                    doctorSpinnerCreate.setAdapter(doctorDataAdapter);
                    String doctor = String.valueOf(doctorSpinnerCreate.getSelectedItem());
                    for (Doctor d : doctors) {
                        if (doctor.equals(d.getName())) {
                            doctorId = d.getDoctorId();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.CreateApptCountries:
                String country = String.valueOf(countrySpinnerCreate.getSelectedItem());
                try {
                    ClinicDAO clinicDAO = new ClinicDAO(this);
                    List<Clinic> clinics = clinicDAO.getClinicsByCountry(country);
                    //List<Clinic> clinics = ((Container)getApplicationContext()).getGlobalDoctorDAO().getDoctorBySpecialization(selection);
                    //List<Clinic> clinics = Container.GlobalClinicDAO.getClinicsByCountry(country);
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

                    DoctorDAO doctorDAO = new DoctorDAO(this);
                    List<Doctor> doctors = doctorDAO.getDoctorsByClinicAndSpecialization(clinicId,specialtyId);
                    List<String> doctorNames = new ArrayList<String>();
                    for (Doctor d : doctors) {
                        doctorNames.add(d.getName());
                    }
                    ArrayAdapter<String> doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                    doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    doctorDataAdapter.notifyDataSetChanged();
                    doctorSpinnerCreate.setAdapter(doctorDataAdapter);
                    String doctor = String.valueOf(doctorSpinnerCreate.getSelectedItem());
                    for (Doctor d : doctors) {
                        if (doctor.equals(d.getName())) {
                            doctorId = d.getDoctorId();
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.CreateApptLocations:
                //Toast.makeText(this,"InClinic",Toast.LENGTH_SHORT).show();
                String clinic = String.valueOf(clinicSpinnerCreate.getSelectedItem());
                //Toast.makeText(this,clinic,Toast.LENGTH_SHORT).show();
                try {
                    ClinicDAO clinicDAO = new ClinicDAO(this);
                    int clinicSelection = 1;
                    List<Clinic> clinics = clinicDAO.getAllClinics();
                    for (Clinic c : clinics) {
                        if (clinic.equals(c.getName())) {
                            clinicSelection = c.getId();
                        }
                    }
                    clinicId = clinicSelection;
                    //Toast.makeText(this,""+clinicId,Toast.LENGTH_SHORT).show();
                    DoctorDAO doctorDAO = new DoctorDAO(this);
                    List<Doctor> doctors = doctorDAO.getDoctorsByClinicAndSpecialization(clinicSelection,specialtyId);
                    List<String> doctorNames = new ArrayList<String>();
                    for (Doctor d : doctors) {
                        doctorNames.add(d.getName());
                    }
                    ArrayAdapter<String> doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                    doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    doctorDataAdapter.notifyDataSetChanged();
                    doctorSpinnerCreate.setAdapter(doctorDataAdapter);
                    String doctor = String.valueOf(doctorSpinnerCreate.getSelectedItem());
                    for (Doctor d : doctors) {
                        if (doctor.equals(d.getName())) {
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



   /* public void showDatePickerDialog(View v) {
        DialogFragment date = new DatePickerFragment();
        date.show(getFragmentManager(), "datePicker");
    }*/

    public void onTimeButtonSelected(View v){
        int id = v.getId();
        Bundle bundle = new Bundle();
        bundle.putInt("VIEW_ID",id);
        FragmentManager manager = getFragmentManager();
        TimePickerFragment datepicker = new TimePickerFragment();
        datepicker.setArguments(bundle);
        datepicker.show(manager, "Datepicker");
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
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(TimePickerFragment.DATA, getTimePickerItems());     // Require ArrayList
        bundle.putInt(TimePickerFragment.SELECTED, 0);
        timepicker.setArguments(bundle);
        timepicker.show(manager, "TimePicker");
    }

    /*public void showDatePicker(View v){
        FragmentManager manager = getFragmentManager();
        DatePickerFragment datepicker = new DatePickerFragment();
        datepicker.show(manager, "Datepicker");
    }*/

    public void onDateButtonSelected(View v){
        int id = v.getId();
        Bundle bundle = new Bundle();
        bundle.putInt("VIEW_ID",id);
        FragmentManager manager = getFragmentManager();
        DatePickerFragment datepicker = new DatePickerFragment();
        datepicker.setArguments(bundle);
        datepicker.show(manager, "Datepicker");
    }

    public void onClickCreateAppointment() {
        //AppointmentManager appointmentManager = new AppointmentManager();
        //Toast.makeText(this, "Button clicked.", Toast.LENGTH_SHORT).show();
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, 1);

        if (this.date.compareTo(currentDate)<0){
            Toast.makeText(this, "You must book at least 24 hours in advance. ", Toast.LENGTH_SHORT).show();
        } else {
            AccountManager accountManager = new AccountManager(this);
            Appointment appointment = new Appointment(this.patientId, this.clinicId, this.specialtyId, this.serviceId, this.doctorId, referrerId,this.date, this.timeframe);
            long res = Container.getAppointmentManager().createAppointment(appointment, this);

            if (res == -1) { 
                Toast.makeText(this,"Appointment creation failed", Toast.LENGTH_SHORT).show();

            } else {
                AlarmSetter malarm = new AlarmSetter();
                AccountManager mAcc = new AccountManager(this);
                Account account = new Account();
                try {
                    account = mAcc.getAccountById(accountId);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                malarm.setAlarm(getApplicationContext(),appointment,account);
                /*Notification notification = new Notification();
                notification.buildNotification(this, "Appointment created.",appointment);*/
                Intent goToMain = new Intent(this, MainActivity.class);
                startActivity(goToMain);
/*            try {
                malarm.setAlarm(this, appointment, accountManager.getAccountById(this.patientId));


            } catch (ParseException e){
                Toast.makeText(this,"In Here",Toast.LENGTH_SHORT).show();
            }*/

            }
        }
    }

    private ArrayList<String> getTimePickerItems() {
        ArrayList<String> availableSlots = new ArrayList<String>();
        //Toast.makeText(this, this.date.getTime().toString(), Toast.LENGTH_SHORT).show();
        this.duration = Container.getServiceManager().getServiceDurationbyID(this.serviceId, this);
        List<Timeframe> temp = Container.getAppointmentManager().getAvailableTimeSlot(this.date, this.patientId, this.doctorId, this.clinicId, 18, 42, duration, this);

        availableSlots.add("N/A");
        for (Timeframe t:temp){
            availableSlots.add(t.getTimeLine());
        }
        return availableSlots;
    }

    @Override
    public void selectItem(int position) {
        Button btn = (Button) findViewById(R.id.timepicker);
        if(getTimePickerItems().get(position)!="N/A"){
            btn.setText(getTimePickerItems().get(position));
            this.timeframe = new Timeframe(getTimePickerItems().get(position));
        }
    }

    @Override
    public void DatePickerFragmentToActivity(int date,int month,int year,Button button){
        super.DatePickerFragmentToActivity(date,month,year,button);
        this.date = Calendar.getInstance();
        this.date.set(year,month,date);

    }
}
