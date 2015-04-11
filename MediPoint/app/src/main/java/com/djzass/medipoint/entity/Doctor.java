package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Deka on 26/3/2015.
 */
public class Doctor implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parc3, int flags) {
        parc3.writeInt(this.DoctorId);
        parc3.writeInt(this.specializationId);
        parc3.writeInt(this.practiceDuration);
        parc3.writeString(this.name);
    }

    public static final Parcelable.Creator<Appointment> CREATOR
            = new Parcelable.Creator<Appointment>() {
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    public void readFromParcel(Parcel in4) {
        this.DoctorId = in4.readInt();
        this.specializationId = in4.readInt();
        this.practiceDuration = in4.readInt();
        this.name = in4.readString();
    }
}
