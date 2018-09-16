package com.zilker.services;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.zilker.beans.LoggedInUserData;
import com.zilker.delegates.LoginDelegator;

public class LoginServices {
	private final Logger LOGGER = Logger.getLogger(LoginServices.class.getName());
	LoginDelegator loginDelegator=new LoginDelegator();
	public LoggedInUserData isValidUser(long registrationNumber, String password) {
		try {
			return loginDelegator.isValidUser(registrationNumber, password) ;
		} catch (SQLException e) {
			LOGGER.info("Sql Exception Occured");
		}
		return null;
	}
}
