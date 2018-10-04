package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.beans.Profile;
import com.example.demo.dbutil.DbConnection;

public class BuildProfileDao {

	public boolean addToProfile(Profile profile, String sql) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, profile.getStudent_id());
			preparedStatement.setString(2, profile.getTitle());
			preparedStatement.setString(3, profile.getDescription());
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
