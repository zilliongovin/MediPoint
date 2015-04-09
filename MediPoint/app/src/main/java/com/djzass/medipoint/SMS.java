package com.djzass.medipoint;


import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;


import java.util.ArrayList;

/**
 * Created by Z480 on 3/30/2015.
 */
public class SMS {
    public void sendSMS (Context context,String body) {
        String senderNo = "Medipoint";
        // Simulate SMS
        ContentValues values = new ContentValues();
        values.put("address", "(+65) 98068849");
        values.put("body", body);
        context.getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
    }

}
