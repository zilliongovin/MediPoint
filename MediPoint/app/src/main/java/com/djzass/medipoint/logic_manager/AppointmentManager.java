package com.djzass.medipoint.logic_manager;

import android.content.Context;

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.logic_database.AppointmentDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AppointmentManager {
    private AppointmentDAO appointmentDao;
    List<Appointment> appointments;

    public AppointmentManager(Context context) throws SQLException {
        appointmentDao = new AppointmentDAO(context);
        appointments = appointmentDao.getAllAppointments();
    }

    public List<Boolean> getAvailableTime(Calendar date, int patient, int doctor){
        //returns array of boolean denoting whether or not each timeframe is free
        List<Boolean> ret = new ArrayList<Boolean>();
        //List<Appointment> appointments = appointmentDao.getAllAppointments();
//        List<Appointment> appointments = Container.GlobalAppointmentDAO.getAllAppointments();
        for (int i=0; i<48; ++i){
            ret.add(true);
        }
        for (Appointment temp : appointments) {
            if (temp.getDate().compareTo(date)==0 && (temp.getPatientId() == patient || temp.getDoctorId() == doctor)) {
                for (int i=temp.getTimeframe().getStartTime(); i<=temp.getTimeframe().getEndTime(); ++i){
                    ret.set(i,false);
                }
            }
        }

        return ret;
    }

    public List<Boolean> getTimeTable(Calendar date, int patient, int doctor, int startTime, int endTime, int duration){
        //returns array of boolean denoting the availability of timeslots
        //starting from Timeframe [starttime] (default = opening time) until [endTime] (closing time, last active time)
        //duration is in terms of 30 mins. 1 hour = 2 duration, 2.5 hours = 5 duration, etc.

        List<Boolean> ret = new ArrayList<Boolean>();
        List<Boolean> availableTime = getAvailableTime(date,patient,doctor);

        for (int i = startTime; i + duration <= endTime; ++i){
            Boolean temp = true;
            for (int j = 0; j < duration; ++j){
                temp = temp & availableTime.get(i+j);
            }
            ret.add(temp);
        }
        return ret;
    }

    public List<String> getTimeSlotString(Calendar date, int patient, int doctor, int startTime, int endTime, int duration){
        List<String> availableTimeSlotString = new ArrayList<String>();
        List<Boolean> availableTimeSlot = getTimeTable(date, patient, doctor, startTime, endTime, duration);
        for (int i = startTime; i + duration <= endTime; ++i){
            if (availableTimeSlot.get(i)) {
                Timeframe slot = new Timeframe(i, i + duration);
                availableTimeSlotString.add(slot.getTimeLine());
            }
        }
        if (availableTimeSlotString.size()==0)
            availableTimeSlotString.add(new String("No slot available on this date."));
        return availableTimeSlotString;
    }

    public List<Appointment> getPatientFutureAppointmentList(int patient, Calendar currentTime){
        List<Appointment> ret = new ArrayList<Appointment>();

        //List<Appointment> appointments = Container.GlobalAppointmentDAO.getAllAppointments();
        for (Appointment temp : appointments) {
            if (temp.getPatientId() == patient) {
                if (currentTime.compareTo(temp.getDate()) < 0) ret.add(temp);
            }
        }

        return ret;
    }

    public List<Appointment> getPatientPastAppointmentList(int patient, Calendar currentTime){
        List<Appointment> ret = new ArrayList<Appointment>();

//        List<Appointment> appointments = Container.GlobalAppointmentDAO.getAllAppointments();
        for (Appointment temp : appointments) {
            if (temp.getPatientId() == patient) {
                if (currentTime.compareTo(temp.getDate()) >= 0) ret.add(temp);
            }
        }

        return ret;
    }

    public List<Appointment> sortByDate(List<Appointment> inp){
        List<Appointment> ret = inp;
        Collections.sort(ret,Appointment.AppSortByDate);
        return ret;
    }

    public List<Appointment> sortBySpecialtyID(List<Appointment> inp){
        List<Appointment> ret = inp;
        Collections.sort(ret,Appointment.AppSortBySpecialty);
        return ret;
    }


    public List<Appointment> getDoctorFutureAppointmentList(int doctor, Calendar currentTime){
        List<Appointment> ret = new ArrayList<Appointment>();

  //      List<Appointment> appointments = Container.GlobalAppointmentDAO.getAllAppointments();
        for (Appointment temp : appointments) {
            if (temp.getDoctorId() == doctor) {
                if (currentTime.compareTo(temp.getDate()) < 0) ret.add(temp);
            }
        }

        return ret;
    }

    public List<Appointment> getDoctorPastAppointmentList(int doctor, Calendar currentTime){
        List<Appointment> ret = new ArrayList<Appointment>();

       // List<Appointment> appointments = Container.GlobalAppointmentDAO.getAllAppointments();
        for (Appointment temp : appointments) {
            if (temp.getDoctorId() == doctor) {
                if (currentTime.compareTo(temp.getDate()) >= 0) ret.add(temp);
            }
        }

        return ret;
    }

    public String returnStatus(Appointment appointment, Calendar currentTime){
        Calendar startTime = appointment.getDate();
        startTime.set(Calendar.HOUR, Timeframe.getHour(appointment.getTimeframe().getStartTime()));
        startTime.set(Calendar.MINUTE, Timeframe.getMinute(appointment.getTimeframe().getStartTime()));


        if (currentTime.compareTo(startTime) < 0) {
            //current time is before starttime
            return "Upcoming";
        } else {
            //current time is after starttime
            Calendar endTime = appointment.getDate();
            endTime.set(Calendar.HOUR, Timeframe.getHour(appointment.getTimeframe().getEndTime()));
            endTime.set(Calendar.MINUTE, Timeframe.getMinute(appointment.getTimeframe().getEndTime()));
            if (currentTime.compareTo(endTime) < 0) return "Ongoing";
            else return "Finished";
        }
    }
}