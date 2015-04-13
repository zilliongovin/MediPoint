package com.djzass.medipoint;

import android.app.Activity;
import android.app.AlarmManager;
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

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.Container;

/**
 * Created by Zillion Govin on 4/4/2015.
 */

public class ViewAppointmentActivity extends Activity {
    private Appointment app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        Bundle b = getIntent().getExtras();
        app = b.getParcelable("appObj");
        Log.d("ViewApp",app.getPreAppointmentActions());
        Log.d("ViewService",Container.getServiceManager().getServicesByID(app.getServiceId(), this).get(0).print());

        TextView specialtyName = (TextView) findViewById(R.id.viewSpecialty);
        specialtyName.setText(Container.getAppointmentManager().getSpecialtyNameByAppointment(app,this));
        TextView serviceName = (TextView) findViewById(R.id.viewService);
        serviceName.setText(Container.getAppointmentManager().getServiceNameByAppointment(app,this));
        TextView appointmentStatus = (TextView) findViewById(R.id.viewStatus);
        appointmentStatus.setText(Container.getAppointmentManager().getStatus(app));
        TextView appointmentDate = (TextView) findViewById(R.id.viewDate);
        appointmentDate.setText(app.getDateString());
        TextView appointmentTime = (TextView) findViewById(R.id.viewTime);
        appointmentTime.setText(app.getTimeString());
        TextView appointmentLocation = (TextView) findViewById(R.id.viewLocation);
        appointmentLocation.setText(Container.getAppointmentManager().getClinicNameByAppointment(app,this));
        TextView doctorName = (TextView) findViewById(R.id.viewDoctor);
        doctorName.setText(Container.getAppointmentManager().getDoctorNameByAppointment(app,this));
        TextView preAppointmentActions = (TextView) findViewById(R.id.viewPreAppointmentActions);
        preAppointmentActions.setText(app.getPreAppointmentActions());
        ImageView specialtyIcon = (ImageView)findViewById(R.id.specialty_icon);
        specialtyIcon.setImageResource(getImageId(Container.getAppointmentManager().getSpecialtyNameByAppointment(app,this)));
    }

    public int getImageId(String specialtyName){
        if (specialtyName.equalsIgnoreCase("ENT"))
            return R.mipmap.ear;
        else if (specialtyName.equalsIgnoreCase("Dental Services"))
            return R.mipmap.dental;
        else if (specialtyName.equalsIgnoreCase("Women's Health"))
            return R.mipmap.female;
        return R.mipmap.icontp_medipoint;
    }

    public void ViewApptEdit(View view)
    {
        Intent in = new Intent(getApplicationContext(), EditAppointmentActivity.class);
        in.putExtra("appFromView", getIntent().getExtras().getParcelable("appObj"));
        startActivity(in);
    }

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

    private void performDelete(){
        Container.getAppointmentManager().cancelAppointment(app, this);
        AlarmSetter mAlarm = new AlarmSetter();
        mAlarm.cancelAlarm(getApplicationContext(),app);
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void cancelAppointment(){
        Appointment appointment = new Appointment();
        AlarmSetter malarm = new AlarmSetter();
        malarm.cancelAlarm(this,appointment);
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


    private AlertDialog ConfirmDelete(){
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Confirm action")
                .setMessage("Are you sure you want to delete this appointment?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
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
