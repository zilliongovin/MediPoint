package com.djzass.medipoint.logic_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.djzass.medipoint.DbContract;
import com.djzass.medipoint.entity.Account;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Deka on 26/3/2015.
 */
public class AccountDAO extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID + " =?";
    private static final String SQL_VERIFY_USER =
            "SELECT " + DbContract.AccountEntry.COLUMN_NAME_USERNAME + "," +
                    DbContract.AccountEntry.COLUMN_NAME_PASSWORD +
                    " FROM " + DbContract.AccountEntry.TABLE_NAME +
                    " WHERE " + DbContract.AccountEntry.COLUMN_NAME_USERNAME + "=? AND " +
                    DbContract.AccountEntry.COLUMN_NAME_PASSWORD + "=?";

    private static final String SQL_FIND_NRIC =
            "SELECT " + DbContract.AccountEntry.COLUMN_NAME_NRIC + "," +
                    DbContract.AccountEntry.COLUMN_NAME_EMAIL + "," +
                    DbContract.AccountEntry.COLUMN_NAME_PASSWORD +
                    " FROM " + DbContract.AccountEntry.TABLE_NAME +
                    " WHERE " + DbContract.AccountEntry.COLUMN_NAME_NRIC + "=?";

    private static final String SQL_FIND_USERNAME =
            "SELECT " + DbContract.AccountEntry.COLUMN_NAME_USERNAME +
                    " FROM " + DbContract.AccountEntry.TABLE_NAME +
                    " WHERE " + DbContract.AccountEntry.COLUMN_NAME_USERNAME + "=?";

    private static final String SQL_FIND_ACCOUNTID =
            "SELECT " + DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID +
                    " FROM " + DbContract.AccountEntry.TABLE_NAME +
                    " WHERE " + DbContract.AccountEntry.COLUMN_NAME_USERNAME + "=?";
    //private SpecialtyDAO specialtyDao;

    public AccountDAO(Context context) throws SQLException {
        super(context);
        initializeDAO();
    }

    /* CREATE/SAVE
    Inserting account into accounts table and return the account id if insertion successful,
    otherwise -1 will be returned
     */
    public int insertAccount(Account account){
        ContentValues values = new ContentValues();
        int id = getAccountCount();
        values.put(DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID, id);
        values.put(DbContract.AccountEntry.COLUMN_NAME_NAME, account.getName());
        values.put(DbContract.AccountEntry.COLUMN_NAME_NRIC, account.getNric());
        values.put(DbContract.AccountEntry.COLUMN_NAME_EMAIL, account.getEmail());
        values.put(DbContract.AccountEntry.COLUMN_NAME_CONTACTNO, account.getPhoneNumber());
        values.put(DbContract.AccountEntry.COLUMN_NAME_ADDRESS, account.getAddress());
        values.put(DbContract.AccountEntry.COLUMN_NAME_DOB, String.valueOf(account.getDob()));
        values.put(DbContract.AccountEntry.COLUMN_NAME_GENDER, account.getGender());
        values.put(DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS, account.getMaritalStatus());
        values.put(DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP, account.getCitizenship());
        values.put(DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE, account.getCountryOfResidence());
        values.put(DbContract.AccountEntry.COLUMN_NAME_USERNAME, account.getUsername());
        values.put(DbContract.AccountEntry.COLUMN_NAME_PASSWORD, account.getPassword());
        values.put(DbContract.AccountEntry.COLUMN_NAME_NOTIFY_EMAIL, account.getNotifyEmail());
        values.put(DbContract.AccountEntry.COLUMN_NAME_NOTIFY_SMS, account.getNotifySMS());

        database.insert(DbContract.AccountEntry.TABLE_NAME, null, values);
        return id;
    }

    /** READ
     * Getting all accounts from the table
     * returns list of accounts
     * */
    public List<Account> getAccounts(String whereclause) {
        List<Account> accounts = new ArrayList<Account>();

        Cursor cursor = database.query(DbContract.AccountEntry.TABLE_NAME,
                new String[] {DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID,
                             DbContract.AccountEntry.COLUMN_NAME_NAME,
                             DbContract.AccountEntry.COLUMN_NAME_NRIC,
                             DbContract.AccountEntry.COLUMN_NAME_EMAIL,
                             DbContract.AccountEntry.COLUMN_NAME_CONTACTNO,
                             DbContract.AccountEntry.COLUMN_NAME_ADDRESS,
                             DbContract.AccountEntry.COLUMN_NAME_DOB,
                             DbContract.AccountEntry.COLUMN_NAME_GENDER,
                             DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS,
                             DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP,
                             DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE,
                             DbContract.AccountEntry.COLUMN_NAME_USERNAME,
                             DbContract.AccountEntry.COLUMN_NAME_PASSWORD,
                             DbContract.AccountEntry.COLUMN_NAME_NOTIFY_EMAIL,
                             DbContract.AccountEntry.COLUMN_NAME_NOTIFY_SMS
                }, whereclause, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Account account = new Account();
            account.setId(cursor.getInt(0));
            account.setName(cursor.getString(1));
            account.setNric(cursor.getString(2));
            account.setEmail(cursor.getString(3));
            account.setPhoneNumber(cursor.getString(4));
            account.setAddress(cursor.getString(5));

            String temp = cursor.getString(6);
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal  = Calendar.getInstance();
            try {
                cal.setTime(dateformat.parse(temp));
            } catch (ParseException e) {
                Log.d("DAO", "Date parsing exception");
            }

            account.setDob(cal);
            account.setGender(cursor.getString(7));
            account.setMaritalStatus(cursor.getString(8));
            account.setCitizenship(cursor.getString(9));
            account.setCountryOfResidence(cursor.getString(10));
            account.setUsername(cursor.getString(11));
            account.setPassword(cursor.getString(12));
            account.setNotifyEmail(cursor.getInt(13));
            account.setNotifySMS(cursor.getInt(14));
            accounts.add(account);
        }

        return accounts;
    }

    public List<Account> getAllAccounts() {
        return getAccounts(null);
    }

    public List<Account> getAccountById(int accountId) {
        String whereclause = DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID + " = " + accountId;
        return getAccounts(whereclause);
    }

    public List<Account> getAccountByNRIC(String nric) {
        String whereclause = DbContract.AccountEntry.COLUMN_NAME_NRIC + " = " + nric;
        return getAccounts(whereclause);
    }

    public List<Account> getAccountByCitizenship(String citizenship) {
        String whereclause = DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP + " = " + citizenship;
        return getAccounts(whereclause);
    }

    public List<Account> getAccountByUsername(String username) {
        String whereclause = DbContract.AccountEntry.COLUMN_NAME_USERNAME + " = " + username;
        return getAccounts(whereclause);
    }

    public List<Account> getAccountByEmail(String email) {
        String whereclause = DbContract.AccountEntry.COLUMN_NAME_EMAIL + " = " + email;
        return getAccounts(whereclause);
    }

    /*  UPDATE
        returns the number of rows affected by the update
     */
    public long update(Account account) {
        ContentValues values = new ContentValues();
        values.put(DbContract.AccountEntry.COLUMN_NAME_NAME, account.getName());
        values.put(DbContract.AccountEntry.COLUMN_NAME_NRIC, account.getNric());
        values.put(DbContract.AccountEntry.COLUMN_NAME_EMAIL, account.getEmail());
        values.put(DbContract.AccountEntry.COLUMN_NAME_CONTACTNO, account.getPhoneNumber());
        values.put(DbContract.AccountEntry.COLUMN_NAME_ADDRESS, account.getAddress());
        values.put(DbContract.AccountEntry.COLUMN_NAME_DOB, String.valueOf(account.getDob()));
        values.put(DbContract.AccountEntry.COLUMN_NAME_GENDER, account.getGender());
        values.put(DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS, account.getMaritalStatus());
        values.put(DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP, account.getCitizenship());
        values.put(DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE, account.getCountryOfResidence());
        values.put(DbContract.AccountEntry.COLUMN_NAME_USERNAME, account.getUsername());
        values.put(DbContract.AccountEntry.COLUMN_NAME_PASSWORD, account.getPassword());
        values.put(DbContract.AccountEntry.COLUMN_NAME_NOTIFY_EMAIL, account.getNotifyEmail());
        values.put(DbContract.AccountEntry.COLUMN_NAME_NOTIFY_SMS, account.getNotifySMS());

        long result = database.update(DbContract.AccountEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(account.getId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deleteAccount(Account account) {
        return database.delete(DbContract.AccountEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { account.getId() + "" });
    }
    /*
        LOAD
        Load the initial values of the accounts
     */
    public void loadAccounts() {
        List<Account> temp= getAllAccounts();
        for (Account tmp : temp) {
            tmp.print();
        }
    }

    public int getAccountCount(){
        return getAllAccounts().size();
    }

    private void initializeDAO(){
        if (getAccountCount()==0){
        }
    }

    public int onLogin(String username,String password){
        String[] selArgs = {username,password};
        Cursor userCursor = database.rawQuery(SQL_VERIFY_USER, selArgs);
        return userCursor.getCount();
    }

    public Cursor checkAccount(String nric){
        String[] selArgs = {nric};
        return database.rawQuery(SQL_FIND_NRIC,selArgs);
    }

    public int checkUsername(String username){
        String[] selArgs = {username};
        return database.rawQuery(SQL_FIND_USERNAME,selArgs).getCount();

    }

    public Cursor findAccountId(String username){
        String[] selArgs = {username};
        return database.rawQuery(SQL_FIND_ACCOUNTID,selArgs);
    }
}
