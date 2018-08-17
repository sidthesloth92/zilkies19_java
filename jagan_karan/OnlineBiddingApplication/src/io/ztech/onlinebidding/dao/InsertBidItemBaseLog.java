package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class InsertBidItemBaseLog implements SqlQueries {
	DatabaseConfig dbConfig = new DatabaseConfig();

	public void insertBidItem(int bidderId, String itemId, String categoryId, String itemName, String price,
			String starttime, String endtime) {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			databaseConnection.setAutoCommit(false);
			Timestamp start = Timestamp.valueOf(starttime);
			Timestamp end = Timestamp.valueOf(endtime);
			PreparedStatement insertBidItem = databaseConnection.prepareStatement(INSERT_BID_BASE);
			insertBidItem.setInt(1, bidderId);
			insertBidItem.setInt(2, Integer.parseInt(itemId));
			insertBidItem.setInt(3, Integer.parseInt(categoryId));
			insertBidItem.setString(4, itemName);
			insertBidItem.setFloat(5, Float.parseFloat(price));
			insertBidItem.setTimestamp(6, start);
			insertBidItem.setTimestamp(7, end);
			insertBidItem.executeUpdate();
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
