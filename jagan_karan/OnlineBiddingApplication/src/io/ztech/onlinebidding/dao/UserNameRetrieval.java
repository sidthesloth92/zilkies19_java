package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class UserNameRetrieval implements SqlQueries, ConstantDisplayStatement, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();

	public boolean userNameRetrieve(String userName) throws Exception {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			PreparedStatement selectUserName = databaseConnection.prepareStatement(SELECT_USERNAME);
			selectUserName.setString(1, userName);
			ResultSet userNameSet = selectUserName.executeQuery();
			while (userNameSet.next()) {
				String username = userNameSet.getString("username");
				if (username.equals("0")) {
					return true;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
		return false;
	}
}
