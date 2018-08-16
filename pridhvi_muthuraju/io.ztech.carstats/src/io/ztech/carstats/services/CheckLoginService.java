package io.ztech.carstats.services;

import java.sql.SQLException;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.CheckLoginDelegate;

public class CheckLoginService {

	CheckLoginDelegate clDelegate = new CheckLoginDelegate();

	public boolean isUser(User user) throws SQLException {
		return clDelegate.isUser(user);
	}
	
	
	public boolean isAdmin(User user) throws SQLException {
		return clDelegate.isAdmin(user);
	}
}
