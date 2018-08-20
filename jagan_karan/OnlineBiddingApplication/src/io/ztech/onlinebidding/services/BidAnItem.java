package io.ztech.onlinebidding.services;

import java.util.logging.Logger;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.delegate.BidAnItemDelegate;
import io.ztech.onlinebidding.ui.DisplayException;

public class BidAnItem {
	public static Logger logger = Logger.getLogger("BidAnItem");
	BidAnItemDelegate bidItem = new BidAnItemDelegate();
	boolean bidItemAvailableNow = true;
	boolean bidTimeAvailableNow = true;
	boolean applicantValid = true;

	public String bid(String bidItemId, String username) throws Exception {
		try {
			applicantValid = bidItem.checkApplicant(bidItemId, username);
			if (applicantValid == true) {
				bidItemAvailableNow = bidItem.checkAvailable(bidItemId, username);
				if (bidItemAvailableNow == true) {
					bidTimeAvailableNow = bidItem.checkTimeAvailable(bidItemId, username);
					if (bidTimeAvailableNow == true) {
						bidItem.calculate(bidItemId, username);
						return ConstantDisplayStatement.ITEM_IS_BID;
					} else
						return ConstantDisplayStatement.INVALID_TIMING;
				} else {
					return ConstantDisplayStatement.INVALID_BIDDER_ITEM;
				}
			} else {
				return ConstantDisplayStatement.INVALID_BUYER;
			}
		} catch (Exception e) {
			DisplayException.displayException();
		}
		return null;
	}
}
