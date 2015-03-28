//Create
public long createClassName(ClassName classInstance, long[] foreign_keys) {
    SQLiteDatabase db = this.getWritableDatabase();
 
    ContentValues values = new ContentValues();
    //put all the attributes of ClassName into values based on appropriate column names
    values.put(COLUMN_NAME_ID, classInstance.getId());
    values.put(COLUMN_NAME_NAME, classInstance.getName());
    //....
 
    // insert row
    long classInstance_id = db.insert(TABLE_NAME, null, values);
 
    // assigning tags to todo
    for (long foreign_key : foreign_keys) {
        createTodoTag(classInstance, foreign_key);
    }
 
    return classInstance_id;
}

//Fetch single data from database, where id = ....
public ClassName getClassName(long classInstance_id) {
    SQLiteDatabase db = this.getReadableDatabase();
 
    String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE "
            + COLUMN_NAME_ID + " = " + classInstance_id;
 
    Log.e(LOG, selectQuery);
 
    Cursor c = db.rawQuery(selectQuery, null);
 
    if (c != null)
        c.moveToFirst();

    // Create the class object, then set the attribute from content of the exisiting data in the table
    ClassName cn = new ClassName();
    cn.setId(c.getInt(c.getColumnIndex(COLUMN_NAME_ID)));
    cn.setName(c.getString(c.getColumnIndex(COLUMN_NAME_NAME)));
    // ADD MORE ATTRIBUTES!!

    return cn;
}

//Fetch all datas from class in database , select * from table_name
public List<ClassName> getAllClassNames() {
    List<ClassName> classNames = new ArrayList<ClassName>();
    String selectQuery = "SELECT  * FROM " + TABLE_NAME;
 
    Log.e(LOG, selectQuery);
 
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);
 
    // looping through all rows and adding to list
    if (c.moveToFirst()) {
        do {
            // Create the class object, then set the attribute from content of the exisiting data in the table
            ClassName cn = new ClassName();
            cn.setId(c.getInt(c.getColumnIndex(COLUMN_NAME_ID)));
            cn.setName(c.getString(c.getColumnIndex(COLUMN_NAME_NAME)));
            // ADD MORE ATTRIBUTES!!
            classNames.add(cn);
        } while (c.moveToNext());
    }
 
    return classNames;
}
//Update
public int updateClassName(ClassName classInstance) {
    SQLiteDatabase db = this.getWritableDatabase();
 
    ContentValues values = new ContentValues();
    //put all the attributes of ClassName into values based on appropriate column names
    values.put(COLUMN_NAME_ID, classInstance.getId());
    values.put(COLUMN_NAME_NAME, classInstance.getName());

    // updating row
    return db.update(TABLE_NAME, values, COLUMN_NAME_ID + " = ?",
            new String[] { String.valueOf(classInstance.getId()) });
}
//Delete
public void deleteClassName(long className_id) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_NAME, COLUMN_NAME_ID + " = ?",
            new String[] { String.valueOf(className_id) });
}

