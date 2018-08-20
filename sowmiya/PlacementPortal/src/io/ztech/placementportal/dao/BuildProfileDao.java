package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.dbutil.DbConnection;

public class BuildProfileDao {

	public boolean addToProfile(Profile profile, String sql) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, profile.getStudent_id());
			preparedStatement.setString(2, profile.getTitle());
			preparedStatement.setString(3, profile.getDescription());
			if (preparedStatement.executeUpdate() >= 0)
				return true;
		} catch (SQLException e) {
			return false;
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;
	}

}
