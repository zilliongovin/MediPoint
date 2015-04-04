package com.djzass.medipoint;

import android.app.Application;

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
 * Created by HP on 4/4/2015.
 */
public class ContainerTwo extends Application {
    private AccountManager GlobalAccountManager;
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
}
