package com.djzass.medipoint.boundary_ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import java.util.List;

/**
 * Created by Deka on 6/4/2015.
 */

public class TimePickerFragment extends DialogFragment{
    public static final String DATA = "items";

    public static final String SELECTED = "selected";

/*
    Button activityButton;
        int viewID = getArguments().getInt("VIEW_ID");
        activityButton = (Button)getActivity().findViewById(viewID);

        //set dialog title
        getDialog().setTitle("Timeslot");

        return view;
    }*/

    private SelectionListener listener;


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            this.listener = (SelectionListener)activity;
        }
        catch ( ClassCastException oops )
        {
            oops.printStackTrace();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle bundle = getArguments();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        dialog.setTitle("Please Select");
        dialog.setPositiveButton("Cancel", new PositiveButtonClickListener());

        List<String> list = (List<String>)bundle.get(DATA);
        int position = bundle.getInt(SELECTED);

        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        dialog.setSingleChoiceItems(cs, position, selectItemListener);

        return dialog.create();
    }

    class PositiveButtonClickListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            dialog.dismiss();
        }
    }

    DialogInterface.OnClickListener selectItemListener = new DialogInterface.OnClickListener()
    {

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            // process
            //get text from selected radio button
            //if radio button is selected
             /*if(selectedId != -1) {
                CharSequence timeSelected = ((RadioButton)getView().findViewById(selectedId)).getText();
                onDataPass activity = (onDataPass)getActivity();
                activity.TimePickerFragmentToActivity(timeSelected,activityButton);


                 //get button from create OR edit appointment layout
                Button timefrag = (Button) getActivity().findViewById(R.id.timepicker);
                timefrag.setText(timeSelected);
                */

            if ( listener != null )
            {
                listener.selectItem(which);
            }
            dialog.dismiss();
        }

    };
}

