package io.ztech.onlinebidding.delegate;

import java.util.ArrayList;

import io.ztech.onlinebidding.dao.ClosedBidRetrieval;
import io.ztech.onlinebidding.dao.RemoveFromLog;

public class RefreshTheLogDelegate {
	ClosedBidRetrieval closedBidItems = new ClosedBidRetrieval();
	ArrayList<Integer> closedBidItem = new ArrayList<Integer>();
	RemoveFromLog removeBidFromLog = new RemoveFromLog();

	public void refreshTheLog() {
		closedBidItem = closedBidItems.closedBidIdRetrieval();
		closedBidItems.AddClosedBidToLog(closedBidItem);
		removeBidFromLog.removeBidFromLog(closedBidItem);

	}
}
