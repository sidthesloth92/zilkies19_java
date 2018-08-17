package io.ztech.onlinebidding.services;

import java.util.HashMap;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.delegate.DisplayItemCategoryDetailsDelegate;

public class DisplayItemCategoryDetailsService {
	DisplayItemCategoryDetailsDelegate itemCategoryDetail = new DisplayItemCategoryDetailsDelegate();
	HashMap<String, String> categoryList = new HashMap<String, String>();
	HashMap<String, String> itemList = new HashMap<String, String>();
	HashMap<String, BidItem> bidItemList = new HashMap<String, BidItem>();
	BidItem bidItemDetail=new BidItem();
	public HashMap<String, String> getCategoryDetail() {
		categoryList = itemCategoryDetail.getCategoryDetails();
		return categoryList;
	}

	public HashMap<String, String> getItemDetail(String categoryId) {
		itemList = itemCategoryDetail.getItemDetails(categoryId);
		return itemList;
	}

	public HashMap<String, BidItem> getBidItemDetails(String itemId, String categoryId) {
		bidItemList = itemCategoryDetail.getBidItemDetails(itemId, categoryId);
		return bidItemList;
	}

	public BidItem getBidItemDetails(String bidItemId) {
		bidItemDetail=itemCategoryDetail.getBidItemDetails(bidItemId);
		return bidItemDetail;
	}
}
