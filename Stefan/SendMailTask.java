package com.android.notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

/**
 * Created by Z480 on 4/3/2015.
 */
public class SendMailTask extends AsyncTask<Message, Void, Void> {
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }

    @Override
    protected Void doInBackground(Message... messages) {
        try {
            Transport.send(messages[0]);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
