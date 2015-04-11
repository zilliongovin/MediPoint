package com.djzass.medipoint.logic_manager;

import android.content.Context;

import com.djzass.medipoint.entity.Service;
import com.djzass.medipoint.logic_database.ServiceDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Deka on 28/3/2015.
 */
public class SpecialtyManager {
    ArrayList<Speciaty> specialties;
    private SpecialtyDAO specialtyDao;

    public ServiceManager(Context context) throws SQLException {
        specialtyDao = new ServiceDAO(context);
        specialties = new ArrayList<Service>();
        specialties = specialtyDao.getAllSpecialties();
    }

    public Specialty getSpecialtyById(int id){
    	for (Specialty s: specialties){
    		if (s.getId() == id)
    			return s;
    	}
    	return null;
    }

    public String getSpecialtyNameById(int id){
    	Specialty s = getSpecialtyById(id);
    	if (s == null)
    		return null;
    	else
    		return s.getName();
    }

}
