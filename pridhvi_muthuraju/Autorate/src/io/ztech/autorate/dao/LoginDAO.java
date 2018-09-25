package io.ztech.autorate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dbutils.DBUtils;

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
			pst.setString(1, user.getFirstName());
			pst.setString(2, user.getLastName());
			pst.setString(3, user.getUsername());
			pst.setString(4, user.getEmailId());
			pst.setString(5, user.getPassword());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}
}
