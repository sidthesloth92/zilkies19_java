package io.ztech.fitnessapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserProfile;
import io.ztech.fitnessapplication.constants.SQLQueryStringConstants;
import io.ztech.fitnessapplication.dbutils.Config;

public class UserAccountDAO {
	private static final Config con_obj = new Config();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public boolean addUserAccount(UserProfile newProfile) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_USER);
			ps.setInt(1, 2);
			ps.setString(2, newProfile.getFirstName());
			ps.setString(3, newProfile.getLastName());
			ps.setString(4, newProfile.getUserName());
			ps.setString(5, newProfile.getPassword());
			ps.setString(6, newProfile.getEmailID());
			ps.setString(7, newProfile.getPhoneNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public int findUser(UserAccount account) {
		int regID = 0;
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_REG_ID);
			ps.setString(1, account.getUserName());
			ps.setString(2, account.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				regID = rs.getInt(1);
			}

		} catch (SQLException e) {
			return 0;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return regID;
	}

	public boolean setLogin(UserAccount account) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.SET_LOGIN_STATUS);
			ps.setInt(1, account.getLoginStatus());
			ps.setInt(2, account.getRegID());
			ps.executeUpdate();

		} catch (SQLException e) {
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public int getUserAccesslevel(UserAccount account) {
		int level = 0;
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_USER_TYPE);
			ps.setInt(1, account.getRegID());
			rs = ps.executeQuery();
			if (rs.next()) {
				level = rs.getInt(1);
			}

		} catch (SQLException e) {
			return 0;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return level;
	}

	public UserProfile getProfile(UserAccount account) {
		UserProfile profile = new UserProfile();
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_ACCOUNT_DETAILS);
			ps.setInt(1, account.getRegID());
			rs = ps.executeQuery();

			if (rs.next()) {
				profile.setFirstName(rs.getString(3));
				profile.setLastName(rs.getString(4));
				profile.setUserName(rs.getString(5));
				profile.setEmailID(rs.getString(7));
				profile.setPhoneNo(rs.getString(8));
			}

		} catch (SQLException e) {
			return null;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return profile;
	}

}
