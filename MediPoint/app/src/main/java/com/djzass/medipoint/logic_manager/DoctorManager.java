package com.djzass.medipoint.logic_manager;

import android.content.Context;

import com.djzass.medipoint.entity.Doctor;
import com.djzass.medipoint.logic_database.DoctorDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 10/4/2015.
 */
public class DoctorManager {
    /**
     * An instance of {@link DoctorDAO}. This is to be re-instated with context before use.
     */
    private DoctorDAO doctorDao;

    /**
     * An instance of {@link DoctorManager}. Use this to promote singleton design pattern.
     */
    private static DoctorManager instance = new DoctorManager();

    /**
     * returns DoctorManager instance
     */
    public static DoctorManager getInstance() {
        return instance;
    }
    /**
     * Re-initializes the DoctorDAO with the given context
     */
    private void updateDoctorDao(Context context){
        try {
            doctorDao = new DoctorDAO(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all doctor
     * @return List<Doctor>
     */
    public List<Doctor> getDoctors(Context context){
        updateDoctorDao(context);
        return doctorDao.getAllDoctors();
    }

    /**
     * Gets doctor with id @param doctorid
     * @return List<Doctor>
     */
    public List<Doctor> getDoctorById(int doctorId, Context context) {
        updateDoctorDao(context);
        return doctorDao.getDoctorById(doctorId);
    }

    /**
     * Gets doctor with specialization id @param specializationid
     * @return List<Doctor>
     */
    public List<Doctor> getDoctorBySpecialization(int specializationId, Context context) {
        updateDoctorDao(context);
        return doctorDao.getDoctorBySpecialization(specializationId);
    }

    public List<Doctor> getDoctorsByClinicAndSpecialization(int specializationId,int clinicId, Context context) {
        updateDoctorDao(context);
        return doctorDao.getDoctorsByClinicAndSpecialization(specializationId,clinicId);
    }

    /**
     * insert @param doctor to database with context @param context   
     * @return row no, -1 if fail
     */
    public long createDoctor(Doctor doctor, Context context){
        updateDoctorDao(context);
        long ret = doctorDao.insertDoctor(doctor);
        return ret;
    }

    /**
     * edit @param doctor in database based on id with context @param context   
     * @return row no, -1 if fail
     */
    public long editDoctor(Doctor doctor, Context context){
        // update doctor according to its id in database
        updateDoctorDao(context);
        long ret = doctorDao.update(doctor);
        return ret;
    }

    /**
     * delete @param doctor in database based on id with context @param context   
     * @return row no, -1 if fail
     */
    public long cancelDoctor(Doctor doctor, Context context){
        // delete doctor according to its id in database 
        long ret = doctorDao.deleteDoctor(doctor);
        updateDoctorDao(context);
        return ret;
    }
}
