package io.ztech.placementportal.services;

import java.sql.SQLException;
import java.time.LocalDate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.delegate.SubmitApplicationDelegate;

public class SubmitApplicationService {

	public boolean completeRegistration(Company company, String reg_no) throws SQLException {
		SubmitApplicationDelegate submitDelegate = new SubmitApplicationDelegate();
		LocalDate date = java.time.LocalDate.now();
		return submitDelegate.completeRegistration(company, reg_no, date);

	}

}
