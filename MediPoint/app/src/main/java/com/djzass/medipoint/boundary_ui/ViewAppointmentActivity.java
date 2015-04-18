package com.djzass.medipoint.boundary_ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.djzass.medipoint.R;
import com.djzass.medipoint.entity.Account;
import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.AlarmSetter;
import com.djzass.medipoint.logic_manager.Container;
import com.djzass.medipoint.logic_manager.SessionManager;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by Deka on 4/4/2015.
 *
 * @author Deka
 * @since 2015
 * @version 1.0
 *
 * @see android.app.Activity
 */
public class ViewAppointmentActivity extends Activity {
    private Appointment app;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        Bundle b = getIntent().getExtras();
        app = b.getParcelable("appObj");

        TextView specialtyName = (TextView) findViewById(R.id.viewSpecialty);
        specialtyName.setText(Container.getAppointmentManager().getSpecialtyNameByAppointment(app,this));
        TextView serviceName = (TextView) findViewById(R.id.viewService);
        serviceName.setText(Container.getAppointmentManager().getServiceNameByAppointment(app, this));
        TextView appointmentStatus = (TextView) findViewById(R.id.viewStatus);
        appointmentStatus.setText(Container.getAppointmentManager().getStatus(app));
        TextView appointmentDate = (TextView) findViewById(R.id.viewDate);
        appointmentDate.setText(app.getDateString());
        TextView appointmentTime = (TextView) findViewById(R.id.viewTime);
        appointmentTime.setText(app.getTimeString());
        TextView appointmentLocation = (TextView) findViewById(R.id.viewLocation);
        appointmentLocation.setText(Container.getAppointmentManager().getClinicNameByAppointment(app, this));
        TextView doctorName = (TextView) findViewById(R.id.viewDoctor);
        doctorName.setText(Container.getAppointmentManager().getDoctorNameByAppointment(app, this));
        TextView preAppointmentActions = (TextView) findViewById(R.id.viewPreAppointmentActions);
        preAppointmentActions.setText(app.getPreAppointmentActions());
        ImageView specialtyIcon = (ImageView)findViewById(R.id.specialty_icon);
        specialtyIcon.setImageResource(getImageId(Container.getAppointmentManager().getSpecialtyNameByAppointment(app,this)));
    }

    /**
     *
     * @param specialtyName
     * @return
     */
    public int getImageId(String specialtyName){
        if (specialtyName.equalsIgnoreCase("ENT"))
            return R.mipmap.ear;
        else if (specialtyName.equalsIgnoreCase("Dental Services"))
            return R.mipmap.dental;
        else if (specialtyName.equalsIgnoreCase("Women's Health"))
            return R.mipmap.female;
        return R.mipmap.icontp_medipoint;
    }

    /**
     *
     * @param view
     */
    public void ViewApptEdit(View view)
    {
        Intent in = new Intent(getApplicationContext(), EditAppointmentActivity.class);
        in.putExtra("appFromView", getIntent().getExtras().getParcelable("appObj"));
        startActivity(in);
    }

    /**
     *
     * @param view
     */
    public void ViewApptDelete(View view){
        //Button delete
        /*AlertDialogInterface deleteApp = new AlertDialogInterface("Delete Appointment?",
                "Are you sure you want to delete this appointment?", this);
        deleteApp.deleteAppointmentDialog(Container.getAppointmentManager(),app,this);
        Container.getAppointmentManager().cancelAppointment(app, this);
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);*/
        AlertDialog diaBox = ConfirmDelete();
        diaBox.show();
    }


    /**
     *
     */
    private void performDelete(){
        Container.getAppointmentManager().cancelAppointment(app, this);

        SessionManager sessionMgr = new SessionManager(this);
        long accountId = 0;
        try {
            accountId = sessionMgr.getAccountId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Account account = new Account();
        try {
            account = Container.getAccountManager().getAccountById(accountId,this);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AlarmSetter mAlarm = new AlarmSetter();
        mAlarm.cancelAlarm(getApplicationContext(),app,account);
        Toast.makeText(this, "Appointment deleted", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_call:
                Intent editIntent= new Intent(getApplicationContext(),EditAppointmentActivity.class);
                startActivity(editIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *
     * @return
     */
    private AlertDialog ConfirmDelete(){
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Confirm action")
                .setMessage("Are you sure you want to delete this appointment?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton){
                        //your deleting code
                        performDelete();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }
}
