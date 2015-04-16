package com.djzass.medipoint.boundary_ui;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

import com.djzass.medipoint.R;
import com.djzass.medipoint.entity.Appointment;

/**
 * Created by Z480 on 4/4/2015.
 */

public class Notification {

    public void buildNotification(Context context,String body,Appointment appointment) {
        NotificationManagerCompat NM= NotificationManagerCompat.from(context);


        Intent intent = new Intent(context, ViewAppointmentActivity.class);
        intent.putExtra("appObj",appointment);
        PendingIntent pI = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        android.app.Notification.Builder notify = new android.app.Notification.Builder(context)
                //can be set differently like icon, sound etc
                .setContentTitle("Medipoint Reminder")
                .setContentText(body)
                .setSmallIcon(R.drawable.icon_medipoint)

                .setContentIntent(pI)
                .setAutoCancel(true);
        //initiate this
        NM.notify(0,notify.build());
    }
}
