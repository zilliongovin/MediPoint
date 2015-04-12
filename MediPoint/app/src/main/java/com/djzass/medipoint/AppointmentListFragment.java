package com.djzass.medipoint;

/**
 * Created by Zillion Govin on 4/4/2015.
 */

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.AppointmentManager;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by Deka on 4/4/2015.
 */
public class AppointmentListFragment extends Fragment implements ActionBar.OnNavigationListener{

    Spinner buttonSpinner;


    private ActionBar actionBar;
    private ArrayList<SpinnerNavItem> navSpinner;
    //private NavigationAdapter adapter;
    //ArrayList<AppointmentDummy> appointments;
    ArrayList<Appointment> appointments;
    private int patientId;
    public static AppointmentListFragment newInstance() {
        AppointmentListFragment fragment = new AppointmentListFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //appointments = new ArrayList<AppointmentDummy>();
        appointments = new ArrayList<Appointment>();
        SessionManager sessionManager = new SessionManager(getActivity());
        try {
            this.patientId = (int)sessionManager.getAccountId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AppointmentManager appointmentManager = AppointmentManager.getInstance();
        appointments = (ArrayList<Appointment>) appointmentManager.getPatientAppointmentList(this.patientId, this.getActivity());




    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.
        switch (position){
            case 0:
                Intent appIntent = new Intent(getActivity().getApplicationContext(),CreateAppointmentActivity.class);
                startActivity(appIntent);
            case 1:
                Intent refIntent = new Intent(getActivity().getApplicationContext(),ReferralActivity.class);
                startActivity(refIntent);
            case 2:
                Intent followIntent = new Intent(getActivity().getApplicationContext(),FollowUpListActivity.class);
                startActivity(followIntent);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_appointment_list, container, false);

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
                    Appointment app = (Appointment) parent.getAdapter().getItem(position);
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
            tv.setText("No appointment available");
            tv.setVisibility(view.VISIBLE);
        }

        //create appointment referral followup button
        buttonSpinner= (Spinner) view.findViewById(R.id.buttonSpinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.create_new, android.R.layout.simple_spinner_dropdown_item);

        buttonSpinner.setAdapter(adapter);
        buttonSpinner.setSelection(0);
        buttonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapterView, View v, int i, long lng) {
                String choice = buttonSpinner.getSelectedItem().toString();

                switch(choice){
                    case "Appointment":     buttonSpinner.setSelection(0);
                        goToCreateAppointment();
                        break;
                    case "Referral":        buttonSpinner.setSelection(0);
                        goToCreateReferral();
                        break;
                    case "Follow Up":       buttonSpinner.setSelection(0);
                        goToCreateFollowUp();
                        break;
                    default:                buttonSpinner.setSelection(0);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
            }
        });

        return view;
    }

    public void goToCreateAppointment()
    {
        Intent intent = new Intent(getActivity(), CreateAppointmentActivity.class);
        startActivity(intent);
    }

    public void goToCreateReferral()
    {
        Intent intent = new Intent(getActivity(), ReferralActivity.class);
        startActivity(intent);
    }

    public void goToCreateFollowUp()
    {
        Intent intent = new Intent(getActivity(), FollowUpListActivity.class);
        startActivity(intent);
    }

}
