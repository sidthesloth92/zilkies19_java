package com.ztech.io.fitnessfirstprototype.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ztech.io.fitnessfirstprototype.beans.User;
import com.ztech.io.fitnessfirstprototype.beans.UserAccount;
import com.ztech.io.fitnessfirstprototype.constants.SQLQueryStrings;
import com.ztech.io.fitnessfirstprototype.dbutils.DBConfiguration;

public class UserAccountDao {
	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static DBConfiguration connectionObject;

	public UserAccountDao() {
		connectionObject = new DBConfiguration();
	}

	public boolean signUp(UserAccount newAccount) throws ClassNotFoundException, SQLException {
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
			System.out.println("updated"+updated);
			if (updated > 0) {
				return true;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean login(User user) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.LOGIN_CHECK);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());

			System.out.println(user.getUsername());
			String loggedInUser = "";
			rs = ps.executeQuery();

			while (rs.next()) {
				loggedInUser = rs.getString(1);
			}

			if (loggedInUser.equals(user.getUsername())) {
				return true;
			} else {
				return false;
			}

		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
	}

	public boolean ifUsernameExists(User user) throws ClassNotFoundException, SQLException {
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.USERNAME_EXISTS);
			ps.setString(1, user.getUsername());

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
