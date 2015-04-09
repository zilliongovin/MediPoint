package com.djzass.medipoint.logic_manager;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import java.sql.SQLException;

import com.djzass.medipoint.SessionManager;
import com.djzass.medipoint.entity.Account;
import com.djzass.medipoint.logic_database.AccountDAO;


import com.djzass.medipoint.logic_database.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AccountManager {
    //private ArrayList<Account> accounts;
    private Account newAccount;
    //DbHelper dbHelper;
    //SQLiteDatabase db;
    AccountDAO accountDAO;
    SessionManager session;
    Context context;

	public AccountManager(Context context){

        //accounts = new ArrayList<Account>();
        //dbHelper = new DbHelper(context);
        //db = dbHelper.getWritableDatabase();
        try {
            accountDAO = new AccountDAO(context);
            session = new SessionManager(context);
            this.context = context;
        }
        catch(SQLException sqlEx)
        {
            sqlEx.getStackTrace();
        }
    }

    public Cursor findAccount(String nric){
        //Cursor cursor = dbHelper.checkAccount(nric,db);
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
        //updateDatabase(newAccount);
        try {
            AccountDAO acctDAO = new AccountDAO(context);
            return acctDAO.insertAccount(newAccount);
            //Toast.makeText(context,""+id,Toast.LENGTH_LONG).show();
        }
        catch(SQLException sqlExcep){
            sqlExcep.getStackTrace();
        }
        return -1;

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
        return accountDAO.checkUsername(username)>0? true:false;
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
        int notify_email = PageTwo.getInt("NOTIFY_EMAIL");
        int notify_sms = PageTwo.getInt("NOTIFY_SMS");
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTimeInMillis(dob);


        String username = PageThree.getString("USERNAME");
        String password = PageThree.getString("PASSWORD");
        Account newAccount = new Account(username,password,name,nric,email,contact,gender,address,maritalStatus,dobCal,citizenship,countryOfResidence,notify_email,notify_sms);
        return newAccount;
    }

    /*public void updateDatabase(Account newAccount){
        // Create a new map of values, where column names are the keys

        ContentValues values = new ContentValues();
        values.put(DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID,"-1");
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
        //Toast.makeText(this,""+newRowId,Toast.LENGTH_LONG).show();
        db = dbHelper.getWritableDatabase();
        ContentValues temp = new ContentValues();
        temp.put(DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID,""+newRowId);
        //String idString[] = {""+newRowId};
        db.update(DbContract.AccountEntry.TABLE_NAME,temp, DbContract.AccountEntry.COLUMN_NAME_USERNAME + "='" + newAccount.getUsername() + "'",null);
    }*/

    public long getLoggedInAccountId(){
        try {
            accountDAO = new AccountDAO(context);
            //session = new SessionManager(context);
        }
        catch(SQLException sqlEx) {
            sqlEx.getStackTrace();
        }
        return session.getAccountId(accountDAO);
    }

    public Account getAccountById(long id) throws ParseException {
        Cursor cursor = accountDAO.getAccountById(id);

        String name = cursor.getString(1);
        String nric = cursor.getString(2);
        String email = cursor.getString(3);
        String contact = cursor.getString(4);
        String address = cursor.getString(5);
        String dob = cursor.getString(6);
        String gender = cursor.getString(7);
        String maritalStatus = cursor.getString(8);
        String citizenship = cursor.getString(9);
        String countryOfResidence = cursor.getString(10);
        String username = cursor.getString(11);
        String password = cursor.getString(12);
        int isEmail = cursor.getInt(13);
        int isSMS = cursor.getInt(14);

        SimpleDateFormat sdf = new SimpleDateFormat();
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(sdf.parse(dob));

        /*long id, String username, String password, String name, String nric,
                String email, String phoneNumber, String gender, String address,
                String maritalStatus, Calendar dob, String citizenship,
                String countryOfResidence, int notifyEmail, int notifySMS*/
        return new Account(id,username,password,name,nric,email,contact,gender,address,maritalStatus,dobCal,citizenship,countryOfResidence,isEmail,isSMS);

    }


}
