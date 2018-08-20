package io.ztech.onlinebidding.delegate;

import java.util.HashMap;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.dao.BidItemDetailRetrieval;
import io.ztech.onlinebidding.dao.CategoryRetrieval;
import io.ztech.onlinebidding.dao.ItemRetrieval;

public class DisplayItemCategoryDetailsDelegate {
	HashMap<String, String> categoryList = new HashMap<String, String>();
	HashMap<String, String> itemList = new HashMap<String, String>();
	HashMap<String, BidItem> bidItemList = new HashMap<String, BidItem>();

	CategoryRetrieval categoryRetrieve = new CategoryRetrieval();
	ItemRetrieval itemRetrieve = new ItemRetrieval();
	BidItemDetailRetrieval bidItemRetrieve = new BidItemDetailRetrieval();
	BidItem bidItemDetail = new BidItem();

	public HashMap<String, String> getCategoryDetails() throws Exception {
		try {
			categoryList = categoryRetrieve.retreiveCategory();
		} catch (Exception e) {
			throw e;
		}
		return categoryList;
	}

	public HashMap<String, String> getItemDetails(String categoryId) throws Exception {
		try {
			itemList = itemRetrieve.retreiveItem(categoryId);
		} catch (Exception e) {
			throw e;
		}
		return itemList;
	}

	public HashMap<String, BidItem> getBidItemDetails(String itemId, String categoryId) throws Exception {
		try {
			bidItemList = bidItemRetrieve.retrieveBidItemDetails(itemId, categoryId);
		} catch (Exception e) {
			throw e;
		}
		return bidItemList;
	}

	public BidItem getBidItemDetails(String bidItemId) throws Exception {
		try {
			bidItemDetail = bidItemRetrieve.retrieveBidItemDetails(bidItemId);
		} catch (Exception e) {
			throw e;
		}
		return bidItemDetail;
	}
}
