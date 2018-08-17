package com.zilker.bean;

public class ConferenceData {
	
	int conference_id;
	
	int user_id;
	
	int hall_id;
	
	int hall_size;		

	int approver_id;
	
	int sno;	

	String conference_name;
	
	String hall_name;
	
	String user_name;
	
	String e_mail;
	
	String approver_name;
	
	String from_date,to_date;
	
	String from_time,to_time;
	
	String phone_no;
	
	int status = 0;	

	int found = 0;
	
	String from_hour,to_hour,from_min,to_min;

	public String getFrom_hour() {
		return from_hour;
	}

	public void setFrom_hour(String from_hour) {
		this.from_hour = from_hour;
	}

	public String getTohour() {
		return to_hour;
	}

	public void setTo_hour(String to_hour) {
		this.to_hour = to_hour;
	}

	public String getFrom_min() {
		return from_min;
	}

	public void setFrom_min(String from_min) {
		this.from_min = from_min;
	}

	public String getTomin() {
		return to_min;
	}

	public void setTo_min(String to_min) {
		this.to_min = to_min;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhoneNo(String phone_no) {
		this.phone_no = phone_no;
	}

	public int getConferenceId() {
		return conference_id;
	}

	public void setConferenceId(int conference_id) {
		this.conference_id = conference_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getHall_id() {
		return hall_id;
	}

	public void setHallId(int hall_id) {
		this.hall_id = hall_id;
	}

	public int getHall_size() {
		return hall_size;
	}

	public void setHallSize(int hall_size) {
		this.hall_size = hall_size;
	}

	public int getApprover_id() {
		return approver_id;
	}

	public void setApprover_id(int approver_id) {
		this.approver_id = approver_id;
	}

	public String getConference_name() {
		return conference_name;
	}

	public void setConferenceName(String conference_name) {
		this.conference_name = conference_name;
	}

	public String getHallName() {
		return hall_name;
	}

	public void setHallName(String hall_name) {
		this.hall_name = hall_name;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return e_mail;
	}

	public void setEmail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getApprover_name() {
		return approver_name;
	}

	public void setApprover_name(String approver_name) {
		this.approver_name = approver_name;
	}

	public String getFromDate() {
		return from_date;
	}

	public void setFromDate(String from_date) {
		this.from_date = from_date;
	}

	public String getToDate() {
		return to_date;
	}

	public void setToDate(String to_date) {
		this.to_date = to_date;
	}

	public String getFromTime() {
		return from_time;
	}

	public void setFromTime(String from_time) {
		this.from_time = from_time;
	}

	public String getToTime() {
		return to_time;
	}

	public void setToTime(String to_time) {
		this.to_time = to_time;
	}
	
	public int getFound() {
		return found;
	}

	public void setFound(int found) {
		this.found = found;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}
}
