package com.zilker.bean;

public class LoginData {

	private int user_id;
	
	private String user_name;
	
	private String pass_word;
	
	private String e_mail;
	
	private String phone_no;
	
	private int role_id;
	
	private String role_name;	
	
	private String Date;
	
	private String time;

	public int getUserId() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getRoleId() {
		return role_id;
	}
	
	public void setRole_id(int role_id) {
		this.role_id=role_id;
	}
	
	public String getRoleName() {
		return role_name;
	}
	
	public void setRole_name(String role_name) {
		this.role_name=role_name;
	}

	public String getPassword() {
		return pass_word;
	}

	public void setPassword(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setEmail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
