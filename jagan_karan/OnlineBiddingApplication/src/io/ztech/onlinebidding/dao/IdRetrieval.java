package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class IdRetrieval implements SqlQueries, DBFields {
	DatabaseConfig config = new DatabaseConfig();

	public int customerIdRetrieve(String username) throws Exception {
		Connection databaseConnection = config.getConnection();
		int custId = 0;
		try {
			PreparedStatement customerId = databaseConnection.prepareStatement(SELECT_CUSTOMER_ID);
			customerId.setString(1, username);
			ResultSet id = customerId.executeQuery();
			while (id.next()) {
				custId = id.getInt(DB_CUST_ID);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			config.closeConnection(databaseConnection);
		}
		return custId;
	}

	public int bidderIdRetrieve(String bidItemId) throws Exception {
		Connection databaseConnection = config.getConnection();
		int bidderId = 0;
		try {
			PreparedStatement customerId = databaseConnection.prepareStatement(SELECT_BIDDER_ID);
			customerId.setInt(1, Integer.parseInt(bidItemId));
			ResultSet id = customerId.executeQuery();
			while (id.next()) {
				bidderId = id.getInt(DB_BIDDER_ID);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			config.closeConnection(databaseConnection);
		}
		return bidderId;
	}

	public int customerIdOfBidder(int bidderId) throws Exception {
		Connection databaseConnection = config.getConnection();
		int customerIdOfBidder = 0;
		try {
			PreparedStatement customerIdBidder = databaseConnection
					.prepareStatement(SELECT_CUSTOMERID_RELEVANT_BIDDERID);
			customerIdBidder.setInt(1, bidderId);
			ResultSet id = customerIdBidder.executeQuery();
			while (id.next()) {
				customerIdOfBidder = id.getInt(DB_CUSTOMER_ID);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			config.closeConnection(databaseConnection);
		}
		return customerIdOfBidder;
	}
}
