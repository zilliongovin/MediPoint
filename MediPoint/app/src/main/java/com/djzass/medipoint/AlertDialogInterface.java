package com.djzass.medipoint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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

    /*
    public void unequalPassword(){
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.create().show();
    }
    */

}
