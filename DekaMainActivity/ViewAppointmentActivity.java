package com.djzass.mediapp.apptlistview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewAppointment extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        Bundle b = getIntent().getExtras();
        Appointment app = b.getParcelable("appObj");

        ImageView specialtyIcon = (ImageView) convertView.findViewById(R.id.view_specialty_icon);
        specialtyIcon.setImageResource(getImageId(app.getName()));
        TextView appointmentService = (TextView) findViewById(R.id.viewService);
        appointmentService.setText(app.getService());
        TextView appointmentStatus = (TextView) findViewById(R.id.viewStatus);
        appointmentStatus.setText(app.getStatus());
        TextView appointmentSpecialty (TextView) findViewById(R.id.viewSpecialty);
        appointmentSpecialty.setText(app.getSpecialty());

        TextView appointmentDate = (TextView) findViewById(R.id.viewDate);
        appointmentDate.setText(app.getDate());
        TextView appointmentTime = (TextView) findViewById(R.id.viewTime);
        appointmentTime.setText(app.getTime());
        TextView appointmentClinic = (TextView) findViewById(R.id.viewLocation);
        appointmentClinic.setText(app.getClinic());
        TextView appointmentDoctor = (TextView) findViewById(R.id.viewDoctor);
        appointmentDoctor.setText(app.getDoctor());

        TextView preAppointmentActions = (TextView) findViewById(R.id.viewPreAppointmentActions);
        preAppointmentActions.setText(app.getPreAppointmentActions());
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

    public int getImageId(String specialtyName){
        if (specialtyName.equalsIgnoreCase("ENT"))
            return R.mipmap.ear;
        else if (specialtyName.equalsIgnoreCase("Dental"))
            return R.mipmap.dental;
        else if (specialtyName.equalsIgnoreCase("Women's Health Services"))
            return R.mipmap.female;
        return R.mipmap.icontp_medipoint;
    }

}

