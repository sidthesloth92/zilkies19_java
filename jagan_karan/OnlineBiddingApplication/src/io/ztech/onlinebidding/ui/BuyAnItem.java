package io.ztech.onlinebidding.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.services.BidAnItem;
import io.ztech.onlinebidding.utils.GetItemCategoryId;

public class BuyAnItem {
	public static Logger logger = Logger.getLogger("BuyAnItem");
	Scanner input = new Scanner(System.in);
	BidAnItem biddingAnItem = new BidAnItem();
	GetItemCategoryId getId = new GetItemCategoryId();
	boolean detailsDisplayed = true;
	public String bidItemId, comment;

	public void buy(String userName) {
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
			detailsDisplayed = displayDetails.displayBidItems(bidItemId);
			if (detailsDisplayed == true) {
				confirmBid(userName);
			} else {
				logger.info(ConstantDisplayStatement.NEW_BID);
				confirmBid(userName);
			}
		} else {
			logger.info(ConstantDisplayStatement.NO_ITEM);
		}
		UserPage user = new UserPage();
		user.customerOptions(userName);
	}

	public void confirmBid(String userName) {
		logger.info(ConstantDisplayStatement.INTERESTED_TO_BUY);
		String option = input.nextLine();
		if (option.equals("1")) {
			logger.info(ConstantDisplayStatement.BID_ITEM_ID);
			bidItemId = input.nextLine();
			logger.info(ConstantDisplayStatement.CONFIRM_BID);
			String confirmOption = input.nextLine();
			if (confirmOption.equals("1")) {
				comment = biddingAnItem.bid(bidItemId, userName);
				logger.info(comment);
			}
		} else {
			logger.info(ConstantDisplayStatement.NOT_INTERESTED);
		}
	}
}
