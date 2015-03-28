package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deka on 28/3/2015.
 */
public class ServiceDAO  extends DbDAO{
    private static final String WHERE_ID_EQUALS = DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID
            + " =?";

    public ServiceDAO(Context context) {
        super(context);
    }


    /*
        CREATE
         Inserting doctor schedule into doctor schedules table and return the row id if insertion successful,
     otherwise -1 will be returned
     */
    public long insertService(Service service){
        ContentValues values = new ContentValues();
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME, service.getName());
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SPECIALTY_ID, service.getSpecialty().getId());
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_DURATION, service.getDuration());
        values.put(DbContract.ServiceEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS, service.getPreAppointmentActions());

        // Inserting Row
        return database.insert(DbContract.ServiceEntry.TABLE_NAME, null, values);
    }

    /*
        READ
      * Getting all services from the table
     * returns list of services
     * */
    public List<Service> getServices() {
        List<Service> services = new ArrayList<Service>();
        //MUST JOIN TABLES

        // Select all rows
        // String selectQuery = "SELECT  * FROM " + DbContract.ServiceEntry.TABLE_NAME;
        Cursor cursor = database.query(DbContract.ServiceEntry.TABLE_NAME,
                new String[] { DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID,
                        DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Service service= new Service();
            service.setId(cursor.getInt(0));
            service.setName(cursor.getString(1));
            services.add(service);
        }

        return services;
    }

    /*
        FETCH BY ID
     */
    //MUST JOIN TABLES
    //READ SINGLE ROW
    public Service getServiceById(long serviceId) {
        String selectQuery = "SELECT  * FROM " + DbContract.ServiceEntry.TABLE_NAME + " WHERE "
                + DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID + " = " + serviceId;

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        // Create the class object, then set the attribute from content of the exisiting data in the table
        Service service = new Service();
        service.setId(c.getInt(c.getColumnIndex(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID)));
        service.setName(c.getString(c.getColumnIndex(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME)));

        return service;
    }
    /*
        UPDATE
       returns the number of rows affected by the update
     */
<<<<<<< HEAD
    public int update(Service service) {
=======
    public long update(Service service) {
>>>>>>> origin/master
        ContentValues values = new ContentValues();
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME, service.getName());
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SPECIALTY_ID, service.getSpecialty().getId());
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_DURATION, service.getDuration());
        values.put(DbContract.ServiceEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS, service.getPreAppointmentActions());

        long result = database.update(DbContract.ServiceEntry.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(service.getId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    /*
        DELETE
        returns the number of rows affected if a whereClause is passed in, 0 otherwise
     */
    public int deleteService(Service service) {
        return database.delete(DbContract.ServiceEntry.TABLE_NAME,
                WHERE_ID_EQUALS, new String[] { service.getId() + "" });
    }
    /*
        LOAD
        Load the initial values of the services
     */
    public void loadServices() {
        Service s1 = new Service();
        Service s2 = new Service();
        Service s3 = new Service();

        List<Service> services = new ArrayList<Service>();
        services.add(s1);
        services.add(s2);
        services.add(s3);
        for (Service s: services) {
<<<<<<< HEAD
            database.insert(s);
=======
            insertService(s);
>>>>>>> origin/master
        }
    }
}
