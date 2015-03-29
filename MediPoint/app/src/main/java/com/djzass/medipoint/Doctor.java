package com.djzass.medipoint;

import java.util.ArrayList;

/**
 * Created by Deka on 26/3/2015.
 */
public class Doctor {
    private int DoctorId;
    private String name;
    private int specializationId;
    private int practiceDuration;
    //per clinic

    public Doctor(){
    }

    public Doctor(String name, Specialty specialization,
                  int practiceDuration) {
        this.name = name;
        this.specializationId = specialization.getId();
    }

    public Doctor(String name, int specializationId, int practiceDuration) {
        this.name = name;
        this.specializationId = specializationId;
        this.practiceDuration = practiceDuration;
    }


    public int getDoctorId() {
        return this.DoctorId;
    }

    public void setDoctorId(int doctorId) {
        this.DoctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpecializationId() {
        return this.specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public int getPracticeDuration() {
        return practiceDuration;
    }

    public void setPracticeDuration(int practiceDuration) {
        this.practiceDuration = practiceDuration;
    }
/*
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

    public String print(){
        String temp = "";
        temp+= DoctorId + " ";
        temp+= name + " ";
        temp+= specializationId + " ";
        temp+= practiceDuration + " ";
        return temp;
    }

*/
    public String print(){
        return 	"=== Printing Doctor Info ==="+"\n"+
                "ID: " + this.DoctorId + "\n" +
                "Name: " + this.name + "\n" +
                "Practice Duration: " + this.practiceDuration+ "\n" +
                "Specialization: " + this.specializationId + "\n"
                ;
    }
}
