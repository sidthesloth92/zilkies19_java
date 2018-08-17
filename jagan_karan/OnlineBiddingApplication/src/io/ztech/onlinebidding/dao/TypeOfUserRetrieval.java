package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class TypeOfUserRetrieval implements SqlQueries, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();
	String typeOfUser;

	public String retrieveUserType(String username) {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement selectType = databaseConnection.prepareStatement(SELECT_TYPE_OF_USER);
			selectType.setString(1, username);
			ResultSet typeSet = selectType.executeQuery();
			while (typeSet.next()) {
				typeOfUser = typeSet.getString(DB_TYPEOFUSER);
			}
			databaseConnection.commit();
		} catch (Exception e) {
			try {
				databaseConnection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
		return typeOfUser;
	}

}
