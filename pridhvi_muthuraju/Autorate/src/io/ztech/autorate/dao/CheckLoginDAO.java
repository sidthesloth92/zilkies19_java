package io.ztech.autorate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dbutils.DBUtils;

public class CheckLoginDAO {
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet res = null;

	public boolean isUser(User user) throws SQLException {
		boolean flag = true;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.IS_USER);
			pst.setString(1, user.getUserName());
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

	public boolean isAdmin(User user) throws SQLException {
		boolean flag = true;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.IS_ADMIN);
			pst.setString(1, user.getUserName());
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
