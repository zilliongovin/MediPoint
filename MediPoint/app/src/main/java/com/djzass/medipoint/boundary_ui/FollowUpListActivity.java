package com.djzass.medipoint.boundary_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.djzass.medipoint.R;
import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.AppointmentManager;
import com.djzass.medipoint.logic_manager.Container;
import com.djzass.medipoint.logic_manager.SessionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


public class FollowUpListActivity extends Activity {

    private int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_list);

        SessionManager sessionManager = new SessionManager(this);
        try {
            this.patientId = (int)sessionManager.getAccountId();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Appointment> appointments = (ArrayList<Appointment>) Container.getAppointmentManager().sortByDate(Container.getAppointmentManager().getPatientRecentAppointments(this.patientId, Calendar.getInstance(), this));

        TextView tv = (TextView)findViewById(R.id.noPastAppoinment);
        if (appointments.size() > 0) {
            ListView apptList = (ListView)findViewById(R.id.followuplist);
            FollowUpAdapter apptAdapter = null;
            tv.setVisibility(View.GONE);
            try {
                apptAdapter = new FollowUpAdapter(this, appointments);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            apptList.setAdapter(apptAdapter);
            apptList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                    Appointment app = (Appointment) parent.getAdapter().getItem(position);
                    //Toast.makeText(getApplicationContext(), app.toString(), Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(getApplicationContext(), CreateFollowUpActivity.class);
                    in.putExtra("appFollowUp", app);
                    startActivity(in);
                        /*Toast.makeText(getApplicationContext(),
                                "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                                .show();*/
                }
            });
        }
        else {
            tv.setVisibility(View.VISIBLE);
        }
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
