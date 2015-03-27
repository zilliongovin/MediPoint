package com.djzass.medipoint;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
