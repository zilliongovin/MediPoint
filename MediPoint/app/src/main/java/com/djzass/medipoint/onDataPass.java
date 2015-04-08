package com.djzass.medipoint;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DateFormatSymbols;

/**
 * Created by Shreyas on 4/6/2015.
 */
public class onDataPass {
    public static void FragmentToActivity(int date,int month,int year,Button button){
        if(button!=null){
            //string representation for month
            String[] month_str = new DateFormatSymbols().getMonths();
            button.setText(date + " " + month_str[month] + " " + year);
        }
    }

}
