package io.zilker.application.service;

import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.User;
import io.zilker.application.delegate.UserDelegate;
import io.zilker.application.logsession.UserSession;

public class UserServices {
	UserDelegate userDelegate = new UserDelegate();

	public void userCreationService(User user) throws Exception {
		userDelegate.userCreationService(user);
	}

	public boolean isUserPresent(String username) {
		return userDelegate.isUserPresent(username);
	}

	public UserSession userLogin(String username, String password) {
		return userDelegate.userLogin(username, password);
	}

	public void addComment(int userID, int projectID, String comment) throws Exception {
		userDelegate.addComment(userID, projectID, comment);
	}

	public String getContractor(int contrID) {
		return userDelegate.getContractor(contrID);
	}

	public Contractor getContractorDetails(int contrID) {
		return userDelegate.getContractorDetails(contrID);
	}
}
