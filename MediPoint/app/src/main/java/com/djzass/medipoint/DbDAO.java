package com.djzass.medipoint;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;

/**
 * Created by Deka on 26/3/2015.
 */
public class DbDAO {
    protected SQLiteDatabase database;
    private DbHelper dbHelper;
    private Context mContext;

<<<<<<< HEAD
    public DbDAO(Context context) {
=======
    public DbDAO(Context context) throws SQLException {
>>>>>>> origin/master
        this.mContext = context;
        dbHelper = DbHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DbHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        database = null;
    }
}
