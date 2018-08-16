package io.ztech.carstats.delegate;

import java.sql.SQLException;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.dao.CheckLoginDAO;

public class CheckLoginDelegate {
	CheckLoginDAO cldao = new CheckLoginDAO();

	public boolean isUser(User user) throws SQLException {
		return cldao.isUser(user);
	}
	
	public boolean isAdmin(User user) throws SQLException {
		return cldao.isAdmin(user);
	}
}
