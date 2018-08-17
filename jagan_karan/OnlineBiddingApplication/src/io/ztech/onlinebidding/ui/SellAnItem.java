package io.ztech.onlinebidding.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.services.SellAnItemDetails;
import io.ztech.onlinebidding.utils.FetchUserDetails;
import io.ztech.onlinebidding.utils.GetItemCategoryId;

public class SellAnItem implements ConstantDisplayStatement {
	public static Logger logger = Logger.getLogger("BuyAnItem");
	Scanner input = new Scanner(System.in);
	GetItemCategoryId getId = new GetItemCategoryId();
	SellAnItemDetails sellItemService = new SellAnItemDetails();
	FetchUserDetails fetchSellItemDetails = new FetchUserDetails();

	public void sell(String username) {
		logger.info(SELL_AN_ITEM);
		DisplayItemAndCategory display = new DisplayItemAndCategory();
		display.displayCategory();
		String categoryId = getId.getId(CATEGORY_ID);
		display.displayItems(categoryId);
		String itemId = getId.getId(ITEM_ID);
		logger.info(ALREADY_PRESENT_BID);
		display.displayBidItems(categoryId, itemId);
		logger.info(ENTER_SELLING_ITEM_DETAILS);
		String itemName = fetchSellItemDetails.fetchdetails(NAME_REGEX, ENTER_ITEM_NAME, INVALID_ITEM);
		String price = fetchSellItemDetails.fetchdetails(FLOAT_REGEX, ENTER_THE_PRICE, INVALID_PRICE);
		String startDate = fetchSellItemDetails.fetchdetails(TIME_STAMP_REGEX, ENTER_THE_START_DATE, INVALID_STARTDATE);
		String endDate = fetchSellItemDetails.fetchdetails(TIME_STAMP_REGEX, ENTER_THE_END_DATE, INVALID_ENDDATE);
		sellItemService.sellItem(username, itemId, categoryId, itemName, price, startDate, endDate);
		UserPage user = new UserPage();
		user.customerOptions(username);
	}

}
