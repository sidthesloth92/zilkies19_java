package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class PriceRetrieval implements SqlQueries, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();

	public float priceRetrive(String bitItemId) {
		float price = 0;
		Connection databaseConnection = dbConfig.getConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement bidItemFromLog = databaseConnection.prepareStatement(SELECT_PRICE_LOG);
			bidItemFromLog.setInt(1, Integer.parseInt(bitItemId));
			ResultSet biditemset = bidItemFromLog.executeQuery();
			while (biditemset.next()) {
				price = biditemset.getFloat(DB_PRICE);
			}
			if (price == 0) {
				PreparedStatement biditem = databaseConnection.prepareStatement(SELECT_PRICE);
				biditem.setInt(1, Integer.parseInt(bitItemId));
				ResultSet biditemset1 = biditem.executeQuery();
				while (biditemset1.next()) {
					price = biditemset1.getFloat(DB_PRICE);
				}
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
		return price;
	}
}
