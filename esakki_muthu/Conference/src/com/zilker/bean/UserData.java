package com.zilker.bean;

public class UserData {

	int user_id = 0;
	
	String user_name = null;
	
	String pass_word = null;
	
	String phone_no = null;
	
	String role_name = null;	

	int login_status = 0;
	
	int role_id = 0;
	
	String current_Date = null;
	
	String current_Time = null;

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUserName(String user_name) {
		this.user_name = user_name;
	}

	public String getPass_word() {
		return pass_word;
	}

	public void setPassword(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRoleName(String role_name) {
		this.role_name = role_name;
	}

	public int getRoleId() {
		return role_id;
	}

	public void setRoleId(int role_id) {
		this.role_id = role_id;
	}

	public String getCurrent_Date() {
		return current_Date;
	}

	public void setCurrent_Date(String current_Date) {
		this.current_Date = current_Date;
	}

	public String getCurrent_Time() {
		return current_Time;
	}

	public void setCurrent_Time(String current_Time) {
		this.current_Time = current_Time;
	}
	
	public int getLoginStatus() {
		return login_status;
	}

	public void setLoginStatus(int login_status) {
		this.login_status = login_status;
	}
	
}
