package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class RemoveFromLog implements SqlQueries, DBFields {

	DatabaseConfig dbConfig = new DatabaseConfig();

	public void removeBidFromLog(ArrayList<Integer> closedBidItem) {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			databaseConnection.setAutoCommit(false);
			Iterator<Integer> closedBidIterator = closedBidItem.iterator();
			while (closedBidIterator.hasNext()) {
				PreparedStatement deleteBid = databaseConnection.prepareStatement(DELETE_BID_FROM_LOG);
				deleteBid.setInt(1, closedBidIterator.next());
				deleteBid.executeUpdate();
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
	}
}
