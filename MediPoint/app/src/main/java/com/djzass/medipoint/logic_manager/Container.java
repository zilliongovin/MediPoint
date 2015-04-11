package com.djzass.medipoint.logic_manager;


import android.app.Application;

import com.djzass.medipoint.entity.Doctor;
import com.djzass.medipoint.entity.DoctorSchedule;

/**
 * Created by Joshua on 3/4/2015.
 */
public class Container {
    /**
     * Boolean, indicates if first initialization has been done
     */
    private static boolean isInitialized = false;

    /**
     * an instance of AppointmentManager to be used globally
     */
    private static AppointmentManager appointmentManager;
    //private static AccountManager accountManager;
    private static ClinicManager clinicManager;
    private static DoctorManager doctorManager;
    private static DoctorScheduleManager doctorScheduleManager;
    private static ServiceManager serviceManager;
    private static SpecialtyManager specialtyManager;

    public static void init(){

        if (!isInitialized) {
            isInitialized = true;
            appointmentManager = AppointmentManager.getInstance();
            //accountManager = AccountManager.getInstance();
            clinicManager = ClinicManager.getInstance();
            doctorManager = DoctorManager.getInstance();
            doctorScheduleManager = DoctorScheduleManager.getInstance();
            serviceManager = ServiceManager.getInstance();
            specialtyManager = SpecialtyManager.getInstance();
        }
    }

    public static AppointmentManager getAppointmentManager() {
        return appointmentManager;
    }

    public static ClinicManager getClinicManager() {
        return clinicManager;
    }

    public static DoctorManager getDoctorManager() {
        return doctorManager;
    }

    public static DoctorScheduleManager getDoctorScheduleManager() {
        return doctorScheduleManager;
    }

    public static ServiceManager getServiceManager() {
        return serviceManager;
    }

    public static SpecialtyManager getSpecialtyManager() {
        return specialtyManager;
    }
}
