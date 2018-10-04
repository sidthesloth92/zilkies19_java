package io.zilker.fantasy.bean;

public class Message {
	private String message, insertedTime, userName;

	// setter for message
	public void setMessage(String userName, String message, String insertedTime) {
		this.message = message;
		this.insertedTime = insertedTime;
		this.userName = userName;
	}

	// get message
	public String getMessage() {
		return message;
	}

	public String getUserName() {
		return userName;
	}

	public String getInsertedTime() {
		return insertedTime;
	}

}
