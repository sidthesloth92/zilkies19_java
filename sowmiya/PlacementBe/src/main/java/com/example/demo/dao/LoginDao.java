package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.example.demo.beans.Register;
import com.example.demo.constants.SqlConstants;
import com.example.demo.dbutil.DbConnection;

public class LoginDao {
	Logger log = Logger.getLogger("LoginDao.class");

	public Register login(Register login) {
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
				return validUser;
			}

		} catch (SQLException e) {
			log.info(e.toString());
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return null;
	}

}
