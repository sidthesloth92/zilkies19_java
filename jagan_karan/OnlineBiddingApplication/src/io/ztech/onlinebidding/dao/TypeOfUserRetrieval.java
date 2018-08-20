package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class TypeOfUserRetrieval implements SqlQueries, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();
	String typeOfUser;

	public String retrieveUserType(String username) throws Exception {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			PreparedStatement selectType = databaseConnection.prepareStatement(SELECT_TYPE_OF_USER);
			selectType.setString(1, username);
			ResultSet typeSet = selectType.executeQuery();
			while (typeSet.next()) {
				typeOfUser = typeSet.getString(DB_TYPEOFUSER);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
		return typeOfUser;
	}

}
