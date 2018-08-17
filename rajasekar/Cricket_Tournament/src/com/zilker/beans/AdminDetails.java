package com.zilker.beans;

public class AdminDetails {
	String First_name = "", Last_name = "", password = "", email = "", mobile = "";
	int age = 0;

	public void setFirstName(String First_name) {
		this.First_name = First_name;
	}

	public String getFirst_name() {
		return this.First_name;
	}

	public void setLastName(String Last_name) {
		this.Last_name = Last_name;
	}

	public String getLast_name() {
		return this.Last_name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}
}
