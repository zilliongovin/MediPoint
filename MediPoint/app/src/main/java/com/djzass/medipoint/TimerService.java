package com.djzass.medipoint;

import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.djzass.medipoint.MainActivity;

public class TimerService extends IntentService {

    Timer notificationTimer;
    //private int delay = 1000*60*5; //1000ms * 60s * 5, 5 mins
    private int delay = 1000*15; //15 seconds

    public TimerService() {
        super("timerservice");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(!MainActivity.SERVICE_TIMER_STARTED){
            notificationTimer = new Timer();
            notificationTimer.scheduleAtFixedRate(notificationTask, 1000L, 10 * 1000L);

            MainActivity.SERVICE_TIMER_STARTED = true;
        }

    }

    private TimerTask notificationTask = new TimerTask() {
        @Override
        public void run() {
        //posNotification();
            toastHandler.sendEmptyMessage(0);
            //call stuff here
        }
    };

    public void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped ...", Toast.LENGTH_SHORT).show();
    }

    private final Handler toastHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
        }
    };
}


    /*
    private static Timer timer = new Timer();
    private Context ctx;

    private int delay = 1000*60*5; //1000ms * 60s * 5, 5 mins

    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        ctx = this;
        startService();
    }

    private void startService()
    {
        timer.scheduleAtFixedRate(new mainTask(), 0, this.delay);
    }

    private class mainTask extends TimerTask
    {
        public void run()
        {
            toastHandler.sendEmptyMessage(0);
            //call stuff here
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped ...", Toast.LENGTH_SHORT).show();
    }

    private final Handler toastHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
        }
    };*/


/*
ADD THIS TO MANIFEST FOR SERVICE!
<!--
        Because android:exported is set to "false",
        the service is only available to this app.
        -->
<service
android:name=".TimerService"
        android:exported="false"/>

        */