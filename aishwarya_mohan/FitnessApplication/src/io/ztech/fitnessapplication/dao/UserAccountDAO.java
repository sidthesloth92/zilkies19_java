package io.ztech.fitnessapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserAccountDetails;
import io.ztech.fitnessapplication.constants.SQLQueryStringConstants;
import io.ztech.fitnessapplication.dbutils.Config;

public class UserAccountDAO {
	private static final Config con_obj = new Config();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public boolean addUserAccount(UserAccountDetails newAccount) {
		try {
			// user_name, password, role, first_name, last_name, email_id, phone_no
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_USER);
			ps.setString(1, newAccount.getUserName());
			ps.setString(2, newAccount.getPassword());
			ps.setInt(3, newAccount.getRole());
			ps.setString(4, newAccount.getFirstName());
			ps.setString(5, newAccount.getLastName());
			ps.setString(6, newAccount.getEmail());
			ps.setString(7, newAccount.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public boolean loginUser(UserAccount account) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_ACCOUNT_DETAILS);
			ps.setString(1, account.getUserName());
			ps.setString(2, account.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return false;
	}

	public int getUserAccesslevel(UserAccount account) {
		int level = 0;
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_USER_TYPE);
			ps.setString(1, account.getUserName());
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

	public UserAccountDetails getAccount(UserAccount curAccount) {
		UserAccountDetails account = new UserAccountDetails();
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_ACCOUNT_DETAILS);
			ps.setString(1, curAccount.getUserName());
			ps.setString(2, curAccount.getPassword());
			rs = ps.executeQuery();

			if (rs.next()) {
				account.setUserName(rs.getString(1));

				account.setFirstName(rs.getString(4));
				account.setLastName(rs.getString(5));
				account.setEmail(rs.getString(6));
				account.setPhone(rs.getString(7));
			}

		} catch (SQLException e) {
			return null;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return account;
	}

	public boolean updateAccount(UserAccountDetails curAccount) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.UPDATE_ACCOUNT_DETAILS);
			ps.setString(1, curAccount.getFirstName());
			ps.setString(2, curAccount.getLastName());
			ps.setString(3, curAccount.getEmail());
			ps.setString(4, curAccount.getPhone());
			ps.setString(5, curAccount.getUserName());

			ps.executeUpdate();

		} catch (SQLException e) {
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

}
