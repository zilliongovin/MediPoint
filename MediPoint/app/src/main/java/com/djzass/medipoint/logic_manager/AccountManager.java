package com.djzass.medipoint.logic_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.djzass.medipoint.DbContract;
import com.djzass.medipoint.SessionManager;
import com.djzass.medipoint.entity.Account;
import com.djzass.medipoint.logic_database.DbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AccountManager {
	//private ArrayList<Account> accounts;
    private Account newAccount;
    DbHelper dbHelper;
    SQLiteDatabase db;
    SessionManager session;
	public AccountManager(Context context) {
        //accounts = new ArrayList<Account>();
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        session = new SessionManager(context);
	}

    public Cursor findAccount(String nric){
        Cursor cursor = dbHelper.checkAccount(nric,db);
        return cursor.getCount()>0? cursor:null;
		
	}
	
	public void updateAccount(){
		
	}
	
	public boolean authenticate(String username,String password){
        int numUsers = dbHelper.onLogin(username,password,db);
        return numUsers>0? true:false;
		
	}
	
	public void createAccount(Bundle AccountDetails){
        Account newAccount = extractAccountDetails(AccountDetails);
        updateDatabase(newAccount);
    }

    public void login(String username,String password) {

        session.createLoginSession(username,password);
    }

    public void logout(){
        session.deleteLoginSession();

    }

    /*public void savePageOne(String name,String nric,String email,String contact,String address){
        newAccount = new Account(name,nric,email,contact,address);
    }*/

    /*public void savePageTwo(String gender,String maritalStatus,String citizenship,String countryOfResidence, Calendar dobCal){
        newAccount.setGender(gender);
        newAccount.setMaritalStatus(maritalStatus);
        newAccount.setCitizenship(citizenship);
        newAccount.setCountryOfResidence(countryOfResidence);
        newAccount.setDob(dobCal);
    }*/

    /*public void savePageThree(String username,String password){
        newAccount.setUsername(username);
        newAccount.setPassword(password);
    }*/

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
        return dbHelper.checkUsername(username,db)>0? true:false;
    }

    public Account extractAccountDetails(Bundle AccountDetails){
        //Bundle PageOneAndTwo = AccountDetails.getBundle("PAGE_ONE_AND_TWO");
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
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTimeInMillis(dob);

        String username = PageThree.getString("USERNAME");
        String password = PageThree.getString("PASSWORD");
        Account newAccount = new Account(username,password,name,nric,email,contact,gender,address,maritalStatus,dobCal,citizenship,countryOfResidence);
        return newAccount;
    }

    public void updateDatabase(Account newAccount){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DbContract.AccountEntry.COLUMN_NAME_NAME, newAccount.getName());
        values.put(DbContract.AccountEntry.COLUMN_NAME_NRIC,newAccount.getNric());
        values.put(DbContract.AccountEntry.COLUMN_NAME_EMAIL, newAccount.getEmail());
        values.put(DbContract.AccountEntry.COLUMN_NAME_CONTACTNO, newAccount.getPhoneNumber());
        values.put(DbContract.AccountEntry.COLUMN_NAME_ADDRESS, newAccount.getAddress());
        values.put(DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP, newAccount.getCitizenship());
        values.put(DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE, newAccount.getCountryOfResidence());
        values.put(DbContract.AccountEntry.COLUMN_NAME_GENDER, newAccount.getGender());
        values.put(DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS, newAccount.getMaritalStatus());
        values.put(DbContract.AccountEntry.COLUMN_NAME_DOB, CalendarToString(newAccount.getDob()));
        values.put(DbContract.AccountEntry.COLUMN_NAME_USERNAME, newAccount.getUsername());
        values.put(DbContract.AccountEntry.COLUMN_NAME_PASSWORD, newAccount.getPassword());

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                DbContract.AccountEntry.TABLE_NAME,
                null,
                values);
    }




}
