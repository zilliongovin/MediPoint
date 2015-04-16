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
    
    /**
     * An instance of {@link AccountDAO}. This is to be re-instated with context before use.
     */
    private AccountDAO accountDao; 

    private static AccountManager instance = new AccountManager();


    /**
     * returns AccountManager instance
     */
    public static AccountManager getInstance() {
        return instance;
    }
    
    /**
     * Re-initializes the AccountDAO with the given context
     */
    public void updateAccountDao(Context context){
        try {
            accountDao = new AccountDAO(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long createAccount(Bundle AccountDetails,Context context){
        updateAccountDao(context);
        Account newAccount = extractAccountDetails(AccountDetails);
        long ret = accountDao.insertAccount(newAccount);
        return ret;
    }

    public long updateAccount(Bundle AccountDetails,Context context){
        updateAccountDao(context);
        Account newAccount = extractAccountDetails(AccountDetails);
        long ret = accountDao.update(newAccount);
        return ret;
    }

    public boolean isNewAccount(String nric, Context context){
        Cursor cursor = findAccount(nric,context);
        if(cursor==null)
            return true;
        else
            return false;
    }

    public boolean doesUsernameExist(String username, Context context){
        updateAccountDao(context);
        return accountDao.checkUsername(username)>0? true:false;
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

    public Account getAccountById(long id, Context context) throws ParseException {
        updateAccountDao(context);
        Cursor cursor = accountDao.getAccountById(id);
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

    public Cursor findAccount(String nric, Context context){
        updateAccountDao(context);
        Cursor cursor = accountDao.checkAccount(nric);
        return cursor.getCount()>0? cursor:null;
    }

    public boolean authenticate(String username,String password, Context context){
        updateAccountDao(context);
        int numUsers = accountDao.onLogin(username,password);
        return numUsers>0? true:false;
    }

    public Cursor findAccount(String nric){
        Cursor cursor = accountDao.checkAccount(nric);
        return cursor.getCount()>0? cursor:null;
    }

    public boolean authenticate(String username,String password){
        int numUsers = accountDao.onLogin(username,password);
        return numUsers>0? true:false;
    }

}

