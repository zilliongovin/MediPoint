package com.djzass.mediapp.apptlistview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        Calendar[] dateTimes = {new GregorianCalendar(1995, 8, 10, 10, 0), new GregorianCalendar(1995, 10, 9, 3, 2),
                new GregorianCalendar(1994, 10, 9, 11, 33), new GregorianCalendar(1993, 6, 7, 10, 2), new GregorianCalendar(1996, 10, 8, 9, 30),
                new GregorianCalendar(1995, 10, 9, 4, 2), new GregorianCalendar(1995, 10, 9, 2,4), new GregorianCalendar(1995, 10, 9, 4, 2),
                new GregorianCalendar(1995, 10, 9, 4, 5), new GregorianCalendar(1995, 10, 9 ,3 ,5)    };
        String[] apptName = { "General Consultation", "Wisdom Tooth Extraction", "Tooth filling", "Tumor Surgery", "Sore Throat",
                "Hemoteraphy", "Hearing Test", "Sinus Surgery", "Women Health's Consultatiton", "Audio Therapy" };
        String[] name = {"Alice", "Bob", "Cindy", "Daniel", "Ezra", "Farah", "George",
                "Hans", "Iris", "Jack", "Kelly"};
        String[] status = {"In Progress", "Pending", "Ongoing", "Cancelled", "Done"};
        String[] clinics = {"DjZass HealthCare Center", "Zjdass Medical Centre", "DassJz Clinic","JzDass Clinic Centre"};
        String[] country = {"Malaysia", "Singapore", "Thailand"};

        String s, cl, co;
        for (int i=0;i<10;i++){
            s = status[i % status.length].toUpperCase();
            cl = clinics[i % clinics.length];
            co = country[i % country.length];
            appointments.add(new Appointment(i, apptName[i], s, dateTimes[i], cl, co));
        }

        /*for (Appointment a: appointments){
            Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT).show();
        }*/

        ListView apptList = (ListView)findViewById(R.id.customListView);
        CustomAdapter apptAdapter = new CustomAdapter(this, appointments);
        apptList.setAdapter(apptAdapter);

        apptList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Appointment app = (Appointment) parent.getAdapter().getItem(position);
                //Toast.makeText(getApplicationContext(), app.toString(), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(), ViewAppointment.class);
                in.putExtra("appObj", app);
                startActivity(in);
                /*Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                        .show();*/
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
