package com.djzass.medipoint.logic_manager;


import android.app.Application;

import com.djzass.medipoint.entity.Doctor;
import com.djzass.medipoint.entity.DoctorSchedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    private static AccountManager accountManager;
    private static AppointmentManager appointmentManager;
    private static ClinicManager clinicManager;
    private static DoctorManager doctorManager;
    private static DoctorScheduleManager doctorScheduleManager;
    private static PatientManager patientManager;
    private static ServiceManager serviceManager;
    private static SpecialtyManager specialtyManager;

    public static void init(){

        if (!isInitialized) {
            isInitialized = true;
            accountManager = AccountManager.getInstance();
            appointmentManager = AppointmentManager.getInstance();
            clinicManager = ClinicManager.getInstance();
            doctorManager = DoctorManager.getInstance();
            doctorScheduleManager = DoctorScheduleManager.getInstance();
            patientManager = patientManager.getInstance();
            serviceManager = ServiceManager.getInstance();
            specialtyManager = SpecialtyManager.getInstance();

        }
    }

    public static AccountManager getAccountManager() {
        return accountManager;
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

    public static PatientManager getPatientManager() {
        return patientManager;
    }

    public static ServiceManager getServiceManager() {
        return serviceManager;
    }

    public static SpecialtyManager getSpecialtyManager() {
        return specialtyManager;
    }

    /**
     * Find difference of days between 2 calendars
     **/
    public static long daysBetween(Calendar startDate, Calendar endDate) {
        //assert: startDate must be before endDate
        if (endDate.before(startDate)) return 100000;
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public static String CalendarToString(Calendar calendar){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        return sdf.format(calendar.getTime());
    }
}
