package io.ztech.onlinebidding.services;

import io.ztech.onlinebidding.delegate.RefreshTheLogDelegate;
import io.ztech.onlinebidding.ui.AdminPage;

public class RefreshTheBidLog {
	RefreshTheLogDelegate refreshLog = new RefreshTheLogDelegate();

	public void refreshBidLog() {
		refreshLog.refreshTheLog();
		AdminPage adminPage=new AdminPage();
		adminPage.AdminOption();
	}
}
