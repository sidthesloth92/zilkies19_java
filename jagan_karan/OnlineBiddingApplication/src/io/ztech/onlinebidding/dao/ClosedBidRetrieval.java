package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class ClosedBidRetrieval implements SqlQueries, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();
	ArrayList<Integer> closedBidItem = new ArrayList<Integer>();
	ArrayList<Integer> bidIdFromFinalLog = new ArrayList<Integer>();
	BidItemDetailRetrieval bidItemretrieve = new BidItemDetailRetrieval();
	BidItem bidItemDetails = new BidItem();

	public ArrayList<Integer> closedBidIdRetrieval() throws Exception {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			PreparedStatement selectEndBids = databaseConnection.prepareStatement(SELECT_ENDED_BID_ID);
			ResultSet endBid = selectEndBids.executeQuery();
			while (endBid.next()) {
				closedBidItem.add(endBid.getInt(DB_BID_ITEM_ID));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
		return closedBidItem;

	}

	public ArrayList<Integer> bidIdRetrievalFromFinalLog() throws Exception {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			PreparedStatement selectBidId = databaseConnection.prepareStatement(SELECT_BID_ITEM_FROM_FINAL_LOG);
			ResultSet bidId = selectBidId.executeQuery();
			while (bidId.next()) {
				bidIdFromFinalLog.add(bidId.getInt(DB_BID_ITEM_ID));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
		return bidIdFromFinalLog;

	}

	public void AddClosedBidToLog(ArrayList<Integer> closedBid) throws Exception {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			Iterator<Integer> closedBidIterator = closedBid.iterator();
			while (closedBidIterator.hasNext()) {
				String bidId = Integer.toString(closedBidIterator.next());
				bidItemDetails = bidItemretrieve.retrieveBidItemDetails(bidId);
				if (bidItemDetails.getApplicantId() == null) {
					bidItemDetails = bidItemretrieve.retrieveDetailFromBase(bidId);
					PreparedStatement insertClosedBidToLog = databaseConnection.prepareStatement(INSERT_FINAL_LOG);
					insertClosedBidToLog.setInt(1, Integer.parseInt(bidItemDetails.getBidItemId()));
					insertClosedBidToLog.setInt(2, Integer.parseInt(bidItemDetails.getApplicantId()));
					insertClosedBidToLog.setFloat(3, Float.parseFloat(bidItemDetails.getPrice()));
					insertClosedBidToLog.setString(4, DB_UNSOLD);
					insertClosedBidToLog.executeUpdate();
				} else {
					PreparedStatement insertClosedBidToLog = databaseConnection.prepareStatement(INSERT_FINAL_LOG);
					insertClosedBidToLog.setInt(1, Integer.parseInt(bidItemDetails.getBidItemId()));
					insertClosedBidToLog.setInt(2, Integer.parseInt(bidItemDetails.getApplicantId()));
					insertClosedBidToLog.setFloat(3, Float.parseFloat(bidItemDetails.getPrice()));
					insertClosedBidToLog.setString(4, DB_SOLD);
					insertClosedBidToLog.executeUpdate();
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
	}
}
