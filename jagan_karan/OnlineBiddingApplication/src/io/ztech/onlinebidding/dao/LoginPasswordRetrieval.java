package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class LoginPasswordRetrieval implements SqlQueries, ConstantDisplayStatement {
	DatabaseConfig dbConfig = new DatabaseConfig();

	public String retreivePassword(String username) throws Exception {
		Connection databaseConnection = dbConfig.getConnection();
		String password = null;
		try {
			PreparedStatement selectpassword = databaseConnection.prepareStatement(SELECT_USERNAME_PASSWORD);
			selectpassword.setString(1, username);
			ResultSet pass = selectpassword.executeQuery();
			while (pass.next()) {
				password = pass.getString(DBFields.DB_PASSWORD);
			}
			return password;
		} catch (Exception e) {
			throw e;
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
	}
}
