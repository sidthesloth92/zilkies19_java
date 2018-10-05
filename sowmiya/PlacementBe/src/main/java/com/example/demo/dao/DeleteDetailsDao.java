package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.example.demo.constants.SqlConstants;
import com.example.demo.dbutil.DbConnection;

public class DeleteDetailsDao {
	private Logger log;

	public DeleteDetailsDao() {
		log = Logger.getLogger("DeleteDetailsdao.class");
	}

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

	public boolean deleteCompany(String id) {
		log.info("Entering deleteCompany()");
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
			log.warning(e.toString());

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		log.info("Exiting deleteCompany()");
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
