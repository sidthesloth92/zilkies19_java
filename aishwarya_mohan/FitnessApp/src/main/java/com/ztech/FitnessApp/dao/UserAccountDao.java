package com.ztech.FitnessApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ztech.FitnessApp.beans.CurrentUser;
import com.ztech.FitnessApp.beans.UserAccount;
import com.ztech.FitnessApp.constants.SQLQueryStrings;
import com.ztech.FitnessApp.dbutils.DBConfiguration;

public class UserAccountDao {

	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static DBConfiguration connectionObject;

	public UserAccountDao() {
		connectionObject = new DBConfiguration();
	}

	public boolean login(CurrentUser user) {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.LOGIN_CHECK);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());

			String loggedInUser = "";
			rs = ps.executeQuery();

			while (rs.next()) {
				loggedInUser = rs.getString(1);
			}

			if (loggedInUser.equals(user.getUserName())) {
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean signUp(UserAccount newAccount) {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.CREATE_ACCOUNT);
			ps.setString(1, newAccount.getUserName());
			ps.setString(2, newAccount.getPassword());
			ps.setString(3, newAccount.getFirstName());
			ps.setString(4, newAccount.getLastName());
			ps.setString(5, newAccount.getEmailID());
			ps.setString(6, newAccount.getPhoneNo());

			int updated = ps.executeUpdate();
			// System.out.println("updated"+updated);
			if (updated > 0) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean ifUsernameExists(String userName) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.USERNAME_EXISTS);
			ps.setString(1, userName);

			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
	}

	public boolean ifEmailExists(String email) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.EMAIL_EXISTS);
			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
	}

}
