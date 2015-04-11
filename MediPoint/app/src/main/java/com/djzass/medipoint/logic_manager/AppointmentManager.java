package com.djzass.medipoint.logic_manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.djzass.medipoint.AppointmentListFragment;
import com.djzass.medipoint.DbContract;
import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.entity.DoctorSchedule;
import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.logic_database.AppointmentDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AppointmentManager {
    /**
     * An instance of {@link AppointmentDAO}. This is to be re-instated with context before use.
     */
    private AppointmentDAO appointmentDao;

    /**
     * An arraylist of {@link Appointment} for use
     */
    public List<Appointment> appointments;

    /**
     * An instance of {@link AppointmentManager}. Use this to promote singleton design pattern.
     */
    private static AppointmentManager instance = new AppointmentManager();

    /**
     * returns AppointmentManager instance
     */
    public static AppointmentManager getInstance(){
        return instance;
    }

    /**
     * Re-initializes the AppointmentDAO with the given context
     */
    private void updateAppointmentDao(Context context){
        try {
            appointmentDao = new AppointmentDAO(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> getAppointments(Context context){
        updateAppointmentDao(context);
        return appointmentDao.getAllAppointments();
    }

    public List<Boolean> getAvailableTime(Calendar date, int patient, int doctor, int clinic, Context context){
        //returns array of boolean denoting whether or not each timeframe is free
        updateAppointmentDao(context);
        List<Boolean> ret = new ArrayList<Boolean>();
        appointments = appointmentDao.getAllAppointments();
        for (int i=0; i<48; ++i){
            ret.add(true);
            //ret.add(false); //use this once docsched is done
        }

        List<DoctorSchedule> sched = new ArrayList<DoctorSchedule>(); //DoctorScheduleDAO.getDoctorSchedulesByDoctorClinicID(doctor, clinic)

        for (DoctorSchedule temp : sched){
            for (int i=temp.getTimeframe().getStartTime(); i<=temp.getTimeframe().getEndTime(); ++i){
                ret.set(i,true);
            }
        }

        for (Appointment temp : appointments) {
            Log.d("Date", temp.getDate().toString() + " " + date.toString());
            Log.d("DateYr", "" + temp.getDate().get(Calendar.YEAR) + " " + date.get(Calendar.YEAR));
            Log.d("DateMth", "" + temp.getDate().get(Calendar.MONTH) + " " + date.get(Calendar.MONTH));
            Log.d("DateDat", "" + temp.getDate().get(Calendar.DATE) + " " + date.get(Calendar.DATE));
            if ( temp.getDate().get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                 temp.getDate().get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
                 temp.getDate().get(Calendar.DATE) == date.get(Calendar.DATE) &&
                (temp.getPatientId() == patient || temp.getDoctorId() == doctor)) {
                for (int i=temp.getTimeframe().getStartTime(); i<=temp.getTimeframe().getEndTime(); ++i){
                    ret.set(i,false);
                }
            }
        }

        return ret;
    }

    public List<Boolean> getTimeTable(Calendar date, int patient, int doctor, int clinic, int startTime, int endTime, int duration, Context context){
        //returns array of boolean denoting the availability of timeslots
        //starting from Timeframe [starttime] (default = opening time) until [endTime] (closing time, last active time)
        //duration is in terms of 30 mins. 1 hour = 2 duration, 2.5 hours = 5 duration, etc.

        updateAppointmentDao(context);
        List<Boolean> ret = new ArrayList<Boolean>();
        List<Boolean> availableTime = getAvailableTime(date,patient,doctor,clinic,context);

        for (int i = startTime; i + duration <= endTime; ++i){
            Boolean temp = true;
            for (int j = 0; j < duration; ++j){
                temp = temp & availableTime.get(i+j);
            }
            ret.add(temp);
        }
        return ret;
    }

    public List<Timeframe> getAvailableTimeSlot(Calendar date, int patient, int doctor, int clinic, int startTime, int endTime, int duration, Context context){
        updateAppointmentDao(context);

        Log.d("Date", date.toString());

        ArrayList<Timeframe> availableTimeSlot = new ArrayList<Timeframe>();
        List<Boolean> availableTime = getTimeTable(date, patient, doctor, clinic, startTime, endTime, duration, context);

        for (int i = startTime; i + duration <= endTime; ++i){
           if (availableTime.get(i - startTime)){
                Timeframe slot = new Timeframe(i, i+duration);
                availableTimeSlot.add(slot);
           }
        }

        return availableTimeSlot;
    }

    public List<Appointment> getPatientFutureAppointmentList(int patient, Calendar currentTime, Context context){
        updateAppointmentDao(context);
        List<Appointment> ret = new ArrayList<Appointment>();

        appointments = appointmentDao.getAllAppointments();
        for (Appointment temp : appointments) {
            if (temp.getPatientId() == patient) {
                if (currentTime.compareTo(temp.getDate()) < 0) ret.add(temp);
            }
        }

        return ret;
    }

    public List<Appointment> getPatientPastAppointmentList(int patient, Calendar currentTime, Context context){
        updateAppointmentDao(context);
        List<Appointment> ret = new ArrayList<Appointment>();

        appointments = appointmentDao.getAllAppointments();
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


    public List<Appointment> getDoctorFutureAppointmentList(int doctor, Calendar currentTime, Context context){
        updateAppointmentDao(context);
        List<Appointment> ret = new ArrayList<Appointment>();

        appointments = appointmentDao.getAllAppointments();
        for (Appointment temp : appointments) {
            if (temp.getDoctorId() == doctor) {
                if (currentTime.compareTo(temp.getDate()) < 0) ret.add(temp);
            }
        }

        return ret;
    }

    public List<Appointment> getDoctorPastAppointmentList(int doctor, Calendar currentTime, Context context){
        updateAppointmentDao(context);
        List<Appointment> ret = new ArrayList<Appointment>();

        appointments = appointmentDao.getAllAppointments();
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
    public long createAppointment(Appointment app, Context context){
        //insert to database
        // update arraylist of appointment appointments = getAppointmentFromDatabase()
        updateAppointmentDao(context);
        long ret = appointmentDao.insertAppointment(app);
        appointments = getAppointments(context);
        return ret;
    }

    public long editAppointment(Appointment app, Context context){
        // update appointment according to its id in database
        // update arraylist of appointment appointments = getAppointmentFromDatabase()
        updateAppointmentDao(context);
        long ret = appointmentDao.update(app);
        appointments = getAppointments(context);
        return ret;
    }

    public long cancelAppointment(Appointment app, Context context){
        // delete appointment according to its id in database
        // update arraylist of appointment appointments = getAppointmentFromDatabase()
        updateAppointmentDao(context);
        long ret = appointmentDao.deleteAppointment(app);
        appointments = getAppointments(context);
        return ret;
    }

   public Appointment getAppointmentByID(int id, Context context){
        updateAppointmentDao(context);
        return appointmentDao.getAppointmentsByID(id).get(0);
    }

    public String getSpecialtyNameByAppointment(Appointment appointment, Context context){
        updateAppointmentDao(context);
        int specialtyID = appointment.getSpecialtyId();
        String specialtyName = appointmentDao.getStringFromID(DbContract.SpecialtyEntry.TABLE_NAME,DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_NAME,DbContract.SpecialtyEntry.COLUMN_NAME_SPECIALTY_ID,specialtyID);
        return specialtyName;
    }

    public String getServiceNameByAppointment(Appointment appointment, Context context){
        updateAppointmentDao(context);
        int serviceID = appointment.getServiceId();
        String serviceName = appointmentDao.getStringFromID(DbContract.ServiceEntry.TABLE_NAME,DbContract.ServiceEntry.COLUMN_NAME_SERVICE_NAME,DbContract.ServiceEntry.COLUMN_NAME_SERVICE_ID,serviceID);
        return serviceName;
    }

    public String getDoctorNameByAppointment(Appointment appointment, Context context){
        updateAppointmentDao(context);
        int doctorID = appointment.getDoctorId();
        String doctorName = appointmentDao.getStringFromID(DbContract.DoctorEntry.TABLE_NAME,DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_NAME,DbContract.DoctorEntry.COLUMN_NAME_DOCTOR_ID,doctorID);
        return doctorName;
    }

    public String getClinicNameByAppointment(Appointment appointment, Context context){
        updateAppointmentDao(context);
        int clinicID = appointment.getClinicId();
        String clinicName = appointmentDao.getStringFromID(DbContract.ClinicEntry.TABLE_NAME,DbContract.ClinicEntry.COLUMN_NAME_CLINIC_NAME,DbContract.ClinicEntry.COLUMN_NAME_CLINIC_ID,clinicID);
        return clinicName;
    }

}