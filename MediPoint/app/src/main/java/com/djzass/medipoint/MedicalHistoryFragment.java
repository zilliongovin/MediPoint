package com.djzass.medipoint;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.djzass.medipoint.entity.Patient;
import com.djzass.medipoint.logic_database.AccountDAO;
import com.djzass.medipoint.logic_database.PatientDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Zillion Govin on 1/4/2015.
 */
public class MedicalHistoryFragment extends Fragment {

    long patientId;
    private String name;
    private String patientGender;

    private int patientAge;
    private String medicalHistory;
    private String allergy;
    private String ongoingTreatment;
    private String ongoingMedication;

    AccountDAO accountDAO;
    PatientDAO patientDAO;
    List<Patient> patient = new ArrayList<>();

    public static MedicalHistoryFragment newInstance() {
        MedicalHistoryFragment fragment = new MedicalHistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //instantiate DAO
        try {
            accountDAO = new AccountDAO(this.getActivity());
            patientDAO = new PatientDAO(this.getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get patient id
        try {
            SessionManager sessionManager = new SessionManager(this.getActivity());
            this.patientId = sessionManager.getAccountId();
            patient = patientDAO.getPatientById(patientId);

            //get name and gender from account table
            Cursor cursor = accountDAO.getAccountById(patientId);
            if(cursor!=null && cursor.moveToFirst()){
                this.name = cursor.getString(1);
                this.patientGender = cursor.getString(7);
            }


            //get medical history and age in patient table
            this.patientAge = patient.get(0).getAge();
            Toast.makeText(this.getActivity(), ""+patientAge, Toast.LENGTH_SHORT).show();
            this.medicalHistory = patient.get(0).getMedicalHistory();
            this.allergy = patient.get(0).getAllergy();
            this.ongoingTreatment = patient.get(0).getListOfTreatments();
            this.ongoingMedication = patient.get(0).getListOfMedications();

            Calendar DOB = patient.get(0).getDob();
            Toast.makeText(this.getActivity(),"DOB : " + DOB.getTime(), Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_view_medical_history, null);

        //get patientName textview
        TextView patientName = (TextView) view.findViewById(R.id.patientName);
        //set the patient name
        if(this.name == null || this.name.trim().equals("")){
            patientName.setText("Error, name is not specified.");
        }
        else {
            patientName.setText(this.name);
        }

        //get patientAge textview
        TextView patientAge = (TextView) view.findViewById(R.id.patientAge);
        //set the patient age
        patientAge.setText(""+this.patientAge);

        //get patientGender textview
        TextView patientGender = (TextView) view.findViewById(R.id.patientGender);
        //set the patient gender
        if(this.patientGender == null || this.patientGender.trim().equals("")){
            patientGender.setText("Error, gender is not specified.");
        }
        else {
            patientGender.setText(this.patientGender);
        }

        //get medicalHistory textview
        TextView medicalHistory = (TextView) view.findViewById(R.id.medicalHistory);
        //set medical history
        if(this.medicalHistory == null || this.medicalHistory.trim().equals("")){
            medicalHistory.setText("No medical history.\n");
        }
        else {
            medicalHistory.setText(this.medicalHistory);
        }

        //get allergy textview
        TextView allergy = (TextView) view.findViewById(R.id.allergy);
        //set allergy
        if(this.allergy == null || this.allergy.trim().equals("")){
            allergy.setText("No allergy.\n");
        }
        else {
            allergy.setText(this.allergy);
        }

        //get ongoingTreatment textview
        TextView ongoingTreatment = (TextView) view.findViewById(R.id.ongoingTreatment);
        //set ongoing Treatment
        if(this.ongoingTreatment == null || this.ongoingTreatment.trim().equals("")){
            ongoingTreatment.setText("No ongoing treatment.");
        }
        else {
            ongoingTreatment.setText(this.ongoingTreatment);
        }

        //get ongoingMedication textview
        TextView ongoingMedication = (TextView) view.findViewById(R.id.ongoingMedication);
        //set ongoing medication
        if(this.ongoingMedication == null || this.ongoingMedication.trim().equals("")){
            ongoingMedication.setText("No ongoing medication.");
        }
        else {
            ongoingMedication.setText(this.ongoingMedication);
        }


        Button editHistory = (Button) view.findViewById(R.id.editHistory);
        editHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onEdit();
            }
        });

        return view;
    }


    public void onEdit(){
        Intent intent = new Intent(this.getActivity(), EditHistoryActivity.class);
        startActivity(intent);
    }
}
