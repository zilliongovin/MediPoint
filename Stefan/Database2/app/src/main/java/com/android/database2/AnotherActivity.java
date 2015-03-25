package com.android.database2;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AnotherActivity extends ListActivity {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";

    private String TAG = "dbase";
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//get the cursor to load into the list
        cursor = readDatabase();
//      prepare the parameters for the list
        String[] columns = {MyDBHandler.COLUMN_PRODUCTNAME, MyDBHandler.COLUMN_QUANTITY};
        int[] to = {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, columns, to, 0);
        setListAdapter(listAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //close database hee?
        Log.i(TAG, "in MyListActivity onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "in MyListActivity onPause()");
//        close the cursor when the activity closes so we don't leak memory
        cursor.close();
    }

    /*gets all the records out of the database and puts them into a cursor*/
    private Cursor readDatabase() {
//        get the database
        MyDBHandler dbHelper = new MyDBHandler(this,
               DATABASE_NAME,
                null,DATABASE_VERSION);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//prepare the parameters
        String selection = null;
        String[] selectionArguments = null;
        String groupBy = null;
        String having = null;
        String sortOrder = null;
//        do the query
        Cursor cursor = db.query(MyDBHandler.TABLE_PRODUCTS,
                null, selection, selectionArguments, groupBy, having, sortOrder);

        return cursor;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this, "Selected item ID: " + id, Toast.LENGTH_SHORT).show();
    }
}
