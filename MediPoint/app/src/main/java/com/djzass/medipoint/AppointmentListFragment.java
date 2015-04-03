package com.djzass.medipoint;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.djzass.medipoint.dummy.DummyContent;

import java.sql.SQLException;

public class AppointmentListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_appointment_list, null);
        // Find ListView to populate
        ListView lvItems = (ListView) view.findViewById(R.id.appointmentListView);
        // Setup cursor adapter using cursor from last step
        //Patient id
        //String selectQuery = "SELECT  * FROM " + DbContract.AppointmentEntry.TABLE_NAME + " WHERE "
        //                + DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID + " = " + id;

        //Cursor appointmentCursor = DbDAO.database.query(selectQuery);

        //AppointmentCursorAdapter appointmentAdapter = new AppointmentCursorAdapter(this, appointmentCursor,0);
        // Attach cursor adapter to the ListView
        //lvItems.setAdapter(appointmentAdapter);

        return view;
    }

}
