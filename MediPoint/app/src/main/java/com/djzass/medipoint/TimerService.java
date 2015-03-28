
import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class TimerService extends Service {
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
    };
}




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