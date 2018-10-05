package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class CreateLoginDao {

	public boolean createStudentLogin(Register register) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.CREATE_LOGIN);
			preparedStatement.setString(1, register.getReg_no());
			preparedStatement.setString(2, register.getEmail());
			preparedStatement.setString(3, register.getRole());
			preparedStatement.setInt(4, 1);
			preparedStatement.setString(5, register.getReg_no());
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;
	}

}
