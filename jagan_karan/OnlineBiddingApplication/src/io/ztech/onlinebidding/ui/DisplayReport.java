package io.ztech.onlinebidding.ui;

import java.util.HashMap;
import java.util.logging.Logger;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.constant.BidderConstants;
import io.ztech.onlinebidding.constant.ConstantDisplayStatement;

public class DisplayReport implements BidderConstants {
	public static Logger logger = Logger.getLogger("DisplayReport");

	public void displaySoldReport(HashMap<String, BidItem> bidItemDetail) {
		logger.info(ConstantDisplayStatement.SOLD_BID_ITEM_REPORT);
		bidItemDetail.forEach((key, value) -> logger.info(
				CON_BID_ITEM_ID + key + CON_APPLICANT_ID + value.getApplicantId() + CON_PRICE + value.getPrice()));
	}

	public void displayUnsoldReport(HashMap<String, BidItem> bidItemDetail) {
		logger.info(ConstantDisplayStatement.UNSOLD_BID_ITEM_REPORT);
		bidItemDetail.forEach((key, value) -> logger.info(
				CON_BID_ITEM_ID + key + CON_APPLICANT_ID + value.getApplicantId() + CON_PRICE + value.getPrice()));
	}
}
