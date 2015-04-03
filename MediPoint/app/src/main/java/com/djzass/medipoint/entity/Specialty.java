package com.djzass.medipoint.entity;

/**
 * Created by Deka on 26/3/2015.
 */
public class Specialty {
    private int id;
    private String name;

    public Specialty(){
    }
    public Specialty(String name){
        this.name = name;
    }

    public String print(){
        String temp = "";
        temp+= id + " ";
        temp+= name + " \n";
        return temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
