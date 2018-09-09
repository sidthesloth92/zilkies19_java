package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.dao.LoginDAO;

public class LoginDelegate {
	LoginDAO loginDAO = new LoginDAO();

	public boolean logoutLogin(Boolean flag,User user) {
		return loginDAO.logoutLogin(flag,user);
	}

	public boolean signup(User user) throws SQLException {
		return loginDAO.signup(user);
	}
}
