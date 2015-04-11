package com.djzass.medipoint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.AppointmentManager;

/**
 * Created by Shreyas on 3/24/2015.
 */
public class AlertDialogInterface {
    AlertDialog.Builder dlgAlert;
    public AlertDialogInterface(String title, String message, Context context){
        dlgAlert = new AlertDialog.Builder(context);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setCancelable(true);
    }

    /*
    public void incompleteForm(){
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.create().show();
    }
    */

    public void AccountCreated(final Runnable func){
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                func.run();
            }

        });
        dlgAlert.create().show();
    }

    public void EditAppointmentConfirmed(final Runnable func){

        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                func.run();
            }
        });

        dlgAlert.setNegativeButton("Cancel", null);

        dlgAlert.create().show();

    }

    public void AccountAlreadyExists(final Runnable func){
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                func.run();
            }
        });
        dlgAlert.create().show();
    }

    public void BackToLogin(final Runnable insertPatientDOB,final Runnable goToLoginPage){
        dlgAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                insertPatientDOB.run();
                goToLoginPage.run();
            }
        });

        dlgAlert.setNegativeButton("NO",null);

        dlgAlert.create().show();

    }

    public void deleteAppointmentDialog(final AppointmentManager appointmentDeleter,final Appointment appointment, final Context context){
        dlgAlert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                appointmentDeleter.cancelAppointment(appointment,context);
            }
        });
        dlgAlert.setNegativeButton("NO",null);
        dlgAlert.create().show();
    }
}



