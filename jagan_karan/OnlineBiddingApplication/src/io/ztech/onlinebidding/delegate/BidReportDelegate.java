package io.ztech.onlinebidding.delegate;

import java.util.HashMap;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.dao.BidItemFromFinalLog;

public class BidReportDelegate {
	BidItemFromFinalLog bidItem = new BidItemFromFinalLog();
	HashMap<String, BidItem> bidItemDetail = new HashMap<String, BidItem>();

	public HashMap<String, BidItem> bidSoldReport() {
		bidItemDetail = bidItem.retrieveSoldBidItemDetails();
		return bidItemDetail;

	}

	public HashMap<String, BidItem> bidUnsoldReport() {
		bidItemDetail = bidItem.retrieveUnsoldBidItemDetails();
		return bidItemDetail;

	}

}
