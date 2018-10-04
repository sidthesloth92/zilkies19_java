package io.ztech.autorate.beans;

public class User {

	private static boolean loginStatus;
	private String firstName = "", lastName = "", username = "", emailId = "", password = "", adminStatus = "";

	public String getAdminStatus() {
		return adminStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
