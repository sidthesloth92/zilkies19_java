package com.zilker.services;

import com.zilker.beans.LoggedInUserData;
import com.zilker.delegates.LoginDelegator;

public class LoginServices {
	LoginDelegator loginDelegator=new LoginDelegator();
	public LoggedInUserData isValidUser(long registrationNumber, String password) {
		return loginDelegator.isValidUser(registrationNumber, password) ;
	}
}
