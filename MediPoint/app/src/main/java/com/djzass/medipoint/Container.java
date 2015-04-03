package com.djzass.medipoint;

import android.app.Application;
import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.djzass.medipoint.logic_database.AccountDAO;
import com.djzass.medipoint.logic_database.AppointmentDAO;
import com.djzass.medipoint.logic_database.ClinicDAO;
import com.djzass.medipoint.logic_database.DoctorDAO;
import com.djzass.medipoint.logic_database.DoctorScheduleDAO;
import com.djzass.medipoint.logic_database.PatientDAO;
import com.djzass.medipoint.logic_database.ServiceDAO;
import com.djzass.medipoint.logic_database.SpecialtyDAO;
import com.djzass.medipoint.logic_manager.AccountManager;
import com.djzass.medipoint.logic_manager.AppointmentManager;
import com.djzass.medipoint.logic_manager.ClinicManager;

/**
 * Created by Joshua on 3/4/2015.
 */
public class Container extends Application {

    private static Context context;

    public static AccountManager GlobalAccountManager;
    public static AppointmentManager GlobalAppointmentManager;
    public static ClinicManager GlobalClinicManager;

    public static AccountDAO GlobalAccountDAO;
    public static AppointmentDAO GlobalAppointmentDAO;
    public static ClinicDAO GlobalClinicDAO;
    public static DoctorDAO GlobalDoctorDAO;
    public static DoctorScheduleDAO GlobalDoctorScheduleDAO;
    public static PatientDAO GlobalPatientDAO;
    public static ServiceDAO GlobalServiceDAO;
    public static SpecialtyDAO GlobalSpecialtyDAO;

    @Override
    public void onCreate(){
        super.onCreate();
        Container.context = getApplicationContext();
        initialize();
    }

    public static Context getAppContext() {
        return Container.context;
    }


    public void initialize() {
        try {
            AccountManager GlobalAccountManager = new AccountManager(getAppContext());
            AppointmentManager GlobalAppointmentManager = new AppointmentManager();
            ClinicManager GlobalClinicManager = new ClinicManager();

            AccountDAO GlobalAccountDAO = new AccountDAO(getAppContext());
            AppointmentDAO GlobalAppointmentDAO = new AppointmentDAO(getAppContext());
            ClinicDAO GlobalClinicDAO = new ClinicDAO(getAppContext());
            DoctorDAO GlobalDoctorDAO = new DoctorDAO(getAppContext());
            DoctorScheduleDAO GlobalDoctorScheduleDAO = new DoctorScheduleDAO(getAppContext());
            PatientDAO GlobalPatientDAO = new PatientDAO(getAppContext());
            ServiceDAO GlobalServiceDAO = new ServiceDAO(getAppContext());
            SpecialtyDAO GlobalSpecialtyDAO = new SpecialtyDAO(getAppContext());

        } catch (java.sql.SQLException e) {
            Log.d("DAO", "initializing exception");
        }
    }
}
