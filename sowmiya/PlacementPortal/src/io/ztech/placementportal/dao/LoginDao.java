package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class LoginDao {

	public String login(Register login) throws Exception {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String role = "";
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.LOGIN_SQL);
			preparedStatement.setString(1, login.getUserName());
			preparedStatement.setString(2, login.getPassword());
			preparedStatement.setInt(3, 1);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				role = resultSet.getString("role");
				preparedStatement = connection.prepareStatement(SqlConstants.SET_RECENT_LOGIN);
				preparedStatement.setString(1, login.getTime().toString());
				preparedStatement.setString(2, login.getUserName());
				if (preparedStatement.executeUpdate() >= 0) {
				}
			}
			return role;

		} catch (SQLException e) {
			throw new Exception(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

}
