package io.ztech.onlinebidding.services;

import io.ztech.onlinebidding.delegate.SellAnItemDelegate;
import io.ztech.onlinebidding.ui.DisplayException;

public class SellAnItemDetails {
	SellAnItemDelegate sellItemsDelegate = new SellAnItemDelegate();

	public void sellItem(String username, String itemId, String categoryId, String itemName, String price,
			String startTime, String endTime) {
		try {
			int bidderid = sellItemsDelegate.insertBidder(username);
			sellItemsDelegate.insertBidItemLog(bidderid, itemId, categoryId, itemName, price, startTime, endTime);
		} catch (Exception e) {
			DisplayException.displayException();
		}
	}
}
