package io.ztech.onlinebidding.ui;

import java.util.HashMap;
import java.util.logging.Logger;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.constant.BidderConstants;
import io.ztech.onlinebidding.services.DisplayItemCategoryDetailsService;

public class DisplayItemAndCategory implements BidderConstants {
	HashMap<String, String> category = new HashMap<String, String>();
	HashMap<String, String> item = new HashMap<String, String>();
	HashMap<String, BidItem> bidItemList = new HashMap<String, BidItem>();
	BidItem bidItem = new BidItem();
	public static Logger logger = Logger.getLogger("DisplayItemAndCategory");

	DisplayItemCategoryDetailsService displayItemCategoryService = new DisplayItemCategoryDetailsService();

	public void displayCategory() {
		category = displayItemCategoryService.getCategoryDetail();
		category.forEach((key, value) -> logger.info(key + ":" + value));
	}

	public void displayItems(String categoryId) {
		item = displayItemCategoryService.getItemDetail(categoryId);
		item.forEach((key, value) -> logger.info(key + ":" + value));

	}

	public boolean displayBidItems(String categoryId, String itemId) {
		bidItemList = displayItemCategoryService.getBidItemDetails(itemId, categoryId);
		if (bidItemList.isEmpty()) {
			return false;
		} else {
			bidItemList.forEach((key,
					value) -> logger.info(CON_BID_ITEM_ID + key + CON_BIDDER_ID + value.getBidderId() + CON_CATEGORY_ID
							+ value.getCategoryId() + CON_ITEM_ID + value.getItemId() + CON_ITEM_NAME
							+ value.getItemName() + CON_PRICE + value.getPrice() + CON_STARTTIME + value.getStarttime()
							+ CON_ENDTIME + value.getEndtime()));
			return true;
		}
	}

	public boolean displayBidItems(String bidItemId) {
		bidItem = displayItemCategoryService.getBidItemDetails(bidItemId);
		if (bidItem.getApplicantId() == null) {
			return false;
		} else {
			logger.info(CON_BID_ITEM_ID + bidItem.getBidItemId() + CON_APPLICANT_ID + bidItem.getApplicantId()
					+ CON_CURRENT_PRICE + bidItem.getPrice() + CON_LAST_BID_TIME + bidItem.getTime());
			return true;
		}

	}

}
