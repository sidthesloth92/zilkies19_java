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

	public HashMap<String, String> getCategoryDetails() {
		categoryList = categoryRetrieve.retreiveCategory();
		return categoryList;
	}

	public HashMap<String, String> getItemDetails(String categoryId) {
		itemList = itemRetrieve.retreiveItem(categoryId);
		return itemList;
	}

	public HashMap<String, BidItem> getBidItemDetails(String itemId, String categoryId) {
		bidItemList = bidItemRetrieve.retrieveBidItemDetails(itemId, categoryId);
		return bidItemList;
	}

	public BidItem getBidItemDetails(String bidItemId) {
		bidItemDetail = bidItemRetrieve.retrieveBidItemDetails(bidItemId);
		return bidItemDetail;
	}
}
