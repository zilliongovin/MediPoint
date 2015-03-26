package com.djzass.medipoint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {

    Button loginButton;
    DbHelper mDbHelper;
    SQLiteDatabase db;
    AccountManager acctManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //----------------------------TEST---------------------------------------------
        mDbHelper = new DbHelper(this);
        db = mDbHelper.getWritableDatabase();


        DbHelper helper = new DbHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DbContract.AccountEntry.COLUMN_NAME_NAME, DbContract.AccountEntry.COLUMN_NAME_NRIC, DbContract.AccountEntry.COLUMN_NAME_EMAIL, DbContract.AccountEntry.COLUMN_NAME_CONTACTNO, DbContract.AccountEntry.COLUMN_NAME_ADDRESS, DbContract.AccountEntry.COLUMN_NAME_DOB, DbContract.AccountEntry.COLUMN_NAME_GENDER, DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS, DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP, DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE,DbContract.AccountEntry.COLUMN_NAME_USERNAME, DbContract.AccountEntry.COLUMN_NAME_PASSWORD};
        Cursor cursor = db.query(DbContract.AccountEntry.TABLE_NAME,columns,null,null,null,null,null);
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
            /*Toast.makeText(this,name,Toast.LENGTH_LONG).show();
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
            */
        }
               //--------------------------------TEST----------------------------------------

        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                EditText usernameBox = (EditText) findViewById(R.id.enterUsernameTextbox);
                EditText passwordBox = (EditText) findViewById(R.id.enterPasswordTextbox);
                String username = usernameBox.getText().toString();
                String password = passwordBox.getText().toString();
                acctManager = new AccountManager(getApplicationContext());
                boolean isAuthenticated = acctManager.authenticate(username,password);
                if(isAuthenticated==true){
                    acctManager.login(username,password);
                    loginSuccessful(username);

                }
                else{
                    wrongCredentials();

                }

            }
        });
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

    public void wrongCredentials(){
        Toast.makeText(this,"Wrong username or password",Toast.LENGTH_LONG).show();
    }

    public void loginSuccessful(String username){
        Toast.makeText(this,"Welcome "+username+"!",Toast.LENGTH_LONG).show();

    }


}
