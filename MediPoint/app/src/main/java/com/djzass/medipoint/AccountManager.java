package com.djzass.medipoint;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class AccountManager {
	private ArrayList<Account> accounts;

	public AccountManager() {
		this.accounts = new ArrayList<Account>();
	}
	
	public void validate(){
		
	}
	
	public void updateAccount(){
		
	}
	
	public void authentication(){
		
	}
	
	public void findAccount(){
		
	}

    public void createAccount(Account newAccount,SQLiteDatabase db){

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DbContract.AccountEntry.COLUMN_NAME_NAME, newAccount.getName());
        values.put(DbContract.AccountEntry.COLUMN_NAME_EMAIL, newAccount.getEmail());
        values.put(DbContract.AccountEntry.COLUMN_NAME_CONTACTNO, newAccount.getPhoneNumber());
        values.put(DbContract.AccountEntry.COLUMN_NAME_ADDRESS, newAccount.getAddress());
        values.put(DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP, newAccount.getCitizenship());
        values.put(DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE, newAccount.getCountryOfResidence());
        values.put(DbContract.AccountEntry.COLUMN_NAME_GENDER, newAccount.getGender());
        values.put(DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS, newAccount.getMaritalStatus());
        values.put(DbContract.AccountEntry.COLUMN_NAME_DOB, ""+newAccount.getDob());
        values.put(DbContract.AccountEntry.COLUMN_NAME_USERNAME, newAccount.getUsername());
        values.put(DbContract.AccountEntry.COLUMN_NAME_PASSWORD, newAccount.getPassword());

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                DbContract.AccountEntry.TABLE_NAME,
                null,
                values);
        accounts.add(newAccount);
    }


}
