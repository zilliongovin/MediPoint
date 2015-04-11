package com.djzass.medipoint.logic_manager;

import android.content.Context;

import com.djzass.medipoint.entity.Service;
import com.djzass.medipoint.logic_database.ServiceDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Deka on 28/3/2015.
 */
public class ServiceManager {
    ArrayList<Service> services;
    private ServiceDAO serviceDao;

    public ServiceManager(Context context) throws SQLException {
        serviceDao = new ServiceDAO(context);
        services = new ArrayList<Service>();
        services = serviceDao.getAllServices();
    }

    public Service getServiceById(int id){
    	for (Service s: services){
    		if (s.getId() == id)
    			return s;
    	}
    	return null;
    }

    public String getServiceNameById(int id){
    	Service s = getServiceById(id);
    	if (s == null)
    		return null;
    	else
    		return s.getName();
    }

}
