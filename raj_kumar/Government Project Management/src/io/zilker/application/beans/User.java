package io.zilker.application.beans;

public class User {
	public UserLocation location;
	public UserName user;
	public UserPassword password;
	public User() {
		location = new UserLocation();
		user = new UserName();
		password = new UserPassword();
	}
}
