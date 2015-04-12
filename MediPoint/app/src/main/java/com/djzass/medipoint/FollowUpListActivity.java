package com.djzass.medipoint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.AppointmentManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Deka on 12/4/2015.
 */
public class FollowUpListActivity extends Activity {
    ArrayList<Appointment> appointments;
    int patientId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follow_up_activity);

        AppointmentManager appointmentManager = AppointmentManager.getInstance();
        SessionManager sessionManager = new SessionManager(this);
        try {
            this.patientId = (int)sessionManager.getAccountId();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        appointments = (ArrayList<Appointment>) appointmentManager.getPatientRecentAppointments(this.patientId, Calendar.getInstance(),this );

        ListView apptList = (ListView)findViewById(R.id.followuplist);
        AppointmentAdapter apptAdapter = null;
        try {
            apptAdapter = new AppointmentAdapter(this, appointments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        apptList.setAdapter(apptAdapter);
    }
}
