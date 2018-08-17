package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.dbutil.DbConnection;

public class BuildProfileDao {
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean addToProfile(Profile profile, String sql) {
		Connection connection = DbConnection.getConnection();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, profile.getStudent_id());
			ps.setString(2, profile.getTitle());
			ps.setString(3, profile.getDescription());
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
