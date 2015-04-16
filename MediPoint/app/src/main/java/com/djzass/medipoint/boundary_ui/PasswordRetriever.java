package com.djzass.medipoint.boundary_ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.djzass.medipoint.R;
import com.djzass.medipoint.logic_manager.AccountManager;
import com.djzass.medipoint.logic_manager.Container;


public class PasswordRetriever extends Activity {

    private static final String username = "djzass15@gmail.com";
    private static final String password = "medipoint";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_retriever);

        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        Container.getAccountManager().updateAccountDao(this);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nricTextbox = (EditText) findViewById(R.id.nricTextbox);
                Cursor cursor = Container.getAccountManager().findAccount(nricTextbox.getText().toString());
                if (cursor == null) {
                    AccountNotExist();
                }

                else {
                    emailPassword(cursor);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_password_retriever, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void AccountNotExist() {
        Toast.makeText(this, "You do not have any existing account", Toast.LENGTH_LONG).show();
    }

    public void emailPassword(Cursor cursor){
        cursor.moveToFirst();
        String email = cursor.getString(1);
        String password = cursor.getString(2);
        String body = "Dear Sir,\n The password of your account is '" + password + "'.\n Thank You!";
        Email emailSender = new Email();
        emailSender.sendMail(email,body);
        Toast.makeText(this,"Email sent",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

}
