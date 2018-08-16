package io.ztech.carstats.delegate;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.dao.LoginDAO;

public class LoginDelegate {
	LoginDAO ldao = new LoginDAO();

	public boolean logoutLogin(Boolean flag) {
		return ldao.logoutLogin(flag);
	}

	public boolean signup(User user) {
		return ldao.signup(user) && Validator.validateUsername(user.getUserName())
				&& Validator.validatePassword(user.getPassword());
	}
}
