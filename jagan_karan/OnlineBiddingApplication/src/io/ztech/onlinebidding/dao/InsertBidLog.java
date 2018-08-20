package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class InsertBidLog implements SqlQueries {
	DatabaseConfig dbConfig = new DatabaseConfig();

	public void insertBidLog(int bidItemId, int customerId, float price) {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			PreparedStatement insertstatement = databaseConnection.prepareStatement(INSERT_BID_LOG);
			insertstatement.setInt(1, bidItemId);
			insertstatement.setInt(2, customerId);
			insertstatement.setFloat(3, (int) price);
			insertstatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			insertstatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
	}
}
