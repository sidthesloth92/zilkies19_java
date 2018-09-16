package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.dao.CheckLoginDAO;

public class CheckLoginDelegate {
	CheckLoginDAO checkLoginDAO = new CheckLoginDAO();

	public boolean isUser(User user) throws SQLException {
		return checkLoginDAO.isUser(user);
	}
	
	public boolean isAdmin(User user) throws SQLException {
		return checkLoginDAO.isAdmin(user);
	}
}
