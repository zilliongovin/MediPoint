package com.djzass.medipoint.boundary_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.djzass.medipoint.R;
import com.djzass.medipoint.logic_manager.AccountManager;
import com.djzass.medipoint.logic_manager.Container;

import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class SignUpPageOneActivity extends Activity {

    private AccountManager AccountCreator;
    public static Activity PageOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        PageOne = this;
        //mDbHelper = new DbHelper(this);

        // Gets the data repository in write mode
        //db = mDbHelper.getWritableDatabase();
        //newAccount = new Account();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    /*public void goToPage1(){
        setContentView(R.layout.activity_sign_up);
    }*/

    public void goToPage2(String name,String nric,String email,String contact,String address){
        Intent PageOneToTwo = new Intent(this,SignUpPageTwoActivity.class);
        Bundle pageOne = new Bundle();
        pageOne.putString("NAME",name);
        pageOne.putString("NRIC",nric);
        pageOne.putString("EMAIL",email);
        pageOne.putString("CONTACT",contact);
        pageOne.putString("ADDRESS",address);
        PageOneToTwo.putExtra("PAGE_ONE",pageOne);
        PageOneToTwo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PageOneToTwo.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(PageOneToTwo);
    }

    /*public void goToPage3(){
        setContentView(R.layout.activity_sign_up3);
    }*/

    public boolean checkPassword(EditText Password,EditText ConfirmPassword)
    {
        String pass1 = Password.getText().toString();
        String pass2 = ConfirmPassword.getText().toString();
        return pass1.equals(pass2);
    }

    public void goToLoginPage()
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void goToNext(View view)
    {
                EditText[] checkViews = new EditText[5];
                checkViews[0] = (EditText) findViewById(R.id.NameTextbox);
                checkViews[1] = (EditText) findViewById(R.id.NRICTextbox);
                checkViews[2] = (EditText) findViewById(R.id.EmailTextbox);
                checkViews[3] = (EditText) findViewById(R.id.ContactTextbox);
                checkViews[4] = (EditText) findViewById(R.id.AddressTextbox);

                String name = checkViews[0].getText().toString();
                String nric = checkViews[1].getText().toString();
                String email = checkViews[2].getText().toString();
                String contact = checkViews[3].getText().toString();
                String address = checkViews[4].getText().toString();

                if(!isFormFilled(checkViews,5)){
                    Toast.makeText(this,"Please fill all fields",Toast.LENGTH_LONG).show();
                }
                else if(!isValidNric(nric)){
                    Toast.makeText(this,"Please enter a valid NRIC",Toast.LENGTH_LONG).show();
                }
                else if(!Container.getAccountManager().isNewAccount(nric, this)){
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            goToLoginPage();
                        }
                    };
                    String title = "Existing account";
                    String message = "You already have an existing account";
                    AlertDialogInterface AlertDisplayer = new AlertDialogInterface(title,message,this);
                    AlertDisplayer.AccountAlreadyExists(r);
                }
                else if(!isValidEmailAddress(email)){
                    Toast.makeText(this,"Please enter a valid email address",Toast.LENGTH_LONG).show();
                }
                else if(!isValidContactNo(contact)){
                    Toast.makeText(this,"Please enter a valid contact number",Toast.LENGTH_LONG).show();
                }
                else {
                    //AccountCreator.savePageOne(name,nric,email,contact,address);
                    goToPage2(name,nric,email,contact,address);
                }
    }

    public boolean isFormFilled(EditText[] views,int n)
    {
        String[] str = new String[n];
        for(int i=0;i<n;i++)
        {
            str[i] = views[i].getText().toString();
            if(str[i].equals(""))
                return false;
        }
        return true;
    }

    public static boolean isValidNric(String nric) {
        return(nric.matches("^.*[a-zA-Z0-9].*$") && nric.length()>4); //only alphanumeric, >4 chars
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    public static boolean isValidContactNo(String contactno) {
        return(contactno.matches("^.*[0-9].*$") && contactno.length()>4); //only numeric, >4 chars
    }
}

