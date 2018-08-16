package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.constants.AppConstants;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class LoginDAO {

	private final Logger logger = Logger.getLogger(OutputDAO.class.getName());
	private Connection con = null;
	private PreparedStatement pst = null;

	public boolean logoutLogin(Boolean flag) {
		User.setLoginStatus(flag);
		return true;
	}

	public boolean signup(User user) {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_USER);
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}
}
