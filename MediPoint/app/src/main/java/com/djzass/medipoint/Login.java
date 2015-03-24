package com.djzass.medipoint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //----------------------------TEST---------------------------------------------
        FeedReaderDbHelper helper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {FeedReaderContract.FeedUserAccount.COLUMN_NAME_NAME, FeedReaderContract.FeedUserAccount.COLUMN_NAME_NRIC, FeedReaderContract.FeedUserAccount.COLUMN_NAME_EMAIL, FeedReaderContract.FeedUserAccount.COLUMN_NAME_CONTACTNO, FeedReaderContract.FeedUserAccount.COLUMN_NAME_ADDRESS, FeedReaderContract.FeedUserAccount.COLUMN_NAME_DOB, FeedReaderContract.FeedUserAccount.COLUMN_NAME_GENDER, FeedReaderContract.FeedUserAccount.COLUMN_NAME_MARITAL_STATUS, FeedReaderContract.FeedUserAccount.COLUMN_NAME_CITIZENSHIP, FeedReaderContract.FeedUserAccount.COLUMN_NAME_COUNTRY_OF_RESIDENCE,FeedReaderContract.FeedUserAccount.COLUMN_NAME_USERNAME, FeedReaderContract.FeedUserAccount.COLUMN_NAME_PASSWORD};
        Cursor cursor = db.query(FeedReaderContract.FeedUserAccount.TABLE_NAME,columns,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            String name = cursor.getString(0);
            String nric = cursor.getString(1);
            String email = cursor.getString(2);
            String contact = cursor.getString(3);
            String address = cursor.getString(4);
            String dob = cursor.getString(5);
            String gender = cursor.getString(6);
            String marStatus = cursor.getString(7);
            String citizenship = cursor.getString(8);
            String country = cursor.getString(9);
            String username = cursor.getString(10);
            String password = cursor.getString(11);
            Toast.makeText(this,name,Toast.LENGTH_LONG).show();
            Toast.makeText(this,nric,Toast.LENGTH_LONG).show();
            Toast.makeText(this,email,Toast.LENGTH_LONG).show();
            Toast.makeText(this,contact,Toast.LENGTH_LONG).show();
            Toast.makeText(this,address,Toast.LENGTH_LONG).show();
            Toast.makeText(this,dob,Toast.LENGTH_LONG).show();
            Toast.makeText(this,gender,Toast.LENGTH_LONG).show();
            Toast.makeText(this,marStatus,Toast.LENGTH_LONG).show();
            Toast.makeText(this,citizenship,Toast.LENGTH_LONG).show();
            Toast.makeText(this,country,Toast.LENGTH_LONG).show();
            Toast.makeText(this,username,Toast.LENGTH_LONG).show();
            Toast.makeText(this,password,Toast.LENGTH_LONG).show();

        }
        //--------------------------------TEST----------------------------------------
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void ForgotPassword(View view)
    {
        Intent intent = new Intent(this,PasswordRetriever.class);
        startActivity(intent);
    }

    public void createSignUpForm(View view)
    {
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }


}
