package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class LoginDAO {

	private Connection con = null;
	private PreparedStatement pst = null;

	public boolean logoutLogin(Boolean flag, User user) {
		User.setLoginStatus(flag);
		if (!flag)
			user.setAdminStatus("");
		return true;
	}

	public boolean signup(User user) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_USER);
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}
}
