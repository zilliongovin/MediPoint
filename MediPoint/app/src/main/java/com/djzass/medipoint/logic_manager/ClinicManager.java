package com.djzass.medipoint.logic_manager;

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

    public ClinicManager() throws SQLException {
        clinics = new ArrayList<Clinic>();

    }
}
