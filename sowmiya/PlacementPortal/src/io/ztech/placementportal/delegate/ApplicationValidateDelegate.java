package io.ztech.placementportal.delegate;

import java.time.LocalDate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.dao.ApplicationValidateDao;

public class ApplicationValidateDelegate {
	ApplicationValidateDao validateDao = new ApplicationValidateDao();

	public LocalDate checkDate(Company company) {
		return validateDao.getDate(company);
	}

	public boolean checkIsApplied(Company company, String reg_no) {
		return validateDao.checkIsApplied(company,reg_no);
	}

}
