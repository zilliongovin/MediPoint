package com.djzass.medipoint.logic_manager;

import android.content.Context;

import com.djzass.medipoint.entity.Clinic;
import com.djzass.medipoint.logic_database.ClinicDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Deka on 28/3/2015.
 */
public class ClinicManager {
    ArrayList<Clinic> clinics;
    private ClinicDAO clinicDao;

    public ClinicManager(Context context) throws SQLException {
        clinicDao = new ClinicDAO(context);
        clinics = new ArrayList<Clinic>();
        clinics = clinicDao.getAllClinics();
    }

    public Clinic getClinicById(int id){
    	for (Clinic c: clinics){
    		if (c.getId() == id)
    			return c;
    	}
    	return null;
    }

    public String getClinicNameById(int id){
    	Clinic c = getClinicById(id);
    	if (c == null)
    		return null;
    	else
    		return c.getName();
    }
}
