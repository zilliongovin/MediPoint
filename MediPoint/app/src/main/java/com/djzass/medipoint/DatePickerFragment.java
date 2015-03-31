package com.djzass.medipoint;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormatSymbols;


/**
 * Created by Zillion Govin on 28/3/2015.
 */
public class DatePickerFragment extends DialogFragment implements View.OnClickListener {

    Button set, cancel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.datepicker_fragment, null);

        //button for datepicker fragment
        set = (Button) view.findViewById(R.id.setDate);

        //set listener when button is clicked
        set.setOnClickListener(this);

        //set dialog title
        getDialog().setTitle("Select Date");

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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

    @Override
    public void onClick(View v) {

        if (v.getId() ==  R.id.setDate){
            //instantiate datepicker
            DatePicker datepicker = (DatePicker)getView().findViewById(R.id.chooseDate);

            //string representation for month
            String[] month_str = new DateFormatSymbols().getMonths();

            //get the selected date for sign up activity
            SignUpPageTwo signupActivity = (SignUpPageTwo) getActivity();
            signupActivity.setDate(datepicker);

            //get input from datepicker
            int date = datepicker.getDayOfMonth();
            int month = datepicker.getMonth();
            int year = datepicker.getYear();

            //change the button text according to date selected
            //sign up datepicker
            Button DOB = (Button) getActivity().findViewById(R.id.DateOfBirthDatePicker);

            //create and edit appointment datepicker
            Button datefrag = (Button) getActivity().findViewById(R.id.datepicker);

            //set button text depend on which activity is active
            if(DOB != null){
                DOB.setText(date + " " + month_str[month] + " " + year);
            }
            else if (datefrag != null){
                datefrag.setText(date + " " + month_str[month] + " " + year);
            }

            //close dialog
            dismiss();
        }
    }

}
