package io.ztech.carstats.services;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.LoginDelegate;

public class LoginService {

	LoginDelegate loginDelegate = new LoginDelegate();

	public boolean login() {
		return loginDelegate.logoutLogin(true);
	}

	public boolean logout() {
		return loginDelegate.logoutLogin(false);
	}

	public boolean signup(User user) {
		return loginDelegate.signup(user) && this.logout();
	}
}
