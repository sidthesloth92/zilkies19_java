package io.ztech.onlinebidding.services;

import java.util.HashMap;

import io.ztech.onlinebidding.bean.BidItem;
import io.ztech.onlinebidding.delegate.BidReportDelegate;
import io.ztech.onlinebidding.ui.AdminPage;
import io.ztech.onlinebidding.ui.DisplayException;
import io.ztech.onlinebidding.ui.DisplayReport;

public class BidReport {
	BidReportDelegate report = new BidReportDelegate();
	HashMap<String, BidItem> bidItemDetail = new HashMap<String, BidItem>();
	DisplayReport displayItem = new DisplayReport();

	public void bidSoldReport() {
		try {
			bidItemDetail = report.bidSoldReport();
			displayItem.displaySoldReport(bidItemDetail);
			AdminPage adminPage = new AdminPage();
			adminPage.AdminOption();
		} catch (Exception e) {
			DisplayException.displayException();
		}
	}

	public void bidUnsoldReport() {
		try {
			bidItemDetail = report.bidUnsoldReport();
			displayItem.displayUnsoldReport(bidItemDetail);
			AdminPage adminPage = new AdminPage();
			adminPage.AdminOption();
		} catch (Exception e) {
			DisplayException.displayException();
		}
	}
}
