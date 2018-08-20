package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class BidItemFromFinalLog implements DBFields, SqlQueries {
	DatabaseConfig config = new DatabaseConfig();
	HashMap<String, BidItem> bidList = new HashMap<>();

	public HashMap<String, BidItem> retrieveSoldBidItemDetails() throws Exception {
		Connection databaseConnection = config.getConnection();
		try {

			PreparedStatement biditem = databaseConnection.prepareStatement(SELECT_SOLDBID_FROM_FINAL_LOG);
			ResultSet biditemset = biditem.executeQuery();
			while (biditemset.next()) {
				BidItem bidItemDetails = new BidItem();
				bidItemDetails.setApplicantId(Integer.toString(biditemset.getInt(DB_APPLICANT_ID)));
				bidItemDetails.setPrice(Float.toString(biditemset.getFloat(DB_PRICE)));
				bidList.put(Integer.toString((biditemset.getInt(DB_BID_ITEM_ID))), bidItemDetails);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			config.closeConnection(databaseConnection);
		}

		return bidList;
	}

	public HashMap<String, BidItem> retrieveUnsoldBidItemDetails() throws Exception {
		Connection databaseConnection = config.getConnection();
		try {

			PreparedStatement biditem = databaseConnection.prepareStatement(SELECT_UNSOLDBID_FROM_FINAL_LOG);
			ResultSet biditemset = biditem.executeQuery();
			while (biditemset.next()) {
				BidItem bidItemDetails = new BidItem();
				bidItemDetails.setApplicantId(Integer.toString(biditemset.getInt(DB_APPLICANT_ID)));
				bidItemDetails.setPrice(Float.toString(biditemset.getFloat(DB_PRICE)));
				bidList.put(Integer.toString((biditemset.getInt(DB_BID_ITEM_ID))), bidItemDetails);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			config.closeConnection(databaseConnection);
		}

		return bidList;
	}

}
