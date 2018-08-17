package io.zilker.fantasy.bean;

public class User {
	private String userName, email, password;
	private int userId, totalPointsObtained, type;

	// setters
	public void setUser(String userName, String email, String password, int type, int totalPointsObtained) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.type = type;
		this.totalPointsObtained = totalPointsObtained;
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
		return type;
	}

	// getter return total points obtained
	public int getTotalPointsObtained() {
		return totalPointsObtained;
	}

}
