package com.djzass.medipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deka on 28/3/2015.
 */
public class ServiceDAO  extends DbDAO{
    public static final String SERVICE_WITH_PREFIX = "service.";
    public static final String SPECIALTY_WITH_PREFIX = "specialty.";

    private static final String WHERE_ID_EQUALS = DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID
            + " =?";

    public ServiceDAO(Context context) throws SQLException {
        super(context);
        initializeDAO();
    }

    /*
        CREATE
         Inserting doctor schedule into doctor schedules table and return the row id if insertion successful,
     otherwise -1 will be returned
     */
    public long insertService(Service service){
        ContentValues values = new ContentValues();
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID, getServiceCount());
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME, service.getName());
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SPECIALTY_ID, service.getSpecialtyId());
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
    public List<Service> getServices(String whereclause) {
        List<Service> services = new ArrayList<Service>();

        String query = "SELECT " + SERVICE_WITH_PREFIX + DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID + ", " +
                SERVICE_WITH_PREFIX + DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME + ", " +
                SERVICE_WITH_PREFIX + DbContract.ServiceEntry.COLUMN_NAME_SERVICE_DURATION + ", " +
                SERVICE_WITH_PREFIX + DbContract.ServiceEntry.COLUMN_NAME_PREAPPOINTMENT_ACTIONS + ", " +
                SERVICE_WITH_PREFIX + DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME +
                " FROM " + DbContract.ServiceEntry.TABLE_NAME + "service" + ", " +
                DbContract.SpecialtyEntry.TABLE_NAME + "specialty WHERE " + SERVICE_WITH_PREFIX +
                DbContract.ServiceEntry.COLUMN_NAME_SPECIALTY_ID + " = " + SPECIALTY_WITH_PREFIX +
                DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID + whereclause;

        Log.d("query", query);
        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Service service= new Service();
            service.setId(cursor.getInt(0));
            service.setName(cursor.getString(1));
            service.setDuration(cursor.getInt(2));
            service.setPreAppointmentActions(cursor.getString(3));
            service.setSpecialtyId(cursor.getInt(4));
            service.getDuration();
            services.add(service);
        }

        return services;
    }

    public List<Service> getAllServices() {
        return getServices("");
    }

    public List<Service> getServicesByID(int id) {
        String whereclause = " AND " + DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID + " + " + id;
        return getServices(whereclause);
    }

    public List<Service> getServicesBySpecialtyID(int specialtyId) {
        String whereclause = " AND " + DbContract.ServiceEntry.COLUMN_NAME_SPECIALTY_ID + " + " + specialtyId;
        return getServices(whereclause);
    }

    /*
        FETCH BY ID
     */
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

    public long update(Service service) {
        ContentValues values = new ContentValues();
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME, service.getName());
        values.put(DbContract.ServiceEntry.COLUMN_NAME_SPECIALTY_ID, service.getSpecialtyId());
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
        List<Service> temp= getAllServices();
        for (Service tmp : temp) {
            tmp.print();
        }
    }

    public int getServiceCount(){
        return getAllServices().size();
    }

    private void initializeDAO(){
        if (getServiceCount()==0){
        }
    }
}
