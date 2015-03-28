package com.djzass.medipoint;

import java.util.Date;
import java.util.Calendar;
import java.lang.String;
import java.util.ArrayList;

public class AppointmentManager {
    private ArrayList<Appointment> appointments;


    public ArrayList<Boolean> getAvailableTime(Calendar date, int patient, int doctor){
        //returns array of boolean denoting whether or not each timeframe is free
        ArrayList<Boolean> ret = new ArrayList<Boolean>();
        for (int i=0; i<48; ++i){
            ret.add(true);
        }
        for (Appointment temp : appointments) {
            if (temp.getDate().compareTo(date)==0 && (temp.getPatient() == patient || temp.getDoctor() == doctor)) {
                for (int i=temp.getTimeframe().getStartTime(); i<=temp.getTimeframe().getEndTime(); ++i){
                    ret.set(i,false);
                }
            }
        }

        return ret;
    }

    public ArrayList<Boolean> getTimeTable(Calendar date, int patient, int doctor, int startTime, int endTime, int duration){
        //returns array of boolean denoting the availability of timeslots
        //starting from Timeframe [starttime] (default = opening time) until [endTime] (closing time, last active time)
        //duration is in terms of 30 mins. 1 hour = 2 duration, 2.5 hours = 5 duration, etc.

        ArrayList<Boolean> ret = new ArrayList<Boolean>();
        ArrayList<Boolean> availableTime = getAvailableTime(date,patient,doctor);

        for (int i = startTime; i + duration <= endTime; ++i){
            Boolean temp = true;
            for (int j = 0; j < duration; ++j){
                temp = temp & availableTime.get(i+j);
            }
            ret.add(temp);
        }
        return ret;
    }

    public ArrayList<Appointment> getPatientFutureAppointmentList(int patient, Calendar currentTime){
        ArrayList<Appointment> ret = new ArrayList<Appointment>();

        for (Appointment temp : appointments) {
            if (temp.getPatient() == patient) {
                if (currentTime.compareTo(temp.getDate()) < 0) ret.add(temp);
            }
        }

        return ret;
    }

    public ArrayList<Appointment> getPatientPastAppointmentList(int patient, Calendar currentTime){
        ArrayList<Appointment> ret = new ArrayList<Appointment>();

        for (Appointment temp : appointments) {
            if (temp.getPatient() == patient) {
                if (currentTime.compareTo(temp.getDate()) >= 0) ret.add(temp);
            }
        }

        return ret;
    }


    public ArrayList<Appointment> getDoctorFutureAppointmentList(int doctor, Calendar currentTime){
        ArrayList<Appointment> ret = new ArrayList<Appointment>();

        for (Appointment temp : appointments) {
            if (temp.getDoctor() == doctor) {
                if (currentTime.compareTo(temp.getDate()) < 0) ret.add(temp);
            }
        }

        return ret;
    }

    public ArrayList<Appointment> getDoctorPastAppointmentList(int doctor, Calendar currentTime){
        ArrayList<Appointment> ret = new ArrayList<Appointment>();

        for (Appointment temp : appointments) {
            if (temp.getDoctor() == doctor) {
                if (currentTime.compareTo(temp.getDate()) >= 0) ret.add(temp);
            }
        }

        return ret;
    }

}