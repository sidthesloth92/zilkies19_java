package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class PresentBidItemIdFromBase implements DBFields, SqlQueries {
	DatabaseConfig config = new DatabaseConfig();
	ArrayList<String> bidItemIdFromBase = new ArrayList<String>();

	public ArrayList<String> presentBidItemId(String itemId, String categoryId) throws Exception {
		Connection databaseConnection = config.getConnection();
		try {
			PreparedStatement bidItemId = databaseConnection.prepareStatement(SELECT_BID_ITEM_ID_FROM_BASE_LOG);
			bidItemId.setInt(1, Integer.parseInt(itemId));
			bidItemId.setInt(2, Integer.parseInt(categoryId));
			ResultSet bidItemSet = bidItemId.executeQuery();
			while (bidItemSet.next()) {
				bidItemIdFromBase.add(Integer.toString(bidItemSet.getInt(DB_BID_ITEM_ID)));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			config.closeConnection(databaseConnection);
		}

		return bidItemIdFromBase;

	}
}
