package com.djzass.medipoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.Container;


public class CreateFollowUpActivity extends Activity {

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
}
