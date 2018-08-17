package io.ztech.onlinebidding.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.services.BidReport;
import io.ztech.onlinebidding.services.RefreshTheBidLog;

public class AdminPage {
	enum AdminOptionList {
		REFRESH, SOLDREPORT, UNSOLDREPORT, EXIT;
	}

	public static Logger logger = Logger.getLogger("AdminPage");
	public static Scanner input = new Scanner(System.in);
	public String choice;
	RefreshTheBidLog refreshBidLog = new RefreshTheBidLog();
	BidReport bidReport = new BidReport();

	public void AdminOption() {

		try {
			logger.info(ConstantDisplayStatement.WELCOME_ADMIN_PAGE);
			logger.info(ConstantDisplayStatement.ADMIN_OPTIONS);
			choice = input.nextLine();
			choice = choice.toUpperCase();
			switch (AdminOptionList.valueOf(choice)) {
			case REFRESH:
				refreshBidLog.refreshBidLog();
				break;
			case SOLDREPORT:
				bidReport.bidSoldReport();
				break;
			case UNSOLDREPORT:
				bidReport.bidUnsoldReport();
				break;
			default:
				logger.info(ConstantDisplayStatement.LOGOUT);
				break;
			}
		} catch (Exception e) {
			logger.info(ConstantDisplayStatement.INVALID_LOGIN_OPTION);
			AdminOption();
		}
	}
}
