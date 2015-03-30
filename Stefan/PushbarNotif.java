package com.android.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

/**
 * Created by Z480 on 3/30/2015.
 */
public class PushbarNotif {
    //deprecated one
    /*NotificationManager NM;
    String title = "Notification";
    String subject = "Medipoint";
    String body = "Appointment created!";
    NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    Notification notify = new Notification(android.R.drawable.stat_notify_more, title, System.currentTimeMillis());
    PendingIntent pending = PendingIntent.getActivity(
            getApplicationContext(), 0, new Intent(), 0);
    notify.setLatestEventInfo(getApplicationContext(), subject, body, pending);
    NM.notify(0, notify);*/

    //non deprecated one
    /**Intent intent = new Intent(getApplicationContext(), MainActivity.class);
     NotificationManager NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
     PendingIntent pending=PendingIntent.getActivity(
     this,0, intent,0);
     // Title
     String subject = "Medipoint";
     //Content
     String body = subject+" Appointment created!";
     //error search how to get context
     Context mContext= this.getApplicationContext();
     Notification notify = new Notification.Builder(mContext)
     //can be set differently like icon, sound etc
     .setContentTitle(subject)
     .setContentText(body)
     .setSmallIcon(android.R.drawable.ic_lock_idle_charging)
     .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
     .setContentIntent(pending)
     .build();
     //initiate this
     NM.notify(0, notify);*/
}
