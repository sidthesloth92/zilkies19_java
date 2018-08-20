package io.ztech.carstats.services;

import java.sql.SQLException;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.LoginDelegate;

public class LoginService {

	LoginDelegate loginDelegate = new LoginDelegate();

	public boolean login(User user) {
		return loginDelegate.logoutLogin(true,user);
	}

	public boolean logout(User user) {
		return loginDelegate.logoutLogin(false,user);
	}

	public boolean signup(User user) throws SQLException {
		return loginDelegate.signup(user) && this.logout(user);
	}
}
