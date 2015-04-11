package com.djzass.medipoint.logic_manager;

import android.content.Context;

import com.djzass.medipoint.entity.Service;
import com.djzass.medipoint.logic_database.ServiceDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 10/4/2015.
 */
public class ServiceManager {

    /**
     * An instance of {@link ServiceDAO}. This is to be re-instated with context before use.
     */
    private ServiceDAO serviceDao;

    /**
     * An instance of {@link ServiceManager}. Use this to promote singleton design pattern.
     */
    private static ServiceManager instance = new ServiceManager();

    /**
     * returns ServiceManager instance
     */
    public static ServiceManager getInstance() {
        return instance;
    }
    /**
     * Re-initializes the ServiceDAO with the given context
     */
    private void updateServiceDao(Context context){
            try {
            serviceDao = new ServiceDAO(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Service> getServices(Context context){
        updateServiceDao(context);
        return serviceDao.getAllServices();
    }

    public List<Service> getServicesByID(int serviceid,Context context) {
        updateServiceDao(context);
        return serviceDao.getServicesByID(serviceid);
    }

    public List<Service> getServicesBySpecialtyID(int specialtyId,Context context) {
        updateServiceDao(context);
        return serviceDao.getServicesBySpecialtyID(specialtyId);
    }

    public String getServiceNameByID(int serviceid,Context context) {
        updateServiceDao(context);
        List<Service> temp = serviceDao.getServicesByID(serviceid);
        return temp.get(0).getName();
    }


    /**
     * insert @param service to database with context @param context   
     * @return row no, -1 if fail
     */
    public long createService(Service service, Context context){
        updateServiceDao(context);
        long ret = serviceDao.insertService(service);
        return ret;
    }

    /**
     * edit @param service in database based on id with context @param context   
     * @return row no, -1 if fail
     */
    public long editService(Service service, Context context){
        // update service according to its id in database
        updateServiceDao(context);
        long ret = serviceDao.update(service);
        return ret;
    }

    /**
     * delete @param service in database based on id with context @param context   
     * @return row no, -1 if fail
     */
    public long cancelService(Service service, Context context){
        // delete service according to its id in database 
        long ret = serviceDao.deleteService(service);
        updateServiceDao(context);
        return ret;
    }
}
