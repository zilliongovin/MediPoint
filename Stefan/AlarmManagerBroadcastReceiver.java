package com.android.notification;

        import java.text.Format;
        import java.text.SimpleDateFormat;
        import java.util.Date;

        import android.app.Activity;
        import android.app.AlarmManager;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.Bundle;
        import android.os.Parcel;
        import android.os.Parcelable;
        import android.os.PowerManager;
        import android.provider.Settings;
        import android.support.v4.app.NotificationManagerCompat;
        import android.telephony.SmsManager;
        import android.util.Log;
        import android.widget.Toast;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver  {

    final public static String ONE_TIME = "onetime";
    /* public static final Parcelable.Creator<AlarmManagerBroadcastReceiver> CREATOR = new Creator<AlarmManagerBroadcastReceiver>() {
        public AlarmManagerBroadcastReceiver createFromParcel(Parcel source) {
            AlarmManagerBroadcastReceiver mAMBR = new AlarmManagerBroadcastReceiver();
            mAMBR.email = source.readString();
            mAMBR.subject = source.readString();
            mAMBR.message = source.readString();
            return mAMBR;
        }

        public AlarmManagerBroadcastReceiver[] newArray(int size) {
            return new AlarmManagerBroadcastReceiver[size];
        }
    };

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(email);
            parcel.writeString(subject);
            parcel.writeString(message);
        }

        @Override
        public int describeContents() {
            return 0;
        }*/
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
        //Acquire the lock
        wl.acquire();


        //You can do the processing here.
       /*Bundle extras = intent.getExtras();
        StringBuilder msgStr = new StringBuilder();
        if(extras != null && extras.getBoolean(ONE_TIME, Boolean.FALSE)){
            //Make sure this intent has been sent by the one-time timer button.
            msgStr.append("One time Timer : ");
        }
        Format formatter = new SimpleDateFormat("hh:mm:ss a");
        msgStr.append(formatter.format(new Date()));
        Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();*/

        //Release the lock
        Bundle extras = intent.getExtras();
        String email = extras.getString("email");
        String subject = extras.getString("subject");
        String messageBody = extras.getString("messageBody");
        String number = extras.getString("mobile");
        String text = extras.getString("text");
        String title = extras.getString("title");
        String body = extras.getString("body");

        Integer branch = extras.getInt("notifiedByEmail");
        switch(branch) {
            case 0:
                Email mEmail = new Email();
                mEmail.setAll(email, subject, messageBody);
                mEmail.sendMail();
            case 1:
                String message = null;
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        message = "Message sent!";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        message = "Error. Message not sent.";
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        message = "Error: No service.";
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        message = "Error: Null PDU.";
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        message = "Error: Radio off.";
                        break;
                }
                SMS mSMS = new SMS();
                mSMS.sendSms(context, number, text, false);
            case 2:
                Notification mNotification = new Notification();
                mNotification.buildNotification(context,title,body);
        }
        wl.release();
    }

    public void notifyEmail(Context context,long time,String email,String subject,String messageBody) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra("email",email);
        intent.putExtra("subject",subject);
        intent.putExtra("messageBody",messageBody);
        intent.putExtra("notifiedByEmail",0);

        /*intent.putExtra(ONE_TIME, Boolean.FALSE);*/
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //After after 5 seconds
        /*am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 5 , pi);*/
        /*am.setTime(System.currentTimeMillis()+2);*/

        am.set(AlarmManager.RTC_WAKEUP, time, pi);

    }

    public void notifySMS(Context context,long time,String mobile, String text) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra("notifiedByEmail",1);
        intent.putExtra("mobile",mobile);
        intent.putExtra("text",text);

        /*intent.putExtra(ONE_TIME, Boolean.FALSE);*/
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //After after 5 seconds
        /*am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 5 , pi);*/
        /*am.setTime(System.currentTimeMillis()+2);*/

        am.set(AlarmManager.RTC_WAKEUP, time, pi);

    }



    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    public void showNotification(Context context,long time,String title, String body){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra("notifiedByEmail",2);
        intent.putExtra("title",title);
        intent.putExtra("body",body);

        /*intent.putExtra(ONE_TIME, Boolean.FALSE);*/
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //After after 5 seconds
        /*am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 5 , pi);*/
        /*am.setTime(System.currentTimeMillis()+2);*/

        am.set(AlarmManager.RTC_WAKEUP, time, pi);


    }

}

