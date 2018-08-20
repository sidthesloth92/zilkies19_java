package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class ItemRetrieval implements SqlQueries, ConstantDisplayStatement, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();
	HashMap<String, String> itemList = new HashMap<>();

	public HashMap<String, String> retreiveItem(String categoryId) throws Exception {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			PreparedStatement selectItem = databaseConnection.prepareStatement(SELECT_ITEM);
			selectItem.setInt(1, Integer.parseInt(categoryId));
			ResultSet category = selectItem.executeQuery();
			while (category.next()) {
				itemList.put(category.getString(DB_ITEM_ID), category.getString(DB_ITEM_NAME));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
		return itemList;
	}
}
