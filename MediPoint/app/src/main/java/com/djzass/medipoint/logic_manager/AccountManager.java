package com.djzass.medipoint.logic_manager;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.djzass.medipoint.entity.Account;
import com.djzass.medipoint.logic_database.AccountDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AccountManager {
    private Account newAccount;
    AccountDAO accountDAO;
    Context context;

	public AccountManager(Context context){

        //accounts = new ArrayList<Account>();
        //dbHelper = new DbHelper(context);
        //db = dbHelper.getWritableDatabase();
        try {
            accountDAO = new AccountDAO(context);
            this.context = context;
        }
        catch(SQLException sqlEx)
        {
            sqlEx.getStackTrace();
        }
    }

    public Cursor findAccount(String nric){
        Cursor cursor = accountDAO.checkAccount(nric);
        return cursor.getCount()>0? cursor:null;
    }

    public void updateAccount(){

    }

    public boolean authenticate(String username,String password){
        int numUsers = accountDAO.onLogin(username,password);
        return numUsers>0? true:false;

    }

    public long createAccount(Bundle AccountDetails,Context context){
        Account newAccount = extractAccountDetails(AccountDetails);

        try {
            AccountDAO acctDAO = new AccountDAO(context);
            return acctDAO.insertAccount(newAccount);
        }
        catch(SQLException sqlExcep){
            sqlExcep.getStackTrace();
        }
        return -1;

    }

   public String CalendarToString(Calendar calendar){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        return sdf.format(calendar.getTime());
    }

    public boolean isNewAccount(String nric){
        Cursor cursor = findAccount(nric);
        if(cursor==null)
            return true;
        else
            return false;
    }

    public boolean doesUsernameExist(String username){
        return accountDAO.checkUsername(username)>0? true:false;
    }

    public Account extractAccountDetails(Bundle AccountDetails){
        Bundle PageOne = AccountDetails.getBundle("PAGE_ONE");
        Bundle PageTwo = AccountDetails.getBundle("PAGE_TWO");
        Bundle PageThree = AccountDetails.getBundle("PAGE_THREE");

        String name = PageOne.getString("NAME");
        String nric = PageOne.getString("NRIC");
        String email = PageOne.getString("EMAIL");
        String contact = PageOne.getString("CONTACT");
        String address = PageOne.getString("ADDRESS");

        String gender = PageTwo.getString("GENDER");
        String maritalStatus = PageTwo.getString("MARITAL_STATUS");
        String citizenship = PageTwo.getString("CITIZENSHIP");
        String countryOfResidence = PageTwo.getString("COUNTRY_OF_RESIDENCE");
        long dob = PageTwo.getLong("DOB");
        int notify_email = PageTwo.getInt("NOTIFY_EMAIL");
        int notify_sms = PageTwo.getInt("NOTIFY_SMS");
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTimeInMillis(dob);


        String username = PageThree.getString("USERNAME");
        String password = PageThree.getString("PASSWORD");
        Account newAccount = new Account(username,password,name,nric,email,contact,gender,address,maritalStatus,dobCal,citizenship,countryOfResidence,notify_email,notify_sms);
        return newAccount;
    }

    public Account getAccountById(long id) throws ParseException {
        Cursor cursor = accountDAO.getAccountById(id);
        cursor.moveToFirst();

        String name = cursor.getString(1);
        String nric = cursor.getString(2);
        String email = cursor.getString(3);
        String contact = cursor.getString(4);
        String address = cursor.getString(5);
        //String dob = cursor.getLong(6);
        //Toast.makeText(context,dob,Toast.LENGTH_LONG).show();
        String dob = cursor.getString(6);
        String gender = cursor.getString(7);
        String maritalStatus = cursor.getString(8);
        String citizenship = cursor.getString(9);
        String countryOfResidence = cursor.getString(10);
        String username = cursor.getString(11);
        String password = cursor.getString(12);
        int isEmail = cursor.getInt(13);
        int isSMS = cursor.getInt(14);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Calendar dobCal = Calendar.getInstance();
        /*dobCal.setTime(sdf.parse(dob));*/



        return new Account(id,username,password,name,nric,email,contact,gender,address,maritalStatus,dobCal,citizenship,countryOfResidence,isEmail,isSMS);

    }
}

