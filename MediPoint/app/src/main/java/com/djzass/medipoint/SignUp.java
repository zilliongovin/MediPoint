package com.djzass.medipoint;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SignUp extends Activity {
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

    public void goToPage1(){
        setContentView(R.layout.activity_sign_up);
    }

    public void goToPage2(){
        setContentView(R.layout.activity_sign_up2);
    }

    public void goToPage3(){
        setContentView(R.layout.activity_sign_up3);
    }

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

    public void goToPrevious(View view)
    {
        switch(view.getId())
        {
            case R.id.sign_up2_left:
                goToPage1();
                break;
            case R.id.sign_up3_left:
                goToPage2();
                break;
        }
    }

    public void goToNext(View view)
    {
        switch(view.getId())
        {
            case R.id.sign_up1_right:
            {
                EditText[] checkViews = new EditText[5];
                checkViews[0] = (EditText) findViewById(R.id.NameTextbox);
                checkViews[1] = (EditText) findViewById(R.id.NRICTextbox);
                checkViews[2] = (EditText) findViewById(R.id.EmailTextbox);
                checkViews[3] = (EditText) findViewById(R.id.ContactTextbox);
                checkViews[4] = (EditText) findViewById(R.id.AddressTextbox);

                boolean isFilled = isFormFilled(checkViews,5);
                if(isFilled)
                {

                    String name = checkViews[0].getText().toString();
                    String nric = checkViews[1].getText().toString();
                    String email = checkViews[2].getText().toString();
                    String contact = checkViews[3].getText().toString();
                    String address = checkViews[4].getText().toString();
                    AccountCreator.savePageOne(name,nric,email,contact,address);
                    goToPage2();
                }

                else
                {
                    incompleteForm();
                }
                break;
            }
            case R.id.sign_up2_right:
            {
                RadioGroup genderGroup = (RadioGroup)findViewById(R.id.GenderRadioGroup);
                int selGender = genderGroup.getCheckedRadioButtonId();
                RadioButton selGenderButton = (RadioButton)findViewById(selGender);
                RadioGroup maritalStatusGroup = (RadioGroup)findViewById(R.id.GenderRadioGroup);
                int selMaritalStatus = maritalStatusGroup.getCheckedRadioButtonId();
                RadioButton selMaritalStatusButton = (RadioButton)findViewById(selMaritalStatus);
                Spinner citizenshipSpinner = (Spinner)findViewById(R.id.CitizenshipSpinner);
                Spinner countryOfResidenceSpinner = (Spinner)findViewById(R.id.CountryOfResidenceSpinner);
                DatePicker dobPicker = (DatePicker)findViewById(R.id.DateOfBirthDatePicker);
                Calendar dobCal = getDate(dobPicker);
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
                    AccountCreator.savePageTwo(gender,maritalStatus,citizenship,countryOfResidence,dobCal);
                    goToPage3();
                }

                break;

            }
            case R.id.sign_up3_right:
            {
                EditText[] checkViews = new EditText[3];
                String[] str = new String[3];
                checkViews[0] = (EditText) findViewById(R.id.UsernameTextbox);
                checkViews[1] = (EditText) findViewById(R.id.PasswordTextbox);
                checkViews[2] = (EditText) findViewById(R.id.ConfirmPasswordTextbox);

                boolean isFilled = isFormFilled(checkViews,3);
                boolean isPasswordEqual = checkPassword(checkViews[1],checkViews[2]);
                if(isFilled && isPasswordEqual)
                {

                    String username = checkViews[0].getText().toString();
                    String password = checkViews[1].getText().toString();
                    AccountCreator.savePageThree(username,password);
                    AccountCreatedDialog();
                    AccountCreator.createAccount();

                }

                else if(!isFilled)
                {
                    incompleteForm();
                }
                else
                {
                    unequalPassword();
                }
                break;

            }
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

    public void AccountCreatedDialog()
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
    }

    public void unequalPassword()
    {
        Toast.makeText(this,"Confirmed Password is incorrect",Toast.LENGTH_LONG).show();

    }

    public Calendar getDate(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar;
    }

}
