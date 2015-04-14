package com.djzass.medipoint.logic_database;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.djzass.medipoint.R;
import com.djzass.medipoint.entity.Specialty;

import java.sql.SQLException;
import java.util.ArrayList;


public class DbTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        SpecialtyDAO specialtyDAO = null;

        try {
            specialtyDAO = new SpecialtyDAO(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Specialty spec1 = new Specialty("ENT");
        Specialty spec2 = new Specialty("Dental");
        Specialty spec3 = new Specialty("Woman's Health Service");

        specialtyDAO.insertSpecialty(spec1);
        specialtyDAO.insertSpecialty(spec2);
        specialtyDAO.insertSpecialty(spec3);
        ArrayList<Specialty> specialties = (ArrayList<Specialty>) specialtyDAO.getAllSpecialties();
        for (int i =0; i<specialties.size(); i++){
            String text = "id: " + specialties.get(i).getId() + "name: " + specialties.get(i).getName();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_db_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
