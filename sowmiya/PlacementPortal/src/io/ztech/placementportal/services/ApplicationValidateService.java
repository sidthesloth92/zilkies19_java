package io.ztech.placementportal.services;

import java.sql.SQLException;
import java.time.LocalDate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.delegate.ApplicationValidateDelegate;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

public class ApplicationValidateService {
	ApplicationValidateDelegate validateDelegate = new ApplicationValidateDelegate();
	RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();

	public boolean checkDate(LocalDate localDate, Company company) {
		LocalDate last_date = validateDelegate.checkDate(company);
		if (localDate.isAfter(last_date)) {
			return false;
		} else
			return true;
	}

	public boolean checkeligible(Company company, String reg_no) throws SQLException {
		Marks student_mark;
		Eligiblity eligible_mark;
		student_mark = retrieveDelegate.getMarkDetail(reg_no);
		eligible_mark = retrieveDelegate.getEligiblityDetail(company);
		if (student_mark.getMarkX() >= eligible_mark.getMarkX()
				&& student_mark.getMarkXII()>= eligible_mark
						.getMarkXII()
				&& student_mark.getCgpa() >= eligible_mark.getCgpa()
				&& student_mark.getArrearCount() <= eligible_mark.getArrearCount()) {
			return true;

		}
		return false;
	}

	public boolean alreadyApplied(Company company, String reg_no) {
		return validateDelegate.checkIsApplied(company, reg_no);
	}

}
