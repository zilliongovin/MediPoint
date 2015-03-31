package com.djzass.medipoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class SignUpPageTwo extends Activity {
    //private AccountManager AccountCreator;
    //DbHelper mDbHelper;
    //SQLiteDatabase db;

    int dateOB = 0;
    int monthOB = 0;
    int yearOB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //mDbHelper = new DbHelper(this);

        // Gets the data repository in write mode
        //db = mDbHelper.getWritableDatabase();
       //AccountCreator = new AccountManager(this);
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

    public void goToPage1(){
        Intent PageTwoToOne = new Intent(this,SignUpPageOne.class);
        startActivity(PageTwoToOne);

    }

    /*public void goToPage2(){
        setContentView(R.layout.activity_sign_up2);
    }*/

    public void goToPage3(String gender,String maritalStatus,String citizenship,String countryOfResidence,long dob){
        Intent PageTwoToThree = new Intent(this,SignUpPageTwo.class);
        Bundle pageTwo = new Bundle();
        pageTwo.putString("GENDER",gender);
        pageTwo.putString("MARITAL_STATUS",maritalStatus);
        pageTwo.putString("CITIZENSHIP",citizenship);
        pageTwo.putString("COUNTRY_OF_RESIDENCE",countryOfResidence);
        pageTwo.putLong("DOB",dob);
        PageTwoToThree.putExtra("PAGE_TWO",pageTwo);
        PageTwoToThree.putExtra("PAGE_ONE",getIntent().getExtras());
        PageTwoToThree.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(PageTwoToThree);
    }

    /*public boolean checkPassword(EditText Password,EditText ConfirmPassword)
    {
        String pass1 = Password.getText().toString();
        String pass2 = ConfirmPassword.getText().toString();
        return pass1.equals(pass2);
    }*/

    /*public void goToLoginPage()
    {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }*/

    public void goToPrevious(View view)
    {
        goToPage1();
    }

    public void goToNext(View view)
    {
        RadioGroup genderGroup = (RadioGroup)findViewById(R.id.GenderRadioGroup);
        int selGender = genderGroup.getCheckedRadioButtonId();
        RadioButton selGenderButton = (RadioButton)findViewById(selGender);
        RadioGroup maritalStatusGroup = (RadioGroup)findViewById(R.id.GenderRadioGroup);
        int selMaritalStatus = maritalStatusGroup.getCheckedRadioButtonId();
        RadioButton selMaritalStatusButton = (RadioButton)findViewById(selMaritalStatus);
        Spinner citizenshipSpinner = (Spinner)findViewById(R.id.CitizenshipSpinner);
        Spinner countryOfResidenceSpinner = (Spinner)findViewById(R.id.CountryOfResidenceSpinner);

        Calendar dobCal = Calendar.getInstance();
        dobCal.set(yearOB, monthOB + 1, dateOB);

        Calendar currentDate = Calendar.getInstance();


        if(selGender==-1||selMaritalStatus==-1)
            incompleteForm();

        else if(dobCal.after(currentDate))
        {
            Toast.makeText(this,"Please enter your date of birth correctly",Toast.LENGTH_LONG).show();
        }

        else
        {
            String gender = selGenderButton.getText().toString();
            String maritalStatus = selMaritalStatusButton.getText().toString();
            String citizenship = citizenshipSpinner.toString();
            String countryOfResidence = countryOfResidenceSpinner.toString();
            long dobLong = dobCal.getTimeInMillis();
            //AccountCreator.savePageTwo(gender,maritalStatus,citizenship,countryOfResidence,dobCal);
            goToPage3(gender,maritalStatus,citizenship,countryOfResidence,dobLong);
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

    public Calendar getDate(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar;
    }

    public void setDate(DatePicker datepicker){
        dateOB = datepicker.getDayOfMonth();
        monthOB = datepicker.getMonth();
        yearOB = datepicker.getYear();
    }

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

