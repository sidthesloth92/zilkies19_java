package io.ztech.autorate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dbutils.DBUtils;

public class LoginDAO {

	private Connection con = null;
	private PreparedStatement pst = null;
	private static ResultSet res = null;

	public boolean logoutLogin(Boolean flag, User user) {
		user.setLoginStatus(flag);
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

	public boolean isUser(User user) throws SQLException {
		boolean flag = true;
		try {
			con = DBUtils.getConnection();
			if(user.getPassword().equals("")) {
				pst = con.prepareStatement(SQLConstants.IS_USERNAME);
				pst.setString(1, user.getUsername());
			}
			else {
				pst = con.prepareStatement(SQLConstants.IS_USER);
				pst.setString(1, user.getUsername());
				pst.setString(2, user.getPassword());
			}
			res = pst.executeQuery();
			res.next();
			if (res.getInt(1) == 0)
				flag = false;
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return flag;
	}

	public boolean isAdmin(User user) throws SQLException {
		boolean flag = true;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.IS_ADMIN);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			res = pst.executeQuery();
			res.next();
			if (res.getInt(1) == 0)
				flag = false;
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);

		}
		return flag;
	}
}
