package io.ztech.onlinebidding.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;

public class UserPage {
	enum BiddingOption {
		BUY, SELL, EXIT;
	}

	public static Logger logger = Logger.getLogger("UserPage");
	public static Scanner input = new Scanner(System.in);
	public String choice;
	BuyAnItem buyItem = new BuyAnItem();
	SellAnItem sellItem = new SellAnItem();

	public void customerOptions(String userName) {
		try {
			logger.info(ConstantDisplayStatement.WELCOME_USER_PAGE + userName);
			logger.info(ConstantDisplayStatement.CUSTOMER_OPTIONS);
			choice = input.nextLine();
			choice = choice.toUpperCase();
			switch (BiddingOption.valueOf(choice)) {
			case BUY:
				buyItem.buy(userName);
				break;
			case SELL:
				sellItem.sell(userName);
				break;
			default:
				logger.info(ConstantDisplayStatement.LOGOUT);
			}
		} catch (Exception e) {
			logger.info(ConstantDisplayStatement.INVALID_LOGIN_OPTION);
			customerOptions(userName);
		}
	}
}
