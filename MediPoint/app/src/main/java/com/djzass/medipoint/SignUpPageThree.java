package com.djzass.medipoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpPageThree extends Activity {
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

    public void goToPage2(){
        Intent PageThreeToTwo = new Intent(this,SignUpPageTwo.class);
        startActivity(PageThreeToTwo);
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

    public void goToLoginPage(String username,String password)
    {
        /*Intent intent = new Intent(this,Login.class);
        startActivity(intent);*/
        Intent PageThreeToLogin = new Intent(this,Login.class);
        Bundle pageThree = new Bundle();
        pageThree.putString("USERNAME",username);
        pageThree.putString("PASSWORD",password);
        PageThreeToLogin.putExtra("PAGE_THREE",pageThree);
        PageThreeToLogin.putExtra("PAGE_ONE_AND_TWO",getIntent().getExtras());
        //PageThreeToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(PageThreeToLogin);
    }

    public void goToPrevious(View view)
    {
       goToPage2();
    }

    public void goToNext(View view)
    {
        EditText[] checkViews = new EditText[3];
        String[] str = new String[3];
        checkViews[0] = (EditText) findViewById(R.id.UsernameTextbox);
        checkViews[1] = (EditText) findViewById(R.id.PasswordTextbox);
        checkViews[2] = (EditText) findViewById(R.id.ConfirmPasswordTextbox);

        boolean isFilled = isFormFilled(checkViews,3);
        boolean usernameExists = AccountCreator.doesUsernameExist(checkViews[0].getText().toString());
        boolean isPasswordEqual = checkPassword(checkViews[1],checkViews[2]);
        if(isFilled && !usernameExists && isPasswordEqual)
        {
            String username = checkViews[0].getText().toString();
            String password = checkViews[1].getText().toString();
            AccountCreator.savePageThree(username,password);
            AccountCreatedDialog(username,password);
            AccountCreator.createAccount(getIntent().getExtras());
        }

        else if(!isFilled)
        {
            incompleteForm();
        }

        else if(usernameExists)
        {
            Toast.makeText(this,"Username already exists",Toast.LENGTH_LONG).show();
        }

        else
        {
            unequalPassword();
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

    public void AccountCreatedDialog(final String username, final String password)
    {
        String message = "Congratulations! Your account has been successfully created.";
        String title = "Success";
        Runnable r = new Runnable() {
            @Override
            public void run() {
                goToLoginPage(username,password);
            }
        };

        AlertDialogInterface AlertDisplayer = new AlertDialogInterface(title,message,this);
        AlertDisplayer.AccountCreated(r);
    }

    public void unequalPassword()
    {
        Toast.makeText(this,"Confirmed Password is incorrect",Toast.LENGTH_LONG).show();

    }

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

