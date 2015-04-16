package com.djzass.medipoint.boundary_ui;

import com.djzass.medipoint.entity.Patient;
import com.djzass.medipoint.entity.Service;

import java.util.ArrayList;

/**
 * Created by Deka on 26/3/2015.
 */
public class MedicalPractice {
    private String name;
    private ArrayList<String> specialties;
    private ArrayList<Service> services;

    public MedicalPractice(){
        this.name = "DJ ZASS Health Care Center";
        this.specialties = new ArrayList<String>();
        this.services = new ArrayList<Service>();

        String sp1 = new String();
        Service sv1 = new Service();
        Service sv2 = new Service();
        Service sv3 = new Service();

        String sp2 = new String();
        Service sv4 = new Service();
        Service sv5 = new Service();
        Service sv6 = new Service();

        String sp3 = new String();
        Service sv7 = new Service();
        Service sv8 = new Service();
        Service sv9 = new Service();

        Patient p1 = new Patient();
        Patient p2 = new Patient();
        Patient p3 = new Patient();

    }
}
