package com.djzass.medipoint;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class SignUp extends ActionBarActivity {
    private Account newAccount;
    DbHelper mDbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDbHelper = new DbHelper(this);

        // Gets the data repository in write mode
        db = mDbHelper.getWritableDatabase();
        newAccount = new Account();
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
                    newAccount.setName(checkViews[0].getText().toString());
                    newAccount.setNric(checkViews[1].getText().toString());
                    newAccount.setEmail(checkViews[2].getText().toString());
                    newAccount.setPhoneNumber(checkViews[3].getText().toString());
                    newAccount.setAddress(checkViews[4].getText().toString());
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
                RadioGroup gender = (RadioGroup)findViewById(R.id.GenderRadioGroup);
                int selGender = gender.getCheckedRadioButtonId();
                RadioButton selGenderButton = (RadioButton)findViewById(selGender);
                RadioGroup maritalStatus = (RadioGroup)findViewById(R.id.GenderRadioGroup);
                int selMaritalStatus = maritalStatus.getCheckedRadioButtonId();
                RadioButton selMaritalStatusButton = (RadioButton)findViewById(selMaritalStatus);
                Spinner citizenship = (Spinner)findViewById(R.id.CitizenshipSpinner);
                Spinner countryOfResidence = (Spinner)findViewById(R.id.CountryOfResidenceSpinner);
                if(selGender==-1||selMaritalStatus==-1)
                    incompleteForm();

                else
                {
                    newAccount.setGender(selGenderButton.getText().toString());
                    newAccount.setMaritalStatus(selMaritalStatusButton.getText().toString());
                    newAccount.setCitizenship(citizenship.toString());
                    newAccount.setCountryOfResidence(countryOfResidence.toString());
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
                    newAccount.setUsername(checkViews[0].getText().toString());
                    newAccount.setPassword(checkViews[1].getText().toString());
                    AccountManager accountCreator = new AccountManager(getApplicationContext());
                    AccountCreatedDialog();
                    accountCreator.createAccount(newAccount,db);

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
        /*
        String message = "Please fill all fields.";
        String title = "Incomplete form";
        AlertDialogInterface AlertDisplayer = new AlertDialogInterface(title,message,this);
        AlertDisplayer.incompleteForm();
        */
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
        /*
        String message = "Please enter your password again.";
        String title = "Password Match failed";
        AlertDialogInterface AlertDisplayer = new AlertDialogInterface(title,message,this);
        AlertDisplayer.unequalPassword();
        */
        Toast.makeText(this,"Confirmed Password is incorrect",Toast.LENGTH_LONG).show();

    }


}
