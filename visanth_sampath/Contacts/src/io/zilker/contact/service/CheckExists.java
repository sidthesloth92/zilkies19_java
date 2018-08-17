package io.zilker.contact.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.zilker.contact.constants.SqlConstants;

public class CheckExists {
	private static PreparedStatement statement;
	private static ResultSet ResultSet;

	// check if the contact exists
	public static boolean checkExists(String firstName, String lastName, Connection con) throws SQLException {
		try {
			statement = con.prepareStatement(SqlConstants.SELECT_WITH_NAMES);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			ResultSet = statement.executeQuery();
			if (ResultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();

		} finally {
			if (statement != null) {
				statement.close();
			}
			if (ResultSet != null) {
				ResultSet.close();
			}
		}
		return false;
	}

}
