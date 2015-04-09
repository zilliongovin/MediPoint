package com.djzass.medipoint;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by Z480 on 4/4/2015.
 */

public class Notification {

    public void buildNotification(Context context,String body) {
        NotificationManagerCompat NM= NotificationManagerCompat.from(context);


        Intent intent = new Intent(context, ViewAppointmentActivity.class);
        PendingIntent pI = PendingIntent.getActivity(context,0,intent,0);

        android.app.Notification notify = new android.app.Notification.Builder(context)
                //can be set differently like icon, sound etc
                .setContentTitle("Medipoint Reminder")
                .setContentText(body)
                .setSmallIcon(R.drawable.icon_medipoint)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(pI)
                .build();
        //initiate this
        NM.notify(0, notify);
    }
}
