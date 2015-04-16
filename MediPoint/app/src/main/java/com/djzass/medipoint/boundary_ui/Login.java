package com.djzass.medipoint.boundary_ui;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.djzass.medipoint.R;
import com.djzass.medipoint.logic_database.DbHelper;
import com.djzass.medipoint.logic_manager.AccountManager;
import com.djzass.medipoint.logic_manager.Container;
import com.djzass.medipoint.logic_manager.SessionManager;


public class Login extends Activity {

    Button loginButton;
    DbHelper mDbHelper;
    SQLiteDatabase db;
    private SessionManager sessionManager;
    private AccountManager accountManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Container.init();
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        accountManager = new AccountManager(this);

        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                EditText usernameBox = (EditText) findViewById(R.id.enterUsernameTextbox);
                EditText passwordBox = (EditText) findViewById(R.id.enterPasswordTextbox);
                String username = usernameBox.getText().toString();
                String password = passwordBox.getText().toString();

                boolean isAuthenticated = accountManager.authenticate(username,password);
                if(isAuthenticated==true){
                    sessionManager.createLoginSession(username,password);
                    loginSuccessful(username);
                    goToMain();

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

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    public void ForgotPassword(View view)
    {
        Intent intent = new Intent(this,PasswordRetriever.class);
        startActivity(intent);
    }

    public void createSignUpForm(View view)
    {
        Intent intent = new Intent(this,SignUpPageOne.class);
        startActivity(intent);
    }

    public void wrongCredentials(){
        Toast.makeText(this,"Wrong username or password",Toast.LENGTH_LONG).show();
    }

    public void loginSuccessful(String username){
        Toast.makeText(this,"Welcome "+username+"!",Toast.LENGTH_LONG).show();

    }

    public void goToMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
