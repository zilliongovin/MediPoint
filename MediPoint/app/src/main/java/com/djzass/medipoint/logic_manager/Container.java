package com.djzass.medipoint.logic_manager;


import android.app.Application;

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

    public static void init(){
        isInitialized = true;
        appointmentManager = AppointmentManager.getInstance();
    }

    public static AppointmentManager getAppointmentManager() {
        return appointmentManager;
    }
}
