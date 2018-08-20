package io.ztech.fitnessapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserPhysicalDetails;
import io.ztech.fitnessapplication.constants.SQLQueryStringConstants;
import io.ztech.fitnessapplication.dbutils.Config;

public class UserPhysicalDetailsDao {
	private static final Config con_obj = new Config();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public boolean addProfile(UserPhysicalDetails newProfile) {
		try {
			// user_name,gender,age,height,weight,activity_id, bmi, bmr, target_id
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_PROFILE);
			ps.setString(1, newProfile.getUserName());
			ps.setString(2, newProfile.getGender());
			ps.setInt(3, newProfile.getAge());
			ps.setFloat(4, newProfile.getHeight());
			ps.setFloat(5, newProfile.getWeight());
			ps.setInt(6, newProfile.getActivty());
			ps.setFloat(7, newProfile.getBmi());
			ps.setInt(8, newProfile.getBmr());
			ps.setInt(9, newProfile.getPlan());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public UserPhysicalDetails getProfile(UserAccount account) {
		UserPhysicalDetails profile = new UserPhysicalDetails();
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_PROFILE);
			ps.setString(1, account.getUserName());
			rs = ps.executeQuery();

			if (rs.next()) {
				profile.setUserName(rs.getString(1));
				profile.setGender(rs.getString(2));
				profile.setAge(rs.getInt(3));
				profile.setHeight(rs.getFloat(4));
				profile.setWeight(rs.getFloat(5));
				profile.setActivty(rs.getInt(6));
				profile.setBmi(rs.getFloat(7));
				profile.setBmr(rs.getInt(8));
				profile.setPlan(rs.getInt(9));

				return profile;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return profile;
	}

	public boolean setTarget(UserPhysicalDetails newProfile) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_TARGET);
			ps.setInt(1, newProfile.getPlan());
			ps.setString(2, newProfile.getUserName());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public boolean trackWeight(UserPhysicalDetails Profile) {
		try {
			conn = con_obj.getConnection();
			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_WEIGHT_LOG);
			ps.setString(1, Profile.getUserName());
			ps.setFloat(2, Profile.getWeight());

			ps.executeUpdate();

		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public boolean updateWeight(UserPhysicalDetails Profile) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.SET_WEIGHT);
			ps.setFloat(1, Profile.getWeight());
			ps.setString(2, Profile.getUserName());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public boolean updateProfile(UserPhysicalDetails Profile) {
		try {
			conn = con_obj.getConnection();
			ps = conn.prepareStatement(SQLQueryStringConstants.UPDATE_PROFILE);
			ps.setFloat(1, Profile.getHeight());
			ps.setFloat(2, Profile.getWeight());
			ps.setInt(3, Profile.getAge());
			ps.setString(4, Profile.getGender() + "");
			ps.setInt(5, Profile.getActivty());
			ps.setFloat(6, Profile.getBmi());
			ps.setInt(7, Profile.getBmr());
			ps.setInt(8, Profile.getPlan());
			ps.setString(9, Profile.getUserName());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

}
