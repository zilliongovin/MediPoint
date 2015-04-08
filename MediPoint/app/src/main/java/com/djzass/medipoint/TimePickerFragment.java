package com.djzass.medipoint;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Zillion Govin on 28/3/2015.
 */
public class TimePickerFragment extends Fragment {

    Button set, cancel;
    ArrayList<String> availableSlots;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.timepicker_fragment, null);

        //button for timepicker fragment
        set = (Button) view.findViewById(R.id.setTime);

        ListView timeSlotList = (ListView)view.findViewById(R.id.listOfSlots);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_single_choice,
                availableSlots);
        timeSlotList.setAdapter(timeAdapter);

        timeSlotList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                AppointmentDummy app = (AppointmentDummy) parent.getAdapter().getItem(position);
                //Toast.makeText(getApplicationContext(), app.toString(), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getActivity().getApplicationContext(), ViewAppointmentActivity.class);
                in.putExtra("appObj", app);
                startActivity(in);
                    /*Toast.makeText(getApplicationContext(),
                            "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                            .show();*/
            }
        });


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
        ArrayList<String> availableSlots = new ArrayList<String>();

        availableSlots.add("10:00 - 10:30");
        availableSlots.add("11:00 - 11:30");
        availableSlots.add("12:00 - 12:30");
        availableSlots.add("13:00 - 13:30");
        availableSlots.add("10:00 - 10:30");
        availableSlots.add("11:00 - 11:30");
        availableSlots.add("12:00 - 12:30");
        availableSlots.add("13:00 - 13:30");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    //fragment lifecycle methods
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


}
