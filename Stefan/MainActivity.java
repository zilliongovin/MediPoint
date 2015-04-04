package com.android.notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.AsyncQueryHandler;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends Activity {
    /*public static final String username = "djzass15@gmail.com";
    public static final String password = "medipoint";
    private EditText emailEdit;
    private EditText subjectEdit;
    private EditText messageEdit;
    private AlarmManagerBroadcastReceiver alarm;
    private static final String SMS_SENT="My App";
    private static final int MAX_SMS_MESSAGE_LENGTH =160;
    private static final short SMS_PORT=8901;
    private static final String SMS_DELIVERED ="your app";
    private static final String MY_ACCOUNT_NAME = "stefan";
    public static final String PAR_KEY = "par_key";
    private PendingIntent pendingIntent;*/


    private String email,message,subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*emailEdit = (EditText) findViewById(R.id.email);
        subjectEdit = (EditText) findViewById(R.id.subject);
        messageEdit = (EditText) findViewById(R.id.message);
        Button sendButton = (Button) findViewById(R.id.sendButton);*/

        /*String email = "deka108@gmail.com";
        String subject = "email";
        String messageBody = "dek yg ini gw coba kalo ditimpuk kirim lg gimana haha";
        AlarmManagerBroadcastReceiver mAMBR = new AlarmManagerBroadcastReceiver();
        Context context =getApplicationContext();
        mAMBR.notifyEmail(context, System.currentTimeMillis()+3600*3*1000,email,subject,messageBody);*/
        /*String mobile = "+6598068849";
        String text = "testing2 haha smoga bisa";
        AlarmManagerBroadcastReceiver mAMBR = new AlarmManagerBroadcastReceiver();
        Context context =getApplicationContext();
        mAMBR.notifySMS(context, System.currentTimeMillis(),mobile,text);*/
        String title = "Medipoint";
        String body = "Appointment created";

        AlarmManagerBroadcastReceiver mAMBR = new AlarmManagerBroadcastReceiver();
        mAMBR.showNotification(getApplicationContext(),System.currentTimeMillis(),title,body);







        /*Intent mIntent =  new Intent(this, ParcelableDemo.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(PAR_KEY,mAMBR);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);*/



        /**String[] projection =
                new String[]{
                        CalendarContract.Calendars._ID,
                        CalendarContract.Calendars.NAME,
                        CalendarContract.Calendars.ACCOUNT_NAME,
                        CalendarContract.Calendars.ACCOUNT_TYPE};
        Cursor calCursor =
                getContentResolver().
                        query(CalendarContract.Calendars.CONTENT_URI,
                                projection,
                                CalendarContract.Calendars.VISIBLE + " = 1",
                                null,
                                CalendarContract.Calendars._ID + " ASC");
        if (calCursor.moveToFirst()) {
            do {
                long id = calCursor.getLong(0);
                String displayName = calCursor.getString(1);
            } while (calCursor.moveToNext());
        }*/

        /*ContentValues values = new ContentValues();
        values.put(
                CalendarContract.Calendars.ACCOUNT_NAME,
                MY_ACCOUNT_NAME);
        values.put(
                CalendarContract.Calendars.ACCOUNT_TYPE,
                CalendarContract.ACCOUNT_TYPE_LOCAL);
        values.put(
                CalendarContract.Calendars.NAME,
                "Medipoint Calendar");
        values.put(
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                "Medipoint Calendar");
        values.put(
                CalendarContract.Calendars.CALENDAR_COLOR,
                0xffff0000);
        values.put(
                CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
                CalendarContract.Calendars.CAL_ACCESS_OWNER);
        values.put(
                CalendarContract.Calendars.OWNER_ACCOUNT,
                true);
        values.put(
                CalendarContract.Calendars.CALENDAR_TIME_ZONE,
                "Singapore");
        values.put(
                CalendarContract.Calendars.SYNC_EVENTS,
                1);
        Uri.Builder builder =
                CalendarContract.Calendars.CONTENT_URI.buildUpon();
        builder.appendQueryParameter(
                CalendarContract.Calendars.ACCOUNT_NAME,
                "medipoint@ntu.com");
        builder.appendQueryParameter(
                CalendarContract.Calendars.ACCOUNT_TYPE,
                CalendarContract.ACCOUNT_TYPE_LOCAL);
        builder.appendQueryParameter(
                CalendarContract.CALLER_IS_SYNCADAPTER,
                "true");
        Uri uri2 =
                getContentResolver().insert(builder.build(), values);

    Calendar cal = new GregorianCalendar(2015, 3, 31);
    cal.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    long start = cal.getTimeInMillis();
    /*ContentValues values = new ContentValues();*/
    /*long calId= getCalendarId();
    values.put(CalendarContract.Events.DTSTART, System.currentTimeMillis());
    values.put(CalendarContract.Events.DTEND, start);
    values.put(CalendarContract.Events.RRULE,  "FREQ=YEARLY" /*"FREQ=DAILY;COUNT=1;BYDAY=MO,;WKST=MO");*/
    /*values.put(CalendarContract.Events.TITLE, "Medipoint");
    values.put(CalendarContract.Events.EVENT_LOCATION, "Singapore");
    values.put(CalendarContract.Events.CALENDAR_ID, calId);
    values.put(CalendarContract.Events.EVENT_TIMEZONE, "Singapore");
    values.put(CalendarContract.Events.DESCRIPTION,
            "The agenda or some description of the event");
// reasonable defaults exist:
    values.put(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
    values.put(CalendarContract.Events.SELF_ATTENDEE_STATUS,
    CalendarContract.Events.STATUS_CONFIRMED);
    values.put(CalendarContract.Events.ALL_DAY, 1);
    values.put(CalendarContract.Events.ORGANIZER, "Medipoint@gmail.com");
    values.put(CalendarContract.Events.GUESTS_CAN_INVITE_OTHERS, 1);
    values.put(CalendarContract.Events.GUESTS_CAN_MODIFY, 1);
    values.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
    Uri uri =
            getContentResolver().
                    insert(CalendarContract.Events.CONTENT_URI, values);
    long eventId = new Long(uri.getLastPathSegment());
// adding an attendee:
    /*values.clear();
    values.put(CalendarContract.Attendees.EVENT_ID, eventId);
    values.put(CalendarContract.Attendees.ATTENDEE_TYPE, CalendarContract.Attendees.TYPE_REQUIRED);
    values.put(CalendarContract.Attendees.ATTENDEE_NAME, "Douglas Adams");
    values.put(CalendarContract.Attendees.ATTENDEE_EMAIL, "d.adams@zaphod-b.com");
    getContentResolver().insert(CalendarContract.Attendees.CONTENT_URI, values);*/
// adding a reminder:
     /*values.clear();
     values.put(CalendarContract.Reminders.EVENT_ID, eventId);
     values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
     values.put(CalendarContract.Reminders.MINUTES, 1000 );
    getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, values);*/


        /*String[] selArgs =
        new String[]{Long.toString(eventId)};
        int deleted =
        getContentResolver().
        delete(
                CalendarContract.Events.CONTENT_URI,
                CalendarContract.Events._ID + " =? ",
                selArgs);*/




        /*IcsCalendarHelper.initActivityObj(this);
        Calendar cal=Calendar.getInstance();

        IcsCalendarHelper.IcsMakeNewCalendarEntry("A Test Event from android app","test app","Germany", cal.getTimeInMillis(),cal.getTimeInMillis()+60*60*1000,1,1,100,0);*/

        /*Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "A Test Event from android app");
        startActivity(intent);*/


        /**long calID = 3;
        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2015, 3, 29, 7, 30);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2015, 3, 30, 8, 45);
        endMillis = endTime.getTimeInMillis();

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        /*values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, "Jazzercise");
        values.put(CalendarContract.Events.DESCRIPTION, "Group workout");
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles");
        values.put(CalendarContract.Reminders.DTSTART, startMillis);
        values.put(CalendarContract.Reminders.DTEND, endMillis);
        values.put(CalendarContract.Reminders.TITLE, "Jazzercise");
        values.put(CalendarContract.Reminders.DESCRIPTION, "Group workout");
        values.put(CalendarContract.Reminders.CALENDAR_ID, calID);
        values.put(CalendarContract.Reminders.EVENT_TIMEZONE, "America/Los_Angeles");
        AsyncQueryHandler handler = new MyHandler(getContentResolver());
        handler.startInsert(0, null, CalendarContract.Reminders.CONTENT_URI, values);*/



        //Pushbar notif non deprecated
       /**Intent intent = new Intent(getApplicationContext(), MainActivity.class);
       NotificationManager NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
       PendingIntent pending=PendingIntent.getActivity(
                this,0, intent,0);
        String subject = "Medipoint";
        String body = subject+" Appointment created!";
        Context mContext= this.getApplicationContext();
        Notification notify = new Notification.Builder(mContext)
            .setContentTitle(subject)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.ic_lock_idle_charging)
            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
            .setContentIntent(pending)
            .build();
        NM.notify(0, notify);*/

        //pushbar notif
        /*NotificationManager NM;
        String title = "Notification";
        String subject ="Medipoint";
        String body = "Appointment created!";
        NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification(android.R.drawable.stat_notify_more,title,System.currentTimeMillis());
        PendingIntent pending=PendingIntent.getActivity(
                getApplicationContext(),0, new Intent(),0);
        notify.setLatestEventInfo(getApplicationContext(),subject,body,pending);
        NM.notify(0, notify);*/

        /*Email email2 = new Email(username,password);
        email = "stefanindriawan@gmail.com" ;
        subject= "hahahaha";
        message = "testing new class";
        email2.sendMail(email, subject, message);*/

        /*sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Email email2 = new Email(username,password);
                String email = emailEdit.getText().toString();
                String subject = subjectEdit.getText().toString();
                String message = messageEdit.getText().toString();
                email2.sendMail(email, subject, message);
            }
        });*/

        /**btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                alarming = new Alarm();
                alarm = new AlarmManagerBroadcastReceiver();
                alarming.startRepeatingTimer(view,alarm);
            }
        });*/
        /*alarm= new AlarmManagerBroadcastReceiver();*

      /*  //pushbar notif
       NotificationManager NM;
        String title = "Notification";
        String subject = "Medipoint";
        String body = "Appointment created!";
        NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification(android.R.drawable.stat_notify_more, title, System.currentTimeMillis());
        PendingIntent pending = PendingIntent.getActivity(
                getApplicationContext(), 0, new Intent(), 0);
        notify.setLatestEventInfo(getApplicationContext(), subject, body, pending);
        NM.notify(0, notify);*/

        //email
        /*Session session1=createSessionObject();
        try {
            String email = "deka108@gmail.com";
            Message message = createMessage(email, "Medipoint ", "appointment created", session1);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        //sms but not automatically
        /*Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.putExtra("address", "+65 98068849");
        i.putExtra("sms_body", "Greetings!");
        i.setType("vnd.android-dir/mms-sms");
        startActivity(i);*/

        /*registerReceiver(receiver, new IntentFilter(SMS_SENT));*/
        /*sendSms("+6582342891","testing",false);*/
    }



    /*private void sendMail(String email, String subject, String messageBody) {
        Session session = createSessionObject();

        try {
            Message message = createMessage(email, subject, messageBody, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private Message createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("MediPoint.com", "MediPoint"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/



   /** public void startRepeatingTimer(View view){
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.SetAlarm(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelRepeatingTimer(View view){
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.CancelAlarm(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }

    }*/




    /**private void sendSms(String phonenumber,String message, boolean isBinary)
    {
        SmsManager manager = SmsManager.getDefault();

        PendingIntent piSend = PendingIntent.getBroadcast(this, 0, new Intent(SMS_SENT), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(this, 0, new Intent(SMS_DELIVERED), 0);

        if(isBinary)
        {
            byte[] data = new byte[message.length()];

            for(int index=0; index<message.length() && index < MAX_SMS_MESSAGE_LENGTH; ++index)
            {
                data[index] = (byte)message.charAt(index);
            }

            manager.sendDataMessage(phonenumber, null, (short) SMS_PORT, data,piSend, piDelivered);
        }
        else
        {
            int length = message.length();

            if(length > MAX_SMS_MESSAGE_LENGTH)
            {
                ArrayList<String> messagelist = manager.divideMessage(message);

                manager.sendMultipartTextMessage(phonenumber, null, messagelist, null, null);
            }
            else
            {
                manager.sendTextMessage(phonenumber, null, message, piSend, piDelivered);
            }
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
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
        }
    };*/

    /*private long getCalendarId() {
        String[] projection = new String[]{CalendarContract.Calendars._ID};
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?))";
        // use the same values as above:
        String[] selArgs =
        new String[]{
                MY_ACCOUNT_NAME,
                CalendarContract.ACCOUNT_TYPE_LOCAL};
        Cursor cursor =
        getContentResolver().
                query(
                        CalendarContract.Calendars.CONTENT_URI,
                        projection,
                        selection,
                        selArgs,
                        null);
        if (cursor.moveToFirst()) {
            return cursor.getLong(0);
        }
        return -1;
    }*/

}