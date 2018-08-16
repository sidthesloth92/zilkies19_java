package io.zilker.application.service;

import io.zilker.application.beans.User;
import io.zilker.application.delegate.UserDelegate;
import io.zilker.application.logsession.UserLog;

public class UserServices {
	UserDelegate userDelegate = new UserDelegate();
	public void userCreationService(User user) {
		userDelegate.userCreationService(user);
	}
	public boolean isUserPresent(String username) {
		return userDelegate.isUserPresent(username);
	}
	public UserLog userLogin(String username, String password) {
		return userDelegate.userLogin(username, password);
	}
	public void addComment(int userID, int projectID, String comment) {
		userDelegate.addComment(userID, projectID, comment);
	}
	public String getContractor(int contrID) {
		return userDelegate.getContractor(contrID);
	}
}
