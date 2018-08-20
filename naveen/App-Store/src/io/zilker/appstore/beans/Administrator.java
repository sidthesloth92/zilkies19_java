package io.zilker.appstore.beans;

import java.io.Serializable;

public class Administrator implements Serializable {

	private static final long serialVersionUID = 1L;
	private int adminID;
	private String userName, password;
	
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	
	public int getAdminID() {
		return this.adminID;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
}
