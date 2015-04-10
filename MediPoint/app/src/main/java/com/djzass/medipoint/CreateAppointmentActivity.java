package com.djzass.medipoint;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import android.os.Parcel;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;

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
import com.djzass.medipoint.logic_database.AppointmentDAO;
import com.djzass.medipoint.logic_database.ClinicDAO;
import com.djzass.medipoint.logic_database.DoctorDAO;
import com.djzass.medipoint.logic_database.ServiceDAO;
import com.djzass.medipoint.logic_database.SpecialtyDAO;
import com.djzass.medipoint.logic_manager.AccountManager;
import com.djzass.medipoint.logic_manager.AppointmentManager;
import com.djzass.medipoint.logic_manager.Container;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CreateAppointmentActivity extends onDataPass implements AdapterView.OnItemSelectedListener, SelectionListener{

    //appointment atrribute selections
    int clinicId;
    int patientId;
    int doctorId;
    Calendar date;
    int serviceId;
    int specialtyId;
    int duration;
    String preAppointmentActions;
    Timeframe timeframe;

    //spinner
    Spinner specialtySpinner_create;
    Spinner countrySpinner_create;
    Spinner serviceSpinner_create;
    Button confirmButton;
    Button cancelButton;
    Spinner doctorSpinner_create;
    Spinner clinicSpinner_create;
    SpecialtyDAO specialtyDAO;
    List<Specialty> specialities;
    AppointmentDAO appointmentDAO;
    //List<Specialty> specialities = ((Container)getApplicationContext()).getGlobalSpecialtyDAO().getAllSpecialties();

    //List<Specialty> specialities = Container.GlobalSpecialtyDAO.getAllSpecialties();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        AlarmSetter as = new AlarmSetter();
        Notification mnotification = new Notification();
        mnotification.buildNotification(this,"appointment Created!");

        Appointment appointment2 = new Appointment(Parcel.obtain());
        Account account = new Account(Parcel.obtain());

        //specialty spinner and array adapter
        try {
            SessionManager sessionManager = new SessionManager(this);
            this.patientId = (int)sessionManager.getAccountId();

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


        //country spinner and array adapter
        countrySpinner_create = (Spinner) findViewById(R.id.CreateApptCountries);
        ArrayAdapter countryAdapter_create = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_dropdown_item);
        countryAdapter_create.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner_create.setAdapter(countryAdapter_create);
        countrySpinner_create.setOnItemSelectedListener(this);

        //service spinner
        serviceSpinner_create = (Spinner) findViewById(R.id.CreateApptServices);
        //doctor spinner
        doctorSpinner_create = (Spinner) findViewById(R.id.CreateApptDoctors);
        //clinic location spinner
        clinicSpinner_create = (Spinner) findViewById(R.id.CreateApptLocations);


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
                String speciality = String.valueOf(specialtySpinner_create.getSelectedItem());
                try {
                    int selection = 0;
                    for (Specialty s : specialities) {
                        if (speciality.equals(s.getName())) {
                            selection = s.getId();
                        }
                    }
                    specialtyId = selection;
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
                    serviceSpinner_create.setAdapter(dataAdapter);
                    String service = String.valueOf(serviceSpinner_create.getSelectedItem());
                    for(Service s : services)
                    {
                        if(service.equals(s.getName()))
                        {
                            this.serviceId = s.getId();
                            this.preAppointmentActions = s.getPreAppointmentActions();
                            this.duration = s.getDuration();
                        }
                    }

                    DoctorDAO doctorDAO = new DoctorDAO(this);
                    List<Doctor> doctors = doctorDAO.getDoctorBySpecialization(selection);
                    //List<Doctor> doctors = ((Container)getApplicationContext()).getGlobalDoctorDAO().getDoctorBySpecialization(selection);
                    //List<Doctor> doctors = Container.GlobalDoctorDAO.getDoctorBySpecialization(selection);
                    List<String> doctorNames = new ArrayList<String>();
                    for (Doctor d : doctors) {
                        doctorNames.add(d.getName());
                    }
                    ArrayAdapter<String> doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                    doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    doctorDataAdapter.notifyDataSetChanged();
                    doctorSpinner_create.setAdapter(doctorDataAdapter);
                    String doctor = String.valueOf(doctorSpinner_create.getSelectedItem());
                    for(Doctor d : doctors)
                    {
                        if(doctor.equals(d.getName()))
                        {
                            doctorId = d.getDoctorId();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.CreateApptCountries:
                String country = String.valueOf(countrySpinner_create.getSelectedItem());
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
                    clinicSpinner_create.setAdapter(clinicDataAdapter);
                    String clinic = String.valueOf(clinicSpinner_create.getSelectedItem());
                    for(Clinic c : clinics)
                    {
                        if(clinic.equals(c.getName()))
                        {
                            clinicId = c.getId();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            default:
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
        bundle.putStringArrayList(TimePickerFragment.DATA, getItems());     // Require ArrayList
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
        Toast.makeText(this, "Button clicked.", Toast.LENGTH_SHORT).show();
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, 1);

        if (this.date.compareTo(currentDate)<0){
            Toast.makeText(this, "You are not allowed to book within 24 hours."+this.date.getTime().toString(), Toast.LENGTH_SHORT).show();
        }

        AccountManager accountManager = new AccountManager(this);
        this.timeframe = new Timeframe(18,21);
        Appointment appointment = new Appointment(this.patientId, this.clinicId,this.specialtyId,this.serviceId,this.doctorId,this.date,this.timeframe);
        long res = Container.getAppointmentManager().createAppointment(appointment, this);

        if (res==-1) {
            Notification notification = new Notification();
            notification.buildNotification(this, "Appointment creation fail :C");
        } else {
            AlarmSetter malarm = new AlarmSetter();
            Notification notification = new Notification();
            notification.buildNotification(this, "Appointment Created!!");
            try {
                malarm.setAlarm(this, appointment, accountManager.getAccountById(this.patientId));
            } catch (ParseException e){

            }
        }
    }

    private ArrayList<String> getItems() {
        ArrayList<String> availableSlots = new ArrayList<String>();

        availableSlots.add("10:00 - 10:30");
        availableSlots.add("11:00 - 11:30");
        availableSlots.add("12:00 - 12:30");
        availableSlots.add("13:00 - 13:30");
        availableSlots.add("10:00 - 10:30");
        availableSlots.add("11:00 - 11:30");
        availableSlots.add("12:00 - 12:30");
        availableSlots.add("13:00 - 13:30");
        return availableSlots;
    }

    @Override
    public void selectItem(int position) {
        Button btn = (Button) findViewById(R.id.timepicker);
        btn.setText(getItems().get(position));
        Container.getAppointmentManager().getAvailableTimeSlot(this.date, this.patientId, this.doctorId, this.clinicId, 18, 42, duration, this);

    }

    @Override
    public void DatePickerFragmentToActivity(int date,int month,int year,Button button){
        super.DatePickerFragmentToActivity(date,month,year,button);
        this.date = Calendar.getInstance();
        this.date.set(year,month,date);
    }
}
