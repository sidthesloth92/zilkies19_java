package io.ztech.onlinebidding.services;

import java.util.HashMap;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.delegate.DisplayItemCategoryDetailsDelegate;
import io.ztech.onlinebidding.ui.DisplayException;

public class DisplayItemCategoryDetailsService {
	DisplayItemCategoryDetailsDelegate itemCategoryDetail = new DisplayItemCategoryDetailsDelegate();
	HashMap<String, String> categoryList = new HashMap<String, String>();
	HashMap<String, String> itemList = new HashMap<String, String>();
	HashMap<String, BidItem> bidItemList = new HashMap<String, BidItem>();
	BidItem bidItemDetail = new BidItem();

	public HashMap<String, String> getCategoryDetail() {
		try {
			categoryList = itemCategoryDetail.getCategoryDetails();
		} catch (Exception e) {
			DisplayException.displayException();
		}
		return categoryList;

	}

	public HashMap<String, String> getItemDetail(String categoryId) {
		try {
			itemList = itemCategoryDetail.getItemDetails(categoryId);
		} catch (Exception e) {
			DisplayException.displayException();
		}
		return itemList;
	}

	public HashMap<String, BidItem> getBidItemDetails(String itemId, String categoryId) {
		try {
			bidItemList = itemCategoryDetail.getBidItemDetails(itemId, categoryId);
		} catch (Exception e) {
			DisplayException.displayException();
		}
		return bidItemList;
	}

	public BidItem getBidItemDetails(String bidItemId) {
		try {
			bidItemDetail = itemCategoryDetail.getBidItemDetails(bidItemId);
		} catch (Exception e) {
			DisplayException.displayException();
		}
		return bidItemDetail;
	}
}
