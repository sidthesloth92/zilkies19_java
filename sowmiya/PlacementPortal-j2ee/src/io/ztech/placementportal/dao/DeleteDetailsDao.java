package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class DeleteDetailsDao {

	public boolean deleteStudent(String id) throws SQLException {
		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.DELETESTUDENTDETAIL);
			preparedStatement.setString(1, id);
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}

		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;

	}

	public boolean deleteCompany(String id) throws SQLException {
		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.DELETECOMPANYDETAIL);
			preparedStatement.setString(1, id);
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}

		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;

	}

	public boolean deleteProfile(String profile_id, String sql) throws SQLException {
		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, profile_id);
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}

		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

		return false;
	}
}
