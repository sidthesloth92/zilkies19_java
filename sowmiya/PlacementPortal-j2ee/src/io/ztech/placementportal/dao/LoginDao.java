package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class LoginDao {

	public Register login(Register login) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Register validUser = new Register();
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.LOGIN_SQL);
			preparedStatement.setString(1, login.getUserName());
			preparedStatement.setString(2, login.getPassword());
			preparedStatement.setInt(3, 1);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				validUser.setRole(resultSet.getString("role"));
				if (resultSet.getString("role").equals("student")) {
					preparedStatement = connection.prepareStatement(SqlConstants.GET_STUDENT_NAME);
					preparedStatement.setString(1, resultSet.getString("reg_no"));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						validUser.setUserName(resultSet.getString("student_name"));
						validUser.setReg_no(resultSet.getString("student_id"));
					}
				} else {
					validUser.setUserName(login.getUserName());
				}
				preparedStatement = connection.prepareStatement(SqlConstants.SET_RECENT_LOGIN);
				preparedStatement.setString(1, login.getTime().toString());
				preparedStatement.setString(2, login.getUserName());
				if (preparedStatement.executeUpdate() >= 0) {
					return validUser;
				}
			}

		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return null;
	}

}
