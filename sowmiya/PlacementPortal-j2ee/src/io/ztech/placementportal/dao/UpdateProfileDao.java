package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.dbutil.DbConnection;

public class UpdateProfileDao {
	private Logger log;

	public UpdateProfileDao() {
		log = Logger.getLogger("UpdateProfileDao.class");
	}

	public boolean addToProfile(Profile profile, String sql) throws SQLException {
		log.info("Entering addToProfile()");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, profile.getTitle());
			preparedStatement.setString(2, profile.getDescription());
			preparedStatement.setInt(3, profile.getProfileDetailId());
			if (preparedStatement.executeUpdate() >= 0) {
				return true;}
		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		log.info("Exiting addToProfile()");
		return false;
	}

}
