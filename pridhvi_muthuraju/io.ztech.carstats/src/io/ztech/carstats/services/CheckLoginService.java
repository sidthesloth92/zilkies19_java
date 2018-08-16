package io.ztech.carstats.services;

import java.sql.SQLException;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.CheckLoginDelegate;

public class CheckLoginService {

	CheckLoginDelegate checkLoginDelegate = new CheckLoginDelegate();

	public boolean isUser(User user) throws SQLException {
		return checkLoginDelegate.isUser(user);
	}
	
	
	public boolean isAdmin(User user) throws SQLException {
		return checkLoginDelegate.isAdmin(user);
	}
}
