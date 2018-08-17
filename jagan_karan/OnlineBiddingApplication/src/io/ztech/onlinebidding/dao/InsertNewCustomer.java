package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.onlinebidding.bean.CustomerDetail;
import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class InsertNewCustomer implements SqlQueries, ConstantDisplayStatement {
	DatabaseConfig dbConfig = new DatabaseConfig();
	public static Logger logger = Logger.getLogger("OnlineBiddingDao");

	public void insertNewUserDetails(CustomerDetail registerDetails) {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement insertstatement = databaseConnection.prepareStatement(INSERT_NEW_CUSTOMER);
			insertstatement.setString(1, registerDetails.getFirstName());
			insertstatement.setString(2, registerDetails.getLastName());
			insertstatement.setString(3, registerDetails.getEmail());
			insertstatement.setString(4, registerDetails.getMobileNumber());
			insertstatement.setString(5, registerDetails.getDateOfBirth());
			insertstatement.setString(6, registerDetails.getTypeOfUser());
			insertstatement.setString(7, registerDetails.getUserName());
			insertstatement.setString(8, registerDetails.getPassword());
			insertstatement.executeUpdate();
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
	}
}
