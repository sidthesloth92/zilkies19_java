package io.ztech.onlinebidding.services;

import io.ztech.onlinebidding.delegate.RefreshTheLogDelegate;
import io.ztech.onlinebidding.ui.AdminPage;
import io.ztech.onlinebidding.ui.DisplayException;

public class RefreshTheBidLog {
	RefreshTheLogDelegate refreshLog = new RefreshTheLogDelegate();

	public void refreshBidLog() {
		try {
			refreshLog.refreshTheLog();
			AdminPage adminPage = new AdminPage();
			adminPage.AdminOption();
		} catch (Exception e) {
			DisplayException.displayException();
		}
	}
}
