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


public class SignUpPageOne extends Activity {
    private AccountManager AccountCreator;
    //DbHelper mDbHelper;
    //SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //mDbHelper = new DbHelper(this);

        // Gets the data repository in write mode
        //db = mDbHelper.getWritableDatabase();
        AccountCreator = new AccountManager(this);
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
        Intent PageOneToTwo = new Intent(this,SignUpPageTwo.class);
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
        Intent intent = new Intent(this,Login.class);
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

                boolean isFilled = isFormFilled(checkViews,5);
                boolean newAccount = AccountCreator.isNewAccount(checkViews[1].getText().toString());

                if(!isFilled)
                {
                    incompleteForm();
                }

                else if(!newAccount){
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

                else
                {

                    String name = checkViews[0].getText().toString();
                    String nric = checkViews[1].getText().toString();
                    String email = checkViews[2].getText().toString();
                    String contact = checkViews[3].getText().toString();
                    String address = checkViews[4].getText().toString();
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

    public void incompleteForm()
    {
        Toast.makeText(this,"Please fill all fields",Toast.LENGTH_LONG).show();
    }

    /*public void AccountCreatedDialog()
    {
        String message = "Congratulations! Your account has been successfully created.";
        String title = "Success";
        Runnable r = new Runnable() {
            @Override
            public void run() {
                goToLoginPage();
            }
        };

        AlertDialogInterface AlertDisplayer = new AlertDialogInterface(title,message,this);
        AlertDisplayer.AccountCreated(r);
    }*/

    /*public void unequalPassword()
    {
        Toast.makeText(this,"Confirmed Password is incorrect",Toast.LENGTH_LONG).show();

    }*/

    /*public Calendar getDate(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar;
    }*/

    /*protected void onSaveInstanceState(Bundle outState,View[] views,int n) {
        super.onSaveInstanceState(outState);
        //Log.i(TAG, "onSaveInstanceState");

        CharSequence[] userText = new CharSequence[n];
        for(int i=0;i<n;i++){
            userText[i] =
        }
        userText = textBox.getText();
        outState.putCharSequence("savedText", userText);

    }*/

    /*protected void onRestoreInstanceState(Bundle savedState) {
        //Log.i(TAG, "onRestoreInstanceState");

        final EditText textBox =
                (EditText) findViewById(R.id.editText1);

        CharSequence userText =
                savedState.getCharSequence("savedText");

        textBox.setText(userText);
    }*/

}

