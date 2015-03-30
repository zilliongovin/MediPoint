package com.djzass.medipoint;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Zillion Govin on 28/3/2015.
 */
public class TimePickerFragment extends DialogFragment implements View.OnClickListener {

    Button set, cancel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.timepicker_fragment, null);

        //button for timepicker fragment
        set = (Button) view.findViewById(R.id.setTime);

        //set listener when button is clicked
        set.setOnClickListener(this);

        //set dialog title
        getDialog().setTitle("Timeslot");

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

        if (v.getId() ==  R.id.setTime){

            //instantiate the radio group
            RadioGroup time = (RadioGroup) getView().findViewById(R.id.timeslot);

            //find id of selected radio button
            int selectedId = time.getCheckedRadioButtonId();

            //get text from selected radio button
            //if radio button is selected
             if(selectedId != -1) {
                CharSequence timeSelected = ((RadioButton)getView().findViewById(selectedId)).getText();

                 //get button from create OR edit appointment layout
                Button timefrag = (Button) getActivity().findViewById(R.id.timepicker);
                timefrag.setText(timeSelected);
            }
            dismiss();
        }
    }

}
