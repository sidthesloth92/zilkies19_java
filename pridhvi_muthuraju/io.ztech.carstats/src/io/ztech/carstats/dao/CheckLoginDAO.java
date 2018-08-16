package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.constants.AppConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class CheckLoginDAO {
	public static final Logger logger = Logger.getLogger(CheckLoginDAO.class.getName());
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet res = null;

	public boolean isUser(User user) throws SQLException {
		boolean flag = true;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement("select count(*) from users where user_name=? and user_password=?");
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			res = pst.executeQuery();
			res.next();
			if (res.getInt(1) == 0)
				flag = false;
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);

		}
		return flag;
	}

	public boolean isAdmin(User user) throws SQLException {
		boolean flag = true;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(
					"select count(*) from users where user_name=? and" + " user_password=? and admin_status='ADMIN'");
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			res = pst.executeQuery();
			res.next();
			if (res.getInt(1) == 0)
				flag = false;
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);

		}
		return flag;
	}
}
