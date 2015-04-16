package com.djzass.medipoint.boundary_ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import java.text.DateFormatSymbols;

/**
 * Created by Shreyas on 4/6/2015.
 */

public class onDataPass extends FragmentActivity{
    
    public void DatePickerFragmentToActivity(int date,int month,int year,Button button){
        if(button!=null){
            //string representation for month
            String[] month_str = new DateFormatSymbols().getMonths();
            button.setText(date + " " + month_str[month] + " " + year);
        }
    }

    public void TimePickerFragmentToActivity(CharSequence timeSelected,Button button){
        button.setText(timeSelected);
    }

}
