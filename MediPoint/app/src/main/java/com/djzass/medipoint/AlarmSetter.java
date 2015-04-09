package com.djzass.medipoint;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.djzass.medipoint.entity.Account;
import com.djzass.medipoint.entity.Appointment;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Z480 on 4/6/2015.
 */
public class AlarmSetter {
    public void setAlarm(Context context,Appointment appointment,Account account){
        Intent reminderIntent = new Intent(context,AlarmBroadcastReceiver.class);
        reminderIntent.putExtra("appointment",appointment);
        reminderIntent.putExtra("account", account);
        int reminderId = appointment.getId();
        PendingIntent reminder = PendingIntent.getBroadcast(context,reminderId,reminderIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (appointment.getDate().getTimeInMillis() - 24 * 3600 * 1000 >= cal.getTimeInMillis()) {
            am.set(AlarmManager.RTC_WAKEUP, appointment.getDate().getTimeInMillis() - 24 * 3600 * 1000, reminder);
        }

    }

    public void cancelAlarm(Context context,Appointment appointment){
        Intent cancelIntent = new Intent(context,AlarmBroadcastReceiver.class);
        int reminderId = appointment.getId();
        PendingIntent sender = PendingIntent.getBroadcast(context, reminderId, cancelIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(sender);
    }
}
