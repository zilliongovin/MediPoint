package com.djzass.medipoint.logic_manager;

import android.content.Context;

import com.djzass.medipoint.DbContract;
import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.entity.DoctorSchedule;
import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.logic_database.AppointmentDAO;
import com.djzass.medipoint.logic_database.DoctorScheduleDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AppointmentManager {
    private AppointmentDAO appointmentDao;
    private DoctorScheduleDAO docschedDao;

    List<Appointment> appointments;

    public AppointmentManager(Context context) throws SQLException {
        appointmentDao = new AppointmentDAO(context);
        appointments = getAppointmentsFromDatabase();
    }

    public List<Appointment> getAppointmentsFromDatabase(){
        return appointmentDao.getAllAppointments();
    }

    public List<Boolean> getAvailableTime(Calendar date, int patient, int doctor, int clinic){
        //returns array of boolean denoting whether or not each timeframe is free
        /*try {
            appointmentDao = new AppointmentDAO(this);
            docschedDao = new DoctorScheduleDAO(this);
        } catch(SQLException sqlExcep){
            sqlExcep.getStackTrace();
        }*/
        List<Boolean> ret = new ArrayList<Boolean>();
        //List<Appointment> appointments = appointmentDao.getAllAppointments();
//        List<Appointment> appointments = Container.GlobalAppointmentDAO.getAllAppointments();
        for (int i=0; i<48; ++i){
            ret.add(false);
        }

        List<DoctorSchedule> sched = new ArrayList<DoctorSchedule>(); //DoctorScheduleDAO.getDoctorSchedulesByDoctorClinicID(doctor, clinic)

        for (DoctorSchedule temp : sched){
            for (int i=temp.getTimeframe().getStartTime(); i<=temp.getTimeframe().getEndTime(); ++i){
                ret.set(i,true);
            }
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

    public List<Boolean> getTimeTable(Calendar date, int patient, int doctor, int clinic, int startTime, int endTime, int duration){
        //returns array of boolean denoting the availability of timeslots
        //starting from Timeframe [starttime] (default = opening time) until [endTime] (closing time, last active time)
        //duration is in terms of 30 mins. 1 hour = 2 duration, 2.5 hours = 5 duration, etc.

        List<Boolean> ret = new ArrayList<Boolean>();
        List<Boolean> availableTime = getAvailableTime(date,patient,doctor,clinic);

        for (int i = startTime; i + duration <= endTime; ++i){
            Boolean temp = true;
            for (int j = 0; j < duration; ++j){
                temp = temp & availableTime.get(i+j);
            }
            ret.add(temp);
        }
        return ret;
    }
    public List<String> getAvailableTimeSlotString(Calendar date, int patient, int doctor, int clinic, int startTime, int endTime, int duration){
        ArrayList<String> availableTimeSlot = new ArrayList<String>();
        List<Boolean> availableTime = getTimeTable(date, patient, doctor, clinic, startTime, endTime, duration);

        for (int i = startTime; i + duration <= endTime; ++i){
           if (availableTime.get(i)){
                Timeframe slot = new Timeframe(i, i+duration);
                availableTimeSlot.add(slot.getTimeLine());
           }
        }

        return availableTimeSlot;
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

    public String getStatus(Appointment appointment){
        Calendar startTime = appointment.getDate();
        Calendar currentTime = Calendar.getInstance();
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

    /*joshua*/
    public long createAppointment(Appointment app, AppointmentDAO appdao){
        //insert to database
        //update arraylist of appointment
        // update arraylist of appointment appointments = getAppointmentFromDatabase()
        long ret = appdao.insertAppointment(app);
        appointments = getAppointmentsFromDatabase();
        return ret;
    }

    public long editAppointment(Appointment app, AppointmentDAO appdao){
        // get id of appointment
        // update appointment according to its id in database
        // update arraylist of appointment appointments = getAppointmentFromDatabase()
        long ret = appdao.update(app);
        appointments = getAppointmentsFromDatabase();
        return ret;
    }

    public long cancelAppointment(Appointment app, AppointmentDAO appdao){
        // get id of appointment
        // delete appointment according to its id in database
        // update arraylist of appointment appointments = getAppointmentFromDatabase()
        long ret = appdao.deleteAppointment(app);
        appointments = getAppointmentsFromDatabase();
        return ret;
    }

   public Appointment getAppointmentByID(int id){
        return appointmentDao.getAppointmentsByID(id).get(0);
    }

    public String getSpecialtyNameByAppointment(Appointment appointment){
        int specialtyID = appointment.getSpecialtyId();
        String specialtyName = appointmentDao.getStringFromID(DbContract.SpecialtyEntry.TABLE_NAME,DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME,DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID,specialtyID);
        return specialtyName;
    }

    public String getServiceNameByAppointment(Appointment appointment){
        int serviceID = appointment.getServiceId();
        String serviceName = appointmentDao.getStringFromID(DbContract.ServiceEntry.TABLE_NAME,DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME,DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID,serviceID);
        return serviceName;
    }

    public String getDoctorNameByAppointment(Appointment appointment){
        int doctorID = appointment.getDoctorId();
        String doctorName = appointmentDao.getStringFromID(DbContract.DoctorEntry.TABLE_NAME,DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME,DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID,doctorID);
        return doctorName;
    }

    public String getClinicNameByAppointment(Appointment appointment){
        int clinicID = appointment.getClinicId();
        String clinicName = appointmentDao.getStringFromID(DbContract.ClinicEntry.TABLE_NAME,DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME,DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID,clinicID);
        return clinicName;
    }

}