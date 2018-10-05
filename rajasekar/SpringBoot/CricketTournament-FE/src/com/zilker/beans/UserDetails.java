package com.zilker.beans;

public class UserDetails {
	String firstName, lastName, password, email, mobile,role;
	int age;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getfirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getlastName() {
		return this.lastName;
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
	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}
}
