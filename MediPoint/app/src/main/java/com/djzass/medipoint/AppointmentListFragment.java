package com.djzass.medipoint;

/**
 * Created by Zillion Govin on 4/4/2015.
 */

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.djzass.medipoint.entity.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Deka on 4/4/2015.
 */
public class AppointmentListFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    //ArrayList<AppointmentDummy> appointments;
    ArrayList<Appointment> appointments;
    private String[] list = { "New Appointment","New Referral", "New Follow-up"};
    Spinner newPage;
    public static AppointmentListFragment newInstance() {
        AppointmentListFragment fragment = new AppointmentListFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //appointments = new ArrayList<AppointmentDummy>();
        appointments = new ArrayList<Appointment>();
        /*
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
            appointments.add(new AppointmentDummy(i, apptName[i], s, dateTimes[i], cl, co));
        }*/
        //appointments = new ArrayList<Appointment>();
        //Appointment(appId, patientId, clinicId, specialtyId, serviceId, doctorId, date, timeframe, preAppointmentActions)
        //appointments.add(new Appointment(1, 1, 1, 1, 1, 1, 1, new GregorianCalendar(2015, 1, 1), new TimeFrame(18, 19), "Fasting"));
        //appointments.add(new Appointment(1, 1, 1, 1, 1, 1, 1, new GregorianCalendar(2015, 1, 15), new TimeFrame(18, 19), "Fasting"))
        //appointments.add(new Appointment(1, 1, 1, 1, 1, 1, 1, new GregorianCalendar(2015, 1, 21), new TimeFrame(18, 19), "Fasting"))
        //appointments.add(new Appointment(1, 1, 1, 1, 1, 1, 1, new GregorianCalendar(2015, 1, 26), new TimeFrame(18, 19), "Fasting"))
        //appointments.add(new Appointment(1, 1, 1, 1, 1, 1, 1, new GregorianCalendar(2015, 1, 30), new TimeFrame(18, 19), "Fasting"))
        /*for (Appointment a: appointments){
            Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT).show();
        }*/

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment_list, container, false);
        TextView tv = (TextView)view.findViewById(R.id.noAppointmentText);

        if (appointments.size() > 0){
            tv.setVisibility(view.GONE);
            ListView apptList = (ListView)view.findViewById(R.id.customListView);
            AppointmentAdapter apptAdapter = null;
            try {
                apptAdapter = new AppointmentAdapter(getActivity(), appointments);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            apptList.setAdapter(apptAdapter);

            apptList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                    AppointmentDummy app = (AppointmentDummy) parent.getAdapter().getItem(position);
                    //Appointment app = (Appointment) parent.getAdapter().getItem(position);
                    //Toast.makeText(getApplicationContext(), app.toString(), Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(getActivity().getApplicationContext(), ViewAppointmentActivity.class);
                    in.putExtra("appObj", app);
                    startActivity(in);
                    /*Toast.makeText(getApplicationContext(),
                            "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                            .show();*/
                }

            });
        }
        else{
            tv.setText("No ongoing appointment available");
            tv.setVisibility(view.VISIBLE);
        }
        newPage = (Spinner)view.findViewById(R.id.createAppointment);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newPage.setAdapter(adapter);
        newPage.setOnItemSelectedListener(this);
        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        Toast.makeText(getActivity().getApplicationContext(),"number " + position +" "+ id ,Toast.LENGTH_SHORT).show();
        switch(position) {
            /*case 1:
                Intent newIntent = new Intent(getActivity().getApplicationContext(),CreateAppointmentActivity.class);
                startActivity(newIntent);*/
            case 1:
                Intent referIntent = new Intent(getActivity().getApplicationContext(),ReferralActivity.class);
                startActivity(referIntent);
            case 2:
                //haven't done yet
                Intent followIntent = new Intent(getActivity().getApplicationContext(),MainActivity.class);
                startActivity(followIntent);
        }
    }

      public void goToCreateAppointment(View view)
    {
        Intent intent = new Intent(getActivity(), CreateAppointmentActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
