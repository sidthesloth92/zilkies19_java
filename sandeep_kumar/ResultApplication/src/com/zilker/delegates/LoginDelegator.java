package com.zilker.delegates;


import com.zilker.beans.LoggedInUserData;
import com.zilker.dao.*;

public class LoginDelegator {
	LoginDetailsDao loginDetailsDao= new LoginDetailsDao();
	public LoggedInUserData isValidUser(long registrationNumber, String password) {
		return loginDetailsDao.isValidUser(registrationNumber, password);
	}
}
