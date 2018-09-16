package io.zilker.appstore.beans;

import java.io.Serializable;

public class Tester implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int testerID;
	private String userName, password;

	public int getTesterID() {
		return testerID;
	}

	public void setTesterID(int testerID) {
		this.testerID = testerID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
