package com.djzass.medipoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.AppointmentManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


public class FollowUpListActivity extends Activity {

    private int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_list);
        AppointmentManager appointmentManager = AppointmentManager.getInstance();
        SessionManager sessionManager = new SessionManager(this);
        try {
            this.patientId = (int)sessionManager.getAccountId();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Appointment> appointments = (ArrayList<Appointment>) appointmentManager.getPatientRecentAppointments(this.patientId, Calendar.getInstance(), this);
        //appointments = appointmentManager.getAppointments(this.getApplicationContext())
        ListView apptList = (ListView)findViewById(R.id.followuplist);
        FollowUpAdapter apptAdapter = null;
        try {
            apptAdapter = new FollowUpAdapter(this, appointments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        apptList.setAdapter(apptAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_follow_up_list, menu);
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
