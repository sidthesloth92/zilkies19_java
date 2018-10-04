package io.zilker.fantasy.bean;

public class User {
	private String userName, email, password;
	private int userId, totalPointsObtained, userType;
	
	public User() {
		
	}

	// setters
	public void setUser(String userName, String email, String password, int type) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userType = type;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	// getter returns user Name
	public String getUserName() {
		return userName;
	}

	// getter return user id
	public int getUserId() {
		return userId;
	}

	// getter return email
	public String getEmail() {
		return email;
	}

	// getter return password
	public String getPassword() {
		return password;
	}

	// getter return user type
	public int getUserType() {
		return userType;
	}

	// getter return total points obtained
	public int getTotalPointsObtained() {
		return totalPointsObtained;
	}

}
