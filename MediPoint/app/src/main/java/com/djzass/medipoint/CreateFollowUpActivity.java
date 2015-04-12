package com.djzass.medipoint;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.logic_manager.Container;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CreateFollowUpActivity extends onDataPass implements SelectionListener{

    Calendar apptDate = Calendar.getInstance();
    Timeframe timeframe = new Timeframe(-1,-1);
    int duration;
    int serviceId;
    int patientId;
    int doctorId;
    int clinicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_follow_up);

        Bundle b = getIntent().getExtras();
        Appointment app = b.getParcelable("appFollowUp");
        TextView country = (TextView) findViewById(R.id.FollowUpCountry);
        country.setText(Container.getClinicManager().getClinicsByID(app.getId(),this).get(0).getCountry());
        TextView location = (TextView) findViewById(R.id.FollowUpLocation);
        location.setText(Container.getAppointmentManager().getClinicNameByAppointment(app,this));
        TextView doctor = (TextView) findViewById(R.id.FollowUpDoctor);
        doctor.setText(Container.getAppointmentManager().getDoctorNameByAppointment(app,this));
        TextView specialty = (TextView) findViewById(R.id.FollowUpSpecialty);
        specialty.setText(Container.getAppointmentManager().getSpecialtyNameByAppointment(app,this));
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
        apptDate.set(date,month,year);
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

}
