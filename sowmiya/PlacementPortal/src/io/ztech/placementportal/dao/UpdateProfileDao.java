package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.dbutil.DbConnection;

public class UpdateProfileDao {

	public boolean addToProfile(Profile profile, String sql) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DbConnection.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, profile.getTitle());
			ps.setString(2, profile.getDescription());
			ps.setInt(3, profile.getProfileDetailId());
			if (ps.executeUpdate() >= 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}

		return false;
	}

}
