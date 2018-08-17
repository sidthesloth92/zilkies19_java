package io.ztech.placementportal.services;

import java.time.LocalDate;
import java.util.HashMap;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.ApplicationConstants;
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

	public boolean checkeligible(Company company, String reg_no) {
		HashMap<String, Float> student_mark, eligible_mark;
		student_mark = retrieveDelegate.getMarkDetail(reg_no);
		eligible_mark = retrieveDelegate.getEligiblityDetail(company);
		if (student_mark.get(ApplicationConstants.PERCENTAGE_X) >= eligible_mark.get(ApplicationConstants.PERCENTAGE_X)
				&& student_mark.get(ApplicationConstants.PERCENTAGE_XII) >= eligible_mark
						.get(ApplicationConstants.PERCENTAGE_XII)
				&& student_mark.get(ApplicationConstants.CGPA) >= eligible_mark.get(ApplicationConstants.CGPA)
				&& student_mark.get(ApplicationConstants.ARREAR_COUNT) <= eligible_mark
						.get(ApplicationConstants.ARREAR_COUNT)) {
			return true;

		}
		return false;
	}

	public boolean alreadyApplied(Company company, String reg_no) {
		return validateDelegate.checkIsApplied(company,reg_no);
	}

	

}
