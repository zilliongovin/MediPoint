package com.djzass.medipoint;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.SmsManager;

import com.djzass.medipoint.entity.Account;
import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.Container;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Z480 on 4/6/2015.
 */
public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
        //Acquire the lock
        wl.acquire();
        Bundle extras = intent.getExtras();
        Appointment appointment = extras.getParcelable("appointment");
        Account account = extras.getParcelable("account");
        String extra = new String();
        if(appointment.getPreAppointmentActions().equalsIgnoreCase("none")){
            extra = " ";

        }
        else{
            extra = " Please do the following actions before you go to appointment:\n"
                    + appointment.getPreAppointmentActions();
        }
        String message = "Dear " + account.getName()+ ",\n"
                + Container.getAppointmentManager().getSpecialtyNameByAppointment(appointment, context) + " Appointment is on:\n"
                + appointment.getDateString() + " with " + Container.getAppointmentManager().getDoctorNameByAppointment(appointment, context) +".\n"
                + extra + "\n"
                +"This is an automated message.Please do not reply";
        if ( appointment !=null){
            Notification mNotification = new Notification();
            mNotification.buildNotification(context,"you have an appointment tomorrow",appointment);
            if(account.getNotifyEmail()==1) {
                Email mEmail = new Email();
                mEmail.sendMail(account.getEmail(),message);
            }
            if(account.getNotifySMS()==1) {
                SMS mSMS = new SMS();
                mSMS.sendSms(context, account.getPhoneNumber(),message,false);
            }

        }
        //Release the lock
        wl.release();
    }
}