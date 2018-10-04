package com.zilker.beans;

public class Login {
	String email, password;
	boolean loginstatus;

	public boolean getLoginstatus() {
		return loginstatus;
	}

	public void setLoginstatus(boolean loginstatus) {
		this.loginstatus = loginstatus;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
}
