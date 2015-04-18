package com.djzass.medipoint.boundary_ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.djzass.medipoint.R;
import com.djzass.medipoint.entity.Account;
import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.entity.Service;
import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.logic_manager.AlarmSetter;
import com.djzass.medipoint.logic_manager.Container;
import com.djzass.medipoint.logic_manager.SessionManager;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CreateFollowUpActivity extends onDataPass implements AdapterView.OnItemSelectedListener, SelectionListener{

    Calendar apptDate = Calendar.getInstance();
    Timeframe timeframe = new Timeframe(-1,-1);
    int duration;
    int specialtyId;
    int serviceId;
    int patientId;
    int doctorId;
    int clinicId;
    Spinner serviceSpinnerCreate;
    List<Service> services;
    List<String> serviceNames = new ArrayList<String>();
    Button confirmButton;
    Button cancelButton;
    long accountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_follow_up);

        SessionManager sessionManager = new SessionManager(this);
        try {
            this.patientId = (int)sessionManager.getAccountId();
            this.accountId = sessionManager.getAccountId();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle b = getIntent().getExtras();
        Appointment app = b.getParcelable("appFollowUp");
        TextView country = (TextView) findViewById(R.id.FollowUpCountry);
        country.setText(Container.getClinicManager().getClinicsByID(app.getClinicId(),this).get(0).getCountry());
        TextView location = (TextView) findViewById(R.id.FollowUpLocation);
        location.setText(Container.getAppointmentManager().getClinicNameByAppointment(app,this));
        TextView doctor = (TextView) findViewById(R.id.FollowUpDoctor);
        doctor.setText(Container.getAppointmentManager().getDoctorNameByAppointment(app,this));
        TextView specialty = (TextView) findViewById(R.id.FollowUpSpecialty);
        specialty.setText(Container.getAppointmentManager().getSpecialtyNameByAppointment(app,this));


        this.specialtyId = app.getSpecialtyId();
        this.serviceId = app.getServiceId();
        this.doctorId = app.getDoctorId();
        this.clinicId = app.getClinicId();
        //service spinner
        serviceSpinnerCreate = (Spinner) findViewById(R.id.CreateFollowUpServices);
        services = Container.getServiceManager().getServicesBySpecialtyID(app.getSpecialtyId(), this);

        for (Service s : services) {
            serviceNames.add(s.getName());
        }
        Log.d("SSize", "" + serviceNames);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, serviceNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        serviceSpinnerCreate.setAdapter(dataAdapter);
        serviceSpinnerCreate.setOnItemSelectedListener(this);

        confirmButton = (Button)findViewById(R.id.ConfirmCreateFollowUpAppt);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onClickCreateAppointment();
            }
        });

        cancelButton = (Button)findViewById(R.id.CancelCreateFollowUpAppt);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_follow_up, menu);
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
        String service = String.valueOf(serviceSpinnerCreate.getSelectedItem());

        for (Service s : services) {
            if (service.equals(s.getName())) {
                serviceId = s.getId();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void onDateButtonSelected(View v){
        int id = v.getId();
        Bundle bundle = new Bundle();
        bundle.putInt("VIEW_ID",id);
        FragmentManager manager = getFragmentManager();
        DatePickerFragment datepicker = new DatePickerFragment();
        datepicker.setArguments(bundle);
        datepicker.show(manager, "Datepicker");
    }

    @Override
    public void DatePickerFragmentToActivity(int date, int month, int year, Button button)
    {
        super.DatePickerFragmentToActivity(date,month,year,button);
        apptDate.set(year,month,date);
    }

    public void onTimeButtonSelected(View v){
        if (this.apptDate.getTimeInMillis()==0){
            Toast.makeText(this, "Please select a date ", Toast.LENGTH_SHORT).show();
            return;
        }
        FragmentManager manager = getFragmentManager();
        TimePickerFragment timepicker = new TimePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(TimePickerFragment.DATA, getTimePickerItems());     // Require ArrayList
        bundle.putInt(TimePickerFragment.SELECTED, 0);
        timepicker.setArguments(bundle);
        timepicker.show(manager, "TimePicker");
    }

    @Override
    public void selectItem(int position) {
        Button btn = (Button) findViewById(R.id.timepicker);
        if(getTimePickerItems().get(position)!="N/A"){
            btn.setText(getTimePickerItems().get(position));
            this.timeframe = new Timeframe(getTimePickerItems().get(position));
        } else {
            resetTimePicker();
        }
    }
    private ArrayList<String> getTimePickerItems() {
        ArrayList<String> availableSlots = new ArrayList<String>();
        //Toast.makeText(this, this.date.getTime().toString(), Toast.LENGTH_SHORT).show();
        this.duration = Container.getServiceManager().getServiceDurationbyID(this.serviceId, this);
        List<Timeframe> temp = Container.getAppointmentManager().getAvailableTimeSlot(this.apptDate, this.patientId, this.doctorId, this.clinicId, 18, 42, duration, this);

        availableSlots.add("N/A");
        for (Timeframe t:temp){
            availableSlots.add(t.getTimeLine());
        }
        return availableSlots;
    }
    public void resetTimePicker(){
        Button btn = (Button) findViewById(R.id.timepicker);
        btn.setText("TAP TO CHOOSE TIME");
        this.timeframe = new Timeframe(-1,-1);
    }
    public void onClickCreateAppointment() {
        //AppointmentManager appointmentManager = new AppointmentManager();
        //Toast.makeText(this, "Button clicked.", Toast.LENGTH_SHORT).show();
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, 1);

        if (this.apptDate.getTimeInMillis()==0){
            Toast.makeText(this, "Please select a date ", Toast.LENGTH_SHORT).show();
        } else if (this.timeframe.getStartTime()<0){
            Toast.makeText(this, "Please select a time. ", Toast.LENGTH_SHORT).show();
        } else {
            this.apptDate.set(Calendar.HOUR_OF_DAY, (this.timeframe.getStartTime() / 2));
            this.apptDate.set(Calendar.MINUTE,30*(this.timeframe.getStartTime()%2));
            if (this.apptDate.compareTo(currentDate)<0){
                Toast.makeText(this, "You must book at least 24 hours in advance. ", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("CalendarCreateC",this.apptDate.toString());
                Appointment appointment = new Appointment(this.patientId, this.clinicId, this.specialtyId, this.serviceId, this.doctorId, -1, this.apptDate, this.timeframe, Container.getServiceManager().getServicePreappbyID(this.serviceId, this));

                Log.d("AppCreateFUPSPe",""+appointment.getSpecialtyId());
                Log.d("AppCreateFUPSer",""+appointment.getServiceId());
                Log.d("AppCreateFUPDoc",""+appointment.getDoctorId());
                Log.d("CalendarCreateA", appointment.getDate().toString());
                long res = Container.getAppointmentManager().createAppointment(appointment, this);
                if (res == -1) {
                    Toast.makeText(this,"Appointment creation failed", Toast.LENGTH_SHORT).show();

                } else {
                    AlarmSetter malarm = new AlarmSetter();
                    Account account = new Account();
                    try {
                        account = Container.getAccountManager().getAccountById(accountId, this);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    malarm.setAlarm(getApplicationContext(),appointment,account);
                    /*Notification notification = new Notification();
                    notification.buildNotification(this, "Appointment created.",appointment);*/
                    Intent goToMain = new Intent(this, MainActivity.class);
                    startActivity(goToMain);

                }
            }
        }
    }

}
