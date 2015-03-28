package com.djzass.medipoint;

import java.lang.String;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.lang.Comparable;
//import java.util.Collection;
//import java.util.Collections;

public class Country {
	private int countryId;
	private String name;
	//private ArrayList<Region> regions;

    public Country(){}
	public Country(String name){
		this.name = name;
		//this.regions = new ArrayList<Region>();
	}

	public int getId() {
		return countryId;
	}
	
	public void setId(int countryId) {
		this.countryId = countryId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	public boolean addRegion(Region region){
		//if (regions.contains(region)){
		//	System.out.println("Region already exists!");
		//	return false;
		//}
		for (Region r: regions){
			if(r.getName().equalsIgnoreCase(r.getName())){
				System.out.println("Region already exists!");
				return false;
			}
		}
		return regions.add(region);
	}
	
	public Region findRegion(Region region){
		for (Region r: regions){
			if(r.getName().toLowerCase().equals(region.getName().toLowerCase()))
				return r;
		}
		return null;
	}

	public boolean removeRegion(Region region){
		for (Region r: regions){
			if(r.getName().toLowerCase().equals(region.getName().toLowerCase()))
				return regions.remove(r);
		}
		return false;
	}
	
	public ArrayList<Region> getRegions(){
		return regions;
	}*/
}
