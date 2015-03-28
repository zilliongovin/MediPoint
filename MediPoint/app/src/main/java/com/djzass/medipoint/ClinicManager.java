package com.djzass.medipoint;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Deka on 28/3/2015.
 */
public class ClinicManager {
    ArrayList<Clinic> clinics;
    private ClinicDAO clinicDao;

    ClinicManager() throws SQLException {
        clinics = new ArrayList<Clinic>();

    }
}
