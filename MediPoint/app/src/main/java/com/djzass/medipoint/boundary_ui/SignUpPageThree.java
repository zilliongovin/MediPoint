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


public class SignUpPageThree extends Activity {

    private AccountManager AccountCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);
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

    public void goToPage2(){
        Intent PageThreeToTwo = new Intent(this,SignUpPageTwo.class);
        PageThreeToTwo.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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

    public void goToMedicalHistoryForm(String username,String password,Intent PageThreeToHistory)
    {
        SignUpPageOne.PageOne.finish();
        SignUpPageTwo.PageTwo.finish();
        this.finish();
        startActivity(PageThreeToHistory);
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
        boolean usernameExists = Container.getAccountManager().doesUsernameExist(checkViews[0].getText().toString(),this);
        boolean isPasswordEqual = checkPassword(checkViews[1],checkViews[2]);
        if(isFilled && !usernameExists && isPasswordEqual)
        {
            String username = checkViews[0].getText().toString();
            String password = checkViews[1].getText().toString();

            //medical history form intent
            Intent PageThreeToHistory = createIntentToHistory(username,password);

            long accountId = Container.getAccountManager().createAccount(PageThreeToHistory.getExtras(),this);

            AccountCreatedDialog(username,password,PageThreeToHistory, (int) accountId);

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

    public void AccountCreatedDialog(final String username, final String password,final Intent intent,int accountID)
    {
        String message = "Congratulations! Your account has been successfully created.";
        String title = "Success";
        intent.putExtra("ID",accountID);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                goToMedicalHistoryForm(username, password, intent);

                //goToLoginPage(username,password,intent);
            }
        };

        AlertDialogInterface AlertDisplayer = new AlertDialogInterface(title,message,this);
        AlertDisplayer.AccountCreated(r);
    }

    public void unequalPassword()
    {
        Toast.makeText(this,"Confirmed Password is incorrect",Toast.LENGTH_LONG).show();

    }

    public Intent createIntentToHistory(String username,String password)
    {
        Intent PageThreeToHistory = new Intent(this, MedicalHistory.class);
        Bundle pageThree = new Bundle();
        pageThree.putString("USERNAME",username);
        pageThree.putString("PASSWORD",password);
        PageThreeToHistory.putExtra("PAGE_THREE",pageThree);
        PageThreeToHistory.putExtra("PAGE_TWO",getIntent().getBundleExtra("PAGE_TWO"));
        PageThreeToHistory.putExtra("PAGE_ONE",getIntent().getBundleExtra("PAGE_ONE"));
        return PageThreeToHistory;
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

