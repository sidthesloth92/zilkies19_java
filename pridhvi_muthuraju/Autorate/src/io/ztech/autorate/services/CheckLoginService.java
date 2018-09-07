package io.ztech.autorate.services;

import java.sql.SQLException;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.delegate.CheckLoginDelegate;

public class CheckLoginService {

	CheckLoginDelegate checkLoginDelegate = new CheckLoginDelegate();

	public boolean isUser(User user) throws SQLException {
		return checkLoginDelegate.isUser(user);
	}
	
	
	public boolean isAdmin(User user) throws SQLException {
		return checkLoginDelegate.isAdmin(user);
	}
}
