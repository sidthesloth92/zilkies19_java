package io.zilker.application.utils;

import io.zilker.application.dao.AdminDAO;

public class StatusCheck {
	AdminDAO adminDAO = new AdminDAO();

	public void statusCheck() throws Exception {
		adminDAO.dailyStatusCheck();
	}
}
