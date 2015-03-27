package com.djzass.medipoint;

import java.util.ArrayList;

/**
 * Created by Deka on 26/3/2015.
 */
public class Doctor {
    private int id;
    private String name;
    private Specialty specialization;
    private int practiceDuration;
    //per clinic
    private ArrayList<DoctorSchedule> doctorSchedules;
    private ArrayList<Appointment> appointments;

    public Doctor(int id, String name, Specialty specialization,
                  int practiceDuration) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.practiceDuration = practiceDuration;
        this.appointments = new ArrayList<Appointment>();
        this.doctorSchedules = new ArrayList<DoctorSchedule>();
    }

    public String getId() {
        return id;
    }

    public void setId(String doctorId) {
        this.id = doctorId;
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

    public String toString(){
        return 	"=== Printing Doctor Info ==="+"\n"+
                "ID: " + this.id + "\n" +
                "Name: " + this.name + "\n" +
                "Practice Duration: " + this.practiceDuration+ "\n" +
                "Specialization: " + this.specialization.getName() + "\n"
                ;
    }
}
