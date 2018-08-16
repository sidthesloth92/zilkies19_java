package io.ztech.carstats.services;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.LoginDelegate;

public class LoginService {

	LoginDelegate lDelegate = new LoginDelegate();

	public boolean login() {
		return lDelegate.logoutLogin(true);
	}

	public boolean logout() {
		return lDelegate.logoutLogin(false);
	}

	public boolean signup(User user) {
		return lDelegate.signup(user) && this.logout();
	}
}
