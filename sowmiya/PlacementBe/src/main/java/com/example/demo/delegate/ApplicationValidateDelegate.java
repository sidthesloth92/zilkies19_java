package com.example.demo.delegate;

import java.sql.SQLException;
import java.time.LocalDate;

import com.example.demo.beans.Company;
import com.example.demo.dao.ApplicationValidateDao;


public class ApplicationValidateDelegate {
	ApplicationValidateDao validateDao = new ApplicationValidateDao();

	public LocalDate checkDate(Company company) {
		return validateDao.getDate(company);
	}

	public boolean checkIsApplied(Company company, String reg_no) throws SQLException {
		return validateDao.checkIsApplied(company, reg_no);
	}


}
