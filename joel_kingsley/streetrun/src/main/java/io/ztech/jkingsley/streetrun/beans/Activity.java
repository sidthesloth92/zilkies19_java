package io.ztech.jkingsley.streetrun.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class Activity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6012198979557217181L;
	
	BigInteger activity_id;
	BigInteger user_id;
	Float distance_traveled;
	Time time_taken;
	Time avg_pace;
	Integer calories;
	Double start_lat;
	Double start_long;
	Double end_lat;
	Double end_long;
	Date activity_date;
	
	public BigInteger getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(BigInteger activity_id) {
		this.activity_id = activity_id;
	}
	public BigInteger getUser_id() {
		return user_id;
	}
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	public Float getDistance_traveled() {
		return distance_traveled;
	}
	public void setDistance_traveled(Float distance_traveled) {
		this.distance_traveled = distance_traveled;
	}
	public Time getTime_taken() {
		return time_taken;
	}
	public void setTime_taken(Time time_taken) {
		this.time_taken = time_taken;
	}
	public Time getAvg_pace() {
		return avg_pace;
	}
	public void setAvg_pace(Time avg_pace) {
		this.avg_pace = avg_pace;
	}
	public Integer getCalories() {
		return calories;
	}
	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	public Double getStart_lat() {
		return start_lat;
	}
	public void setStart_lat(Double start_lat) {
		this.start_lat = start_lat;
	}
	public Double getStart_long() {
		return start_long;
	}
	public void setStart_long(Double start_long) {
		this.start_long = start_long;
	}
	public Double getEnd_lat() {
		return end_lat;
	}
	public void setEnd_lat(Double end_lat) {
		this.end_lat = end_lat;
	}
	public Double getEnd_long() {
		return end_long;
	}
	public void setEnd_long(Double end_long) {
		this.end_long = end_long;
	}
	public Date getActivity_date() {
		return activity_date;
	}
	public void setActivity_date(Date activity_date) {
		this.activity_date = activity_date;
	}
	
	
}
