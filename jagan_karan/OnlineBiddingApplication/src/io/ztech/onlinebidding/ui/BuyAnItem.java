package io.ztech.onlinebidding.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.delegate.BidAnItemDelegate;
import io.ztech.onlinebidding.services.BidAnItem;
import io.ztech.onlinebidding.utils.GetItemCategoryId;

public class BuyAnItem {
	public static Logger logger = Logger.getLogger("BuyAnItem");
	Scanner input = new Scanner(System.in);
	BidAnItem biddingAnItem = new BidAnItem();
	GetItemCategoryId getId = new GetItemCategoryId();
	boolean detailsDisplayed = true;
	public String bidItemId, comment;
	ArrayList<String> previousPresentBidItemId = new ArrayList<String>();
	BidAnItemDelegate bid = new BidAnItemDelegate();

	public void buy(String userName) throws Exception {
		logger.info(ConstantDisplayStatement.BID_AN_ITEM);
		DisplayItemAndCategory displayDetails = new DisplayItemAndCategory();
		displayDetails.displayCategory();
		String categoryId = getId.getId(ConstantDisplayStatement.CATEGORY_ID);
		displayDetails.displayItems(categoryId);
		String itemId = getId.getId(ConstantDisplayStatement.ITEM_ID);
		detailsDisplayed = displayDetails.displayBidItems(categoryId, itemId);
		if (detailsDisplayed == true) {
			logger.info(ConstantDisplayStatement.SELECT_BID_TO_DISPLAY_CURRENT_STATUS);
			bidItemId = input.nextLine();
			previousPresentBidItemId = bid.presentBidItemId(itemId, categoryId);
			if (previousPresentBidItemId.contains(bidItemId)) {
				detailsDisplayed = displayDetails.displayBidItems(bidItemId);
				if (detailsDisplayed == true) {
					confirmBid(userName, itemId, categoryId);
				} else {
					logger.info(ConstantDisplayStatement.NEW_BID);
					confirmBid(userName, itemId, categoryId);
				}
			} else {
				logger.info(ConstantDisplayStatement.NOT_PRESENT_IN_SELECTED_CATEGORY);
			}
		} else {
			logger.info(ConstantDisplayStatement.NO_ITEM);
		}
		UserPage user = new UserPage();
		user.customerOptions(userName);
	}

	public void confirmBid(String userName, String itemId, String categoryId) throws Exception {
		logger.info(ConstantDisplayStatement.INTERESTED_TO_BUY);
		String option = input.nextLine();
		if (option.equals("1")) {
			logger.info(ConstantDisplayStatement.BID_ITEM_ID);
			bidItemId = input.nextLine();
			previousPresentBidItemId = bid.presentBidItemId(itemId, categoryId);
			if (previousPresentBidItemId.contains(bidItemId)) {
				logger.info(ConstantDisplayStatement.CONFIRM_BID);
				String confirmOption = input.nextLine();
				if (confirmOption.equals("1")) {
					comment = biddingAnItem.bid(bidItemId, userName);
					logger.info(comment);
				}
			} else {
				logger.info(ConstantDisplayStatement.NOT_PRESENT_IN_SELECTED_CATEGORY);
			}
		} else {
			logger.info(ConstantDisplayStatement.NOT_INTERESTED);
		}
	}
}
