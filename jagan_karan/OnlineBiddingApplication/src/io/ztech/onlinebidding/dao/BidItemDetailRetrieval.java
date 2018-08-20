package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class BidItemDetailRetrieval implements SqlQueries, ConstantDisplayStatement, DBFields {
	DatabaseConfig config = new DatabaseConfig();
	HashMap<String, BidItem> bidList = new HashMap<>();

	public HashMap<String, BidItem> retrieveBidItemDetails(String itemId, String categoryId) throws Exception {
		Connection databaseConnection = config.getConnection();
		try {

			PreparedStatement bidItem = databaseConnection.prepareStatement(SELECT_BID_ITEM_FROM_BASELOG);
			bidItem.setInt(1, Integer.parseInt(categoryId));
			bidItem.setInt(2, Integer.parseInt(itemId));
			ResultSet bidItemSet = bidItem.executeQuery();
			while (bidItemSet.next()) {
				BidItem bidItemDetails = new BidItem();
				bidItemDetails.setBidderId(Integer.toString(bidItemSet.getInt(DB_BIDDER_ID)));
				bidItemDetails.setCategoryId(Integer.toString(bidItemSet.getInt(DB_CATEGORY_ID)));
				bidItemDetails.setItemId(Integer.toString(bidItemSet.getInt(DB_ITEM_ID)));
				bidItemDetails.setPrice(Float.toString(bidItemSet.getFloat(DB_PRICE)));
				bidItemDetails.setItemName(bidItemSet.getString(DB_ITEM_NAME));
				bidItemDetails.setStarttime(bidItemSet.getTimestamp(DB_STARTTIME));
				bidItemDetails.setEndtime(bidItemSet.getTimestamp(DB_ENDTIME));
				bidList.put(Integer.toString((bidItemSet.getInt(DB_BID_ITEM_ID))), bidItemDetails);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			config.closeConnection(databaseConnection);
		}

		return bidList;
	}

	public BidItem retrieveBidItemDetails(String bidItemId) throws Exception {
		Connection databaseConnection = config.getConnection();
		BidItem bidItemDetails = new BidItem();

		try {

			databaseConnection.setAutoCommit(false);
			PreparedStatement biditemprice = databaseConnection.prepareStatement(SELECT_MAX_PRICE);
			biditemprice.setInt(1, Integer.parseInt(bidItemId));
			ResultSet biditemset = biditemprice.executeQuery();
			while (biditemset.next()) {
				bidItemDetails.setPrice(Float.toString(biditemset.getFloat(DB_MAX)));
			}
			PreparedStatement biditem = databaseConnection.prepareStatement(SELECT_BID_FROM_LOG);
			biditem.setInt(1, Integer.parseInt(bidItemId));
			biditem.setFloat(2, Float.parseFloat(bidItemDetails.getPrice()));
			ResultSet biditems = biditem.executeQuery();
			while (biditems.next()) {
				bidItemDetails.setBidItemId(Integer.toString(biditems.getInt(DB_BID_ITEM_ID)));
				bidItemDetails.setApplicantId(Integer.toString(biditems.getInt(DB_APPLICANT_ID)));
				bidItemDetails.setPrice(Float.toString(biditems.getFloat(DB_PRICE)));
				bidItemDetails.setTime(biditems.getTimestamp(DB_TIME));
			}
			databaseConnection.commit();
		} catch (Exception e) {
			try {
				databaseConnection.rollback();
			} catch (Exception e1) {
				throw e1;
			}
			throw e;
		} finally {
			config.closeConnection(databaseConnection);
		}
		return bidItemDetails;
	}

	public BidItem retrieveDetailFromBase(String bidItemId) throws Exception {
		Connection databaseConnection = config.getConnection();
		BidItem bidItemDetails = new BidItem();

		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement biditem = databaseConnection.prepareStatement(SELECT_ITEM_FROM_BASE);
			biditem.setInt(1, Integer.parseInt(bidItemId));
			ResultSet biditems = biditem.executeQuery();
			while (biditems.next()) {
				bidItemDetails.setBidItemId(Integer.toString(biditems.getInt(DB_BID_ITEM_ID)));
				bidItemDetails.setApplicantId(Integer.toString(biditems.getInt(DB_BIDDER_ID)));
				bidItemDetails.setPrice(Float.toString(biditems.getFloat(DB_PRICE)));
			}
			databaseConnection.commit();
		} catch (Exception e) {
			try {
				databaseConnection.rollback();
			} catch (Exception e1) {
				throw e1;
			}
			throw e;
		} finally {
			config.closeConnection(databaseConnection);
		}
		return bidItemDetails;
	}
}
