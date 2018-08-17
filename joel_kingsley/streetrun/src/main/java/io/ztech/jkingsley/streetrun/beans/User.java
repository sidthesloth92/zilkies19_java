package io.ztech.jkingsley.streetrun.beans;

import java.io.Serializable;
import java.math.BigInteger;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8763571977852888208L;
	
	BigInteger user_id;
	String first_name;
	String last_name;
	float last_known_lat;
	float last_known_long;
	
	public BigInteger getUser_id() {
		return user_id;
	}
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public float getLast_known_lat() {
		return last_known_lat;
	}
	public void setLast_known_lat(float last_known_lat) {
		this.last_known_lat = last_known_lat;
	}
	public float getLast_known_long() {
		return last_known_long;
	}
	public void setLast_known_long(float last_known_long) {
		this.last_known_long = last_known_long;
	}
	
	
}
