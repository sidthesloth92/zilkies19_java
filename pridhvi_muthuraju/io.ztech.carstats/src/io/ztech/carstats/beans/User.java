package io.ztech.carstats.beans;

public class User {

	private static boolean loginStatus;
	private String userName="", password="", adminStatus="";

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public static boolean getLoginStatus() {
		return loginStatus;
	}

	public static void setLoginStatus(boolean loginStatus) {
		User.loginStatus = loginStatus;
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
