package com.djzass.medipoint;

import java.util.ArrayList;

/**
 * Created by Deka on 26/3/2015.
 */
public class Doctor {
<<<<<<< HEAD
    private int DId;
=======
    private int DID;
>>>>>>> origin/master
    private String doctorId;
    private String name;
    private Specialty specialization;
    private int practiceDuration;
    //per clinic
    private ArrayList<DoctorSchedule> doctorSchedules;
    private ArrayList<Appointment> appointments;

    public Doctor(){

    }

<<<<<<< HEAD
    public Doctor(int id, String name, Specialty specialization,
                  int practiceDuration) {
        this.id = id;
=======
    public Doctor(String name, Specialty specialization,
                  int practiceDuration) {
>>>>>>> origin/master
        this.name = name;
        this.specialization = specialization;
        this.practiceDuration = practiceDuration;
        this.appointments = new ArrayList<Appointment>();
        this.doctorSchedules = new ArrayList<DoctorSchedule>();
    }



    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId() {
<<<<<<< HEAD
        this.doctorId = "D" + String.format("%07d", this.DId);
=======
        this.doctorId = "D" + String.format("%07d", this.DID);
>>>>>>> origin/master
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialty getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialty specialization) {
        this.specialization = specialization;
    }

    public int getPracticeDuration() {
        return practiceDuration;
    }

    public void setPracticeDuration(int practiceDuration) {
        this.practiceDuration = practiceDuration;
    }

    public ArrayList<DoctorSchedule> getDoctorSchedule() {
        return doctorSchedules;
    }

    public boolean addDoctorSchedule(DoctorSchedule doctorSchedule) {
        for (DoctorSchedule d: doctorSchedules){
            if(d.getDay().equalsIgnoreCase(doctorSchedule.getDay())){
                System.out.println("Day clashes");
                return false;
            }
        }
        return doctorSchedules.add(doctorSchedule);
    }

    public void printDoctorSchedule(){
        if (doctorSchedules.isEmpty())
            System.out.println("No practice schedule");
        else{
            for (DoctorSchedule ds: doctorSchedules){
                System.out.println(ds);
            }
        }
    }

<<<<<<< HEAD
    public int getDId() {
        return DId;
    }

    public void setDId(int DId) {
        this.DId = DId;
=======
    public int getDID() {
        return this.DID;
    }

    public void setId(int DID) {
        this.DID = DID;
>>>>>>> origin/master
    }

    public String toString(){
        return 	"=== Printing Doctor Info ==="+"\n"+
<<<<<<< HEAD
                "ID: " + this.id + "\n" +
=======
                "ID: " + this.DID + "\n" +
>>>>>>> origin/master
                "Name: " + this.name + "\n" +
                "Practice Duration: " + this.practiceDuration+ "\n" +
                "Specialization: " + this.specialization.getName() + "\n"
                ;
    }
}
