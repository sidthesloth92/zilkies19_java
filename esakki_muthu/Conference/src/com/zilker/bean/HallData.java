package com.zilker.bean;

import java.util.ArrayList;

public class HallData {
	
	int hall_id;
	
	String hall_name;
	
	int hall_size;
	
	ArrayList<Integer> facility_id;
	
	ArrayList<String> facility_name;
	
	String description;

	public int getHallId() {
		return hall_id;
	}

	public void setHallId(int hall_id) {
		this.hall_id = hall_id;
	}

	public String getHallName() {
		return hall_name;
	}

	public void setHallName(String hall_name) {
		this.hall_name = hall_name;
	}

	public int getHallSize() {
		return hall_size;
	}

	public void setHallSize(int hall_size) {
		this.hall_size = hall_size;
	}

	public ArrayList<Integer> getFacility_id() {
		return facility_id;
	}

	public void setFacilityId(ArrayList<Integer> facility_id) {
		this.facility_id = facility_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<String> getFacility_name() {
		return facility_name;
	}

	public void setFacility_name(ArrayList<String> facility_name) {
		this.facility_name = facility_name;
	}

}
