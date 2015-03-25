package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class AccountManager {
	private ArrayList<Account> accounts;
    FeedReaderDbHelper DbHelper;
    SQLiteDatabase db;
    SessionManager session;
	public AccountManager(Context context) {
        accounts = new ArrayList<Account>();
        DbHelper = new FeedReaderDbHelper(context);
        db = DbHelper.getWritableDatabase();
        session = new SessionManager(context);
	}
	
	public void validate(){
		
	}
	
	public void updateAccount(){
		
	}
	
	public boolean authenticate(String username,String password){
        int numUsers = DbHelper.onLogin(username,password,db);
        return numUsers>0? true:false;
		
	}
	
	public void findAccount(){
		
	}

    public void createAccount(Account newAccount,SQLiteDatabase db){



// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_NAME, newAccount.getName());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_NRIC,newAccount.getNric());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_EMAIL, newAccount.getEmail());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_CONTACTNO, newAccount.getPhoneNumber());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_ADDRESS, newAccount.getAddress());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_CITIZENSHIP, newAccount.getCitizenship());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE, newAccount.getCountryOfResidence());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_GENDER, newAccount.getGender());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS, newAccount.getMaritalStatus());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_DOB, ""+newAccount.getDob());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_USERNAME, newAccount.getUsername());
        values.put(FeedReaderContract.AccountEntry.COLUMN_NAME_PASSWORD, newAccount.getPassword());

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedReaderContract.AccountEntry.TABLE_NAME,
                null,
                values);
        accounts.add(newAccount);
    }

    public void login(String username,String password) {

        session.createLoginSession(username,password);
    }


}
