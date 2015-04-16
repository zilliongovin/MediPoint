package com.djzass.medipoint.logic_manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.djzass.medipoint.logic_database.AccountDAO;

import java.sql.SQLException;

/**
 * Created by Shreyas on 3/24/2015.
 */
public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    public static final String PREF_NAME = "UserSession";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_USERNAME = "username";

    // Email address (make variable public to access from outside)
    public static final String KEY_PASSWORD = "password";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String username, String password){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_USERNAME, username);

        // Storing email in pref
        editor.putString(KEY_PASSWORD, password);

        // commit changes
        editor.commit();
    }

    public void deleteLoginSession(){
        editor.clear();
        editor.commit();
    }

    public long getAccountId()throws SQLException{
        AccountDAO accountDAO = new AccountDAO(_context);
        String username = pref.getString(KEY_USERNAME,"");
        Cursor cursor = accountDAO.findAccountId(username);
        if(cursor!=null && cursor.moveToFirst())
            return cursor.getInt(0);
        return -1;
    }
}
