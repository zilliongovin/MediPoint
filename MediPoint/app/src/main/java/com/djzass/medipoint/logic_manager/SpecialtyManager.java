package com.djzass.medipoint.logic_manager;

import android.content.Context;

import com.djzass.medipoint.entity.Specialty;
import com.djzass.medipoint.logic_database.SpecialtyDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankur on 10/4/2015.
 * Specialty Manager is the logic manager for handling Specialty objects.
 *
 * @author Ankur
 * @version 1.0
 * @since 2015
 *
 * @see com.djzass.medipoint.entity.Specialty,com.djzass.medipoint.logic_database.SpecialtyDAO
 */
public class SpecialtyManager {
    /**
     * An instance of {@link SpecialtyDAO}. This is to be re-instated with context before use.
     */
    private SpecialtyDAO specialtyDao;

    /**
     * An instance of {@link AppointmentManager}. Use this to promote singleton design pattern.
     */
    private static SpecialtyManager instance = new SpecialtyManager();

    /**
     * returns SpecialtyManager instance
     */
    public static SpecialtyManager getInstance() {
        return instance;
    }

    /**
     * Re-initializes the SpecialtyDAO with the given context
     */
    private void updateSpecialtyDao(Context context){
        try {
            specialtyDao = new SpecialtyDAO(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all Specialities
     * @return List of speciality objects
     */
    public List<Specialty> getSpecialtys(Context context){
        updateSpecialtyDao(context);
        return specialtyDao.getAllSpecialties();
    }

    /**
     * Get all Speciality Names by @param specialtyID
     * @return speciality string
     */
    public String getSpecialtyNameByID(int specialtyId, Context context) {
        updateSpecialtyDao(context);
        List<Specialty> temp = specialtyDao.getSpecialtiesByID(specialtyId);
        return temp.get(0).getName();
    }

    /**
     * insert @param specialty to database with context @param context   
     * @return row no, -1 if fail
     */
    public long createSpecialty(Specialty specialty, Context context){
        updateSpecialtyDao(context);
        long ret = specialtyDao.insertSpecialty(specialty);
        return ret;
    }

    /**
     * edit @param specialty in database based on id with context @param context   
     * @return row no, -1 if fail
     */
    public long editSpecialty(Specialty specialty, Context context){
        // update specialty according to its id in database
        updateSpecialtyDao(context);
        long ret = specialtyDao.update(specialty);
        return ret;
    }

    /**
     * delete @param specialty in database based on id with context @param context   
     * @return row no, -1 if fail
     */
    public long cancelSpecialty(Specialty specialty, Context context){
        // delete specialty according to its id in database 
        long ret = specialtyDao.deleteSpecialty(specialty);
        updateSpecialtyDao(context);
        return ret;
    }
}
