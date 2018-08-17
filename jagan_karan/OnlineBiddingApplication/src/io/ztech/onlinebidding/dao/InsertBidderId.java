package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class InsertBidderId implements SqlQueries, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();
	int bidderNo = 0;

	public int insertBidder(int customerId) {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement insertBidder = databaseConnection.prepareStatement(INSERT_BIDDER);
			insertBidder.setInt(1, customerId);
			insertBidder.executeUpdate();
			PreparedStatement selectBidderId = databaseConnection.prepareStatement(SELECT_BIDDER);
			selectBidderId.setInt(1, customerId);
			ResultSet bidderId = selectBidderId.executeQuery();
			while (bidderId.next()) {
				bidderNo = bidderId.getInt(DB_MAX);
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
		return bidderNo;
	}

}
