package io.ztech.onlinebidding.services;

import java.util.HashMap;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.delegate.BidReportDelegate;
import io.ztech.onlinebidding.ui.AdminPage;
import io.ztech.onlinebidding.ui.DisplayReport;

public class BidReport {
	BidReportDelegate report = new BidReportDelegate();
	HashMap<String, BidItem> bidItemDetail = new HashMap<String, BidItem>();
	DisplayReport displayItem = new DisplayReport();

	public void bidSoldReport() {
		bidItemDetail = report.bidSoldReport();
		displayItem.displaySoldReport(bidItemDetail);
		AdminPage adminPage = new AdminPage();
		adminPage.AdminOption();
	}

	public void bidUnsoldReport() {
		bidItemDetail = report.bidUnsoldReport();
		displayItem.displayUnsoldReport(bidItemDetail);
		AdminPage adminPage = new AdminPage();
		adminPage.AdminOption();

	}
}
