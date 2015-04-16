package com.djzass.medipoint.logic_manager;

import android.content.Context;

import com.djzass.medipoint.entity.Patient;
import com.djzass.medipoint.logic_database.PatientDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Joshua on 10/4/2015.
 */
public class PatientManager {

    /**
     * An instance of {@link com.djzass.medipoint.logic_database.PatientDAO}. This is to be re-instated with context before use.
     */
    private PatientDAO patientDao;

    /**
     * An instance of {@link com.djzass.medipoint.logic_manager.PatientManager}. Use this to promote singleton design pattern.
     */
    private static PatientManager instance = new PatientManager();

    /**
     * returns PatientManager instance
     */
    public static PatientManager getInstance() {
        return instance;
    }
    /**
     * Re-initializes the PatientDAO with the given context
     */
    private void updatePatientDao(Context context){
            try {
            patientDao = new PatientDAO(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Patient> getPatients(Context context){
        updatePatientDao(context);
        return patientDao.getAllPatients();
    }

    public List<Patient> getPatientsByID(int patientid,Context context) {
        updatePatientDao(context);
        return patientDao.getPatientById(patientid);
    }

    /**
     * insert @param patient to database with context @param context   
     * @return row no, -1 if fail
     */
    public long createPatient(Patient patient, Context context){
        updatePatientDao(context);
        long ret = patientDao.insertPatient(patient);
        return ret;
    }

    /**
     * edit @param patient in database based on id with context @param context   
     * @return row no, -1 if fail
     */
    public long editPatient(Patient patient, Context context){
        // update patient according to its id in database
        updatePatientDao(context);
        long ret = patientDao.update(patient);
        return ret;
    }

    /**
     * delete @param patient in database based on id with context @param context   
     * @return row no, -1 if fail
     */
    public long cancelPatient(Patient patient, Context context){
        // delete patient according to its id in database 
        long ret = patientDao.deletePatient(patient);
        updatePatientDao(context);
        return ret;
    }
}
