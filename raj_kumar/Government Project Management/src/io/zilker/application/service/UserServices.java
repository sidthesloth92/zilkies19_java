package io.zilker.application.service;

import io.zilker.application.beans.User;
import io.zilker.application.delegate.UserDelegate;

public class UserServices {
	UserDelegate userDelegate = new UserDelegate();
	public void userCreationService(User user) {
		userDelegate.userCreationService(user);
	}
	public boolean isUserPresent(String username) {
		return userDelegate.isUserPresent(username);
	}
	public boolean userLogin(String username, String password) {
		return userDelegate.userLogin(username, password);
	}
	public void addComment(int userID, int projectID, String comment) {
		userDelegate.addComment(userID, projectID, comment);
	}
}
