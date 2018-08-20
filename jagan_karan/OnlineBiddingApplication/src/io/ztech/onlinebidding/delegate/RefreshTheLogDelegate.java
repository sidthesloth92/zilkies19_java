package io.ztech.onlinebidding.delegate;

import java.util.ArrayList;

import io.ztech.onlinebidding.dao.ClosedBidRetrieval;
import io.ztech.onlinebidding.dao.RemoveFromLog;

public class RefreshTheLogDelegate {
	ClosedBidRetrieval closedBidItems = new ClosedBidRetrieval();
	ArrayList<Integer> closedBidItem = new ArrayList<Integer>();
	ArrayList<Integer> bidIdFromFinalLog = new ArrayList<Integer>();
	RemoveFromLog removeBidFromLog = new RemoveFromLog();

	public void refreshTheLog() throws Exception {
		try {
			closedBidItem = closedBidItems.closedBidIdRetrieval();
			bidIdFromFinalLog = closedBidItems.bidIdRetrievalFromFinalLog();
			ArrayList<Integer> commonBidId = new ArrayList<Integer>(closedBidItem);
			ArrayList<Integer> allBidId = new ArrayList<Integer>(closedBidItem);

			allBidId.addAll(bidIdFromFinalLog);
			commonBidId.retainAll(bidIdFromFinalLog);
			allBidId.removeAll(commonBidId);
			closedBidItems.AddClosedBidToLog(allBidId);
			removeBidFromLog.removeBidFromLog(allBidId);
		} catch (Exception e) {
			throw e;
		}
	}
}
