package io.ztech.carstats.delegate;

import java.sql.SQLException;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.dao.CheckLoginDAO;

public class CheckLoginDelegate {
	CheckLoginDAO checkLoginDAO = new CheckLoginDAO();

	public boolean isUser(User user) throws SQLException {
		return checkLoginDAO.isUser(user);
	}
	
	public boolean isAdmin(User user) throws SQLException {
		return checkLoginDAO.isAdmin(user);
	}
}
