package com.zilker.bean;

import java.util.ArrayList;

public class Availability {

	int availability_id;
	
	int hall_id;
	
	String hall_name;
	
	int hall_Size;
	
	ArrayList<String> bookedDates = new ArrayList<String>();
	
	ArrayList<String> bookedTimes = new ArrayList<String>();
	

	public ArrayList<String> getBookedDates() {
		return bookedDates;
	}

	public void setBookedDates(ArrayList<String> bookedDates) {
		this.bookedDates = bookedDates;
	}

	public ArrayList<String> getBookedTimes() {
		return bookedTimes;
	}

	public void setBookedTimes(ArrayList<String> bookedTimes) {
		this.bookedTimes = bookedTimes;
	}

	public int getAvailabilityId() {
		return availability_id;
	}

	public void setAvailability_id(int availability_id) {
		this.availability_id = availability_id;
	}

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
		return hall_Size;
	}

	public void setHallSize(int hall_Size) {
		this.hall_Size = hall_Size;
	}

	
	
	
}
