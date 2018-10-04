package com.ztech.FitnessApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ztech.FitnessApp.beans.UserProfile;
import com.ztech.FitnessApp.constants.SQLQueryStrings;
import com.ztech.FitnessApp.dbutils.DBConfiguration;

public class UserProfileDao {

	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static DBConfiguration connectionObject;

	public UserProfileDao() {
		connectionObject = new DBConfiguration();
	}

	// user_name,gender, age, height, weight, activity_id, bmi, bmr
	public boolean customise(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.CREATE_PROFILE);
			ps.setString(1, newProfile.getUserName());
			ps.setInt(2, newProfile.getGender());
			ps.setInt(3, newProfile.getAge());
			ps.setFloat(4, newProfile.getHeight());
			ps.setFloat(5, newProfile.getWeight());
			ps.setInt(6, newProfile.getLifestyle());
			ps.setFloat(7, newProfile.getBmi());
			ps.setFloat(8, newProfile.getBmr());

			int updated = ps.executeUpdate();
			System.out.println("updated" + updated);
			if (updated > 0) {
				return true;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean setAge(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.SET_AGE);
			ps.setInt(1, newProfile.getAge());
			ps.setString(2, newProfile.getUserName());

			int updated = ps.executeUpdate();
			System.out.println("updated" + updated);
			if (updated > 0) {
				return true;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean setHeight(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.SET_HEIGHT);
			ps.setFloat(1, newProfile.getHeight());
			ps.setString(2, newProfile.getUserName());

			int updated = ps.executeUpdate();
			System.out.println("updated" + updated);
			if (updated > 0) {
				return true;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean setWeight(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.SET_WEIGHT);
			ps.setFloat(1, newProfile.getWeight());
			ps.setString(2, newProfile.getUserName());

			int updated = ps.executeUpdate();
			System.out.println("updated" + updated);
			if (updated > 0) {
				return true;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean setLifestyle(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.SET_LIFESTYLE);
			ps.setInt(1, newProfile.getLifestyle());
			ps.setString(2, newProfile.getUserName());

			int updated = ps.executeUpdate();
			System.out.println("updated" + updated);
			if (updated > 0) {
				return true;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean setBmi(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.SET_BMI);
			ps.setFloat(1, newProfile.getBmi());
			ps.setString(2, newProfile.getUserName());

			int updated = ps.executeUpdate();
			System.out.println("updated" + updated);
			if (updated > 0) {
				return true;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean setBmr(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.SET_BMR);
			ps.setFloat(1, newProfile.getBmr());
			ps.setString(2, newProfile.getUserName());

			int updated = ps.executeUpdate();
			System.out.println("updated" + updated);
			if (updated > 0) {
				return true;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean setTarget(UserProfile newProfile) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.SET_TARGET);
			ps.setInt(1, newProfile.getTarget());
			ps.setString(2, newProfile.getUserName());

			int updated = ps.executeUpdate();
			System.out.println("updated" + updated);
			if (updated > 0) {
				return true;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public UserProfile getProfile(String userName) {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_PROFILE);
			ps.setString(1, userName);

			rs = ps.executeQuery();

			UserProfile profile = new UserProfile();

			if (rs.next()) {
				profile.setUserName(rs.getString("user_name"));
				profile.setGender(Integer.parseInt(rs.getString("gender")));
				profile.setAge(Integer.parseInt(rs.getString("age")));
				profile.setHeight(Float.parseFloat(rs.getString("height")));
				profile.setWeight(Float.parseFloat(rs.getString("weight")));
				profile.setLifestyle(Integer.parseInt(rs.getString("activity_id")));
				profile.setBmi(Float.parseFloat(rs.getString("bmi")));
				profile.setBmr(Float.parseFloat(rs.getString("bmr")));
				if (rs.getString("target") != null) {
					profile.setTarget(Integer.parseInt(rs.getString("target")));
				}

				return profile;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return null;
	}
}
