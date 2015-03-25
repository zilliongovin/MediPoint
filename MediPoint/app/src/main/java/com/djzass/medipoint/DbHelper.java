package com.djzass.medipoint;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shreyas on 3/17/2015.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String CHAR_EIGHT_TYPE = " CHAR(8)";
    private static final String CHAR_TEN_TYPE = " CHAR(10)";
    private static final String VARCHAR_TEN_TYPE = " VARCHAR(10)";
    private static final String VARCHAR_THIRTY_TYPE = " VARCHAR(30)";
    private static final String VARCHAR_FIFTY_TYPE = " VARCHAR(50)";
    private static final String INT_TYPE = " INTEGER";
    private static final String DATETIME_TYPE = " DATETIME";
    private static final String COMMA_SEP = ",";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MediPoint.db";

    //CREATE TABLE : private static final String SQL_CREATE_TABLE_NAME = "CREATE TABLE " + " (" + " );";
    /* ACCOUNT TABLE*/
    private static final String SQL_CREATE_ACCOUNT =
            "CREATE TABLE " + DbContract.AccountEntry.TABLE_NAME + " (" +
                    DbContract.AccountEntry.COLUMN_NAME_ACCOUNT_ID + VARCHAR_TEN_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_NAME + VARCHAR_FIFTY_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_NRIC + CHAR_TEN_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_EMAIL + VARCHAR_THIRTY_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_CONTACTNO + VARCHAR_TEN_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_ADDRESS + VARCHAR_TEN_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_DOB + DATETIME_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_GENDER + " CHAR(1)" + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_MARITAL_STATUS + VARCHAR_TEN_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_CITIZENSHIP + VARCHAR_THIRTY_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_COUNTRY_OF_RESIDENCE + VARCHAR_THIRTY_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_USERNAME + VARCHAR_THIRTY_TYPE + COMMA_SEP +
                    DbContract.AccountEntry.COLUMN_NAME_PASSWORD + VARCHAR_THIRTY_TYPE +
                    " );";

    // If you change the database schema, you must increment the database version.
    private static final String SQL_DELETE_ACCOUNT =
            "DROP TABLE IF EXISTS " + DbContract.AccountEntry.TABLE_NAME + ";";

    private static final String SQL_VERIFY_USER =
            "SELECT " + DbContract.AccountEntry.COLUMN_NAME_USERNAME + COMMA_SEP +
            DbContract.AccountEntry.COLUMN_NAME_PASSWORD + " FROM " + DbContract.AccountEntry.TABLE_NAME +
            " WHERE " + DbContract.AccountEntry.COLUMN_NAME_USERNAME + "=? AND " +
            DbContract.AccountEntry.COLUMN_NAME_PASSWORD + "=?";

    public int onLogin(String username,String password,SQLiteDatabase db){
        String[] selArgs = {username,password};
        Cursor userCursor = db.rawQuery(SQL_VERIFY_USER, selArgs);
        return userCursor.getCount();
    }

    /* COUNTRY TABLE  */
    private static final String SQL_CREATE_COUNTRY = "CREATE TABLE " + DbContract.CountryEntry.TABLE_NAME + " (" +
            DbContract.CountryEntry.COLUMN_NAME_COUNTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DbContract.CountryEntry.COLUMN_NAME_COUNTRY_NAME + VARCHAR_THIRTY_TYPE + " );";

    private static final String SQL_DELETE_COUNTRY =
            "DROP TABLE IF EXISTS " + DbContract.CountryEntry.TABLE_NAME + ";";

    /* APPOINTMENT TABLE */
    private static final String SQL_CREATE_APPOINTMENT = "CREATE TABLE " + DbContract.AppointmentEntry.TABLE_NAME + " (" +
            DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DbContract.AppointmentEntry.COLUMN_NAME_CLINIC_ID + INT_TYPE + COMMA_SEP +
            DbContract.AppointmentEntry.COLUMN_NAME_PATIENT_ID + CHAR_EIGHT_TYPE + COMMA_SEP +
            DbContract.AppointmentEntry.COLUMN_NAME_DOCTOR_ID + CHAR_EIGHT_TYPE + COMMA_SEP +
            DbContract.AppointmentEntry.COLUMN_NAME_DATE_TIME + DATETIME_TYPE + COMMA_SEP +
            DbContract.AppointmentEntry.COLUMN_NAME_START_TIME + DATETIME_TYPE + COMMA_SEP +
            DbContract.AppointmentEntry.COLUMN_NAME_END_TIME + DATETIME_TYPE + COMMA_SEP +
            DbContract.AppointmentEntry.COLUMN_NAME_SERVICE_ID + INT_TYPE + COMMA_SEP +
            DbContract.AppointmentEntry.COLUMN_NAME_SPECIALTY_ID + INT_TYPE +  " );";

    private static final String SQL_DELETE_APPOINTMENT =
            "DROP TABLE IF EXISTS " + DbContract.AppointmentEntry.TABLE_NAME + ";";

    /*DOCTOR TABLE*/
    private static final String SQL_CREATE_DOCTOR = "CREATE TABLE " + DbContract.DoctorEntry.TABLE_NAME + " (" +
            DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID + VARCHAR_TEN_TYPE + COMMA_SEP +
            DbContract.DoctorEntry.COLUMN_NAME_NAME + VARCHAR_THIRTY_TYPE + COMMA_SEP +
            DbContract.DoctorEntry.COLUMN_NAME_SPECIALIZATION_ID + INT_TYPE + COMMA_SEP +
            DbContract.DoctorEntry.COLUMN_NAME_PRACTICE_DURATION + INT_TYPE + " );";

    private static final String SQL_DELETE_DOCTOR =
            "DROP TABLE IF EXISTS " + DbContract.DoctorEntry.TABLE_NAME + ";";

    /*DOCTOR SCHEDULE TABLE*/
    private static final String SQL_CREATE_DOCTOR_SCHEDULE = "CREATE TABLE " + DbContract.DoctorScheduleEntry.TABLE_NAME + " (" +
            DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "+ COMMA_SEP +
            DbContract.DoctorScheduleEntry.COLUMN_NAME_DOCTOR_ID + INT_TYPE + COMMA_SEP +
            DbContract.DoctorScheduleEntry.COLUMN_NAME_CLINIC_ID + INT_TYPE + COMMA_SEP +
            DbContract.DoctorScheduleEntry.COLUMN_NAME_START_TIME + DATETIME_TYPE + COMMA_SEP +
            DbContract.DoctorScheduleEntry.COLUMN_NAME_END_TIME + DATETIME_TYPE  + " );";

    private static final String SQL_DELETE_DOCTOR_SCHEDULE =
            "DROP TABLE IF EXISTS " + DbContract.DoctorScheduleEntry.TABLE_NAME + ";";

    /*CLINIC TABLE*/
    private static final String SQL_CREATE_CLINIC = "CREATE TABLE " + DbContract.ClinicEntry.TABLE_NAME + " (" +
            DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            DbContract.ClinicEntry.COLUMN_NAME_COUNTRY_ID + INT_TYPE + COMMA_SEP +
            DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME + VARCHAR_THIRTY_TYPE + COMMA_SEP +
            DbContract.ClinicEntry.COLUMN_NAME_TEL_NUMBER + VARCHAR_TEN_TYPE + COMMA_SEP +
            DbContract.ClinicEntry.COLUMN_NAME_FAX_NUMBER + VARCHAR_TEN_TYPE + COMMA_SEP +
            DbContract.ClinicEntry.COLUMN_NAME_ZIPCODE + VARCHAR_TEN_TYPE + " );";


    private static final String SQL_DELETE_CLINIC =
        "DROP TABLE IF EXISTS " + DbContract.ClinicEntry.TABLE_NAME + ";";

    /*SPECIALTY TABLE*/
    private static final String SQL_CREATE_SPECIALTY = "CREATE TABLE " + DbContract.SpecialtyEntry.TABLE_NAME + " (" +
            DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME + VARCHAR_THIRTY_TYPE + " );";

    private static final String SQL_DELETE_SPECIALTY =
            "DROP TABLE IF EXISTS " + DbContract.SpecialtyEntry.TABLE_NAME + ";";

    /*SERVICE TABLE*/
    private static final String SQL_CREATE_SERVICE = "CREATE TABLE " + DbContract.ServiceEntry.TABLE_NAME + " (" +
            DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            DbContract.ServiceEntry.COLUMN_NAME_SPECIALTY_ID + INT_TYPE + COMMA_SEP +
            DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME + VARCHAR_THIRTY_TYPE + COMMA_SEP +
            DbContract.ServiceEntry.COLUMN_NAME_SERVICE_DURATION + INT_TYPE + " );";

    private static final String SQL_DELETE_SERVICE =
            "DROP TABLE IF EXISTS " + DbContract.ServiceEntry.TABLE_NAME + ";";

    /*PREAPPOINTMENT ACTION ENTRY*/
    private static final String SQL_CREATE_PREAPPOINTMENT_ACTION = "CREATE TABLE " + DbContract.PreAppoinmentActionEntry.TABLE_NAME + " (" +
            DbContract.PreAppoinmentActionEntry.COLUMN_NAME_PREAPPOINTMENT_ACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            DbContract.PreAppoinmentActionEntry.COLUMN_NAME_SERVICE_ID + INT_TYPE + COMMA_SEP +
            DbContract.PreAppoinmentActionEntry.COLUMN_NAME_PREAPPOINTMENT_ACTION_NAME + TEXT_TYPE + " );";

    private static final String SQL_DELETE_PREAPPOINTMENT_ACTION =
            "DROP TABLE IF EXISTS " + DbContract.PreAppoinmentActionEntry.TABLE_NAME + ";";

    /*PATIENT ENTRY*/
    private static final String SQL_CREATE_PATIENT = "CREATE TABLE " + DbContract.PatientEntry.TABLE_NAME + " (" +
            DbContract.PatientEntry.COLUMN_NAME_PATIENT_ID + VARCHAR_TEN_TYPE + COMMA_SEP +
            DbContract.PatientEntry.COLUMN_NAME_AGE + INT_TYPE + COMMA_SEP +
            DbContract.PatientEntry.COLUMN_NAME_ACCOUNT_ID + INT_TYPE  + " );";

    private static final String SQL_DELETE_PATIENT =
            "DROP TABLE IF EXISTS " + DbContract.PatientEntry.TABLE_NAME + ";";

    /*ALLERGY ENTRY*/
    private static final String SQL_CREATE_ALLERGY = "CREATE TABLE " + DbContract.AllergyEntry.TABLE_NAME + " (" +
            DbContract.AllergyEntry.COLUMN_NAME_ALLERGY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            DbContract.AllergyEntry.COLUMN_NAME_ALLERGY_NAME + VARCHAR_THIRTY_TYPE  + " );";

    private static final String SQL_DELETE_ALLERGY =
            "DROP TABLE IF EXISTS " + DbContract.AllergyEntry.TABLE_NAME + ";";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static DbHelper instance;

    public static synchronized DbHelper getHelper(Context context) {
        if (instance == null)
            instance = new DbHelper(context);
        return instance;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACCOUNT);
        db.execSQL(SQL_CREATE_ALLERGY);
        db.execSQL(SQL_CREATE_APPOINTMENT);
        db.execSQL(SQL_CREATE_CLINIC);
        db.execSQL(SQL_CREATE_COUNTRY);
        db.execSQL(SQL_CREATE_DOCTOR);
        db.execSQL(SQL_CREATE_DOCTOR_SCHEDULE);
        db.execSQL(SQL_CREATE_PATIENT);
        db.execSQL(SQL_CREATE_PREAPPOINTMENT_ACTION);
        db.execSQL(SQL_CREATE_SERVICE);
        db.execSQL(SQL_CREATE_SPECIALTY);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ACCOUNT);
        db.execSQL(SQL_DELETE_ALLERGY);
        db.execSQL(SQL_DELETE_APPOINTMENT);
        db.execSQL(SQL_DELETE_CLINIC);
        db.execSQL(SQL_DELETE_COUNTRY);
        db.execSQL(SQL_DELETE_DOCTOR);
        db.execSQL(SQL_DELETE_DOCTOR_SCHEDULE);
        db.execSQL(SQL_DELETE_PATIENT);
        db.execSQL(SQL_DELETE_PREAPPOINTMENT_ACTION);
        db.execSQL(SQL_DELETE_SERVICE);
        db.execSQL(SQL_DELETE_SPECIALTY);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    //CRUD (Create, Read, Update and Delete) Operations

    /*AppointmentCrud
    //create
    public int createAppointment(SQLiteDatabase db, Appointment newAppointment){
        ContentValues values = new ContentValues();
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID, newAppointment.getId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID, newAppointment.getId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID, newAppointment.getId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID, newAppointment.getId());
        values.put(DbContract.AppointmentEntry.COLUMN_NAME_APPOINTMENT_ID, newAppointment.getId());

        long newAppointmentId;
        newAppointmentId = db.insert(DbContract.AppointmentEntry.TABLE_NAME, null, values);

        appointments.add(newAppointment);

        return newAppointmentId;
    }
    //fetch*/
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
    public void closeDb(SQLiteDatabase db) {
        if (db != null && db.isOpen())
            db.close();
    }
}
