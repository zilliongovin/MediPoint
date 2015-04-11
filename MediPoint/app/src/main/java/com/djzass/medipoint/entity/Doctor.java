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
    private int  clinicId;
    //per clinic

    public Doctor(){
    }

    public Doctor(String name, Specialty specialization,Clinic clinic,
                  int practiceDuration) {
        this.name = name;
        this.specializationId = specialization.getId();
        this.clinicId = clinic.getId();
    }

    public Doctor(String name, int specializationId, int practiceDuration, int clinicId) {
        this.name = name;
        this.specializationId = specializationId;
        this.practiceDuration = practiceDuration;
        this.clinicId = clinicId;
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

    public int getClinicId()
    {
        return this.clinicId;
    }

    public void setClinicId(int clinicId)
    {
        this.clinicId = clinicId;
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

    public Doctor(Parcel in){
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel desc, int flags) {
        desc.writeInt(this.DoctorId);
        desc.writeInt(this.specializationId);
        desc.writeInt(this.practiceDuration);
        desc.writeString(this.name);
    }

    public static final Parcelable.Creator<Doctor> CREATOR
            = new Parcelable.Creator<Doctor>() {
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.DoctorId = in.readInt();
        this.specializationId = in.readInt();
        this.practiceDuration = in.readInt();
        this.name = in.readString();
    }
}
