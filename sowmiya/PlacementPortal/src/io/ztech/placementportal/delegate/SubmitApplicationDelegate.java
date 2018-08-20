package io.ztech.placementportal.delegate;

import java.sql.SQLException;
import java.time.LocalDate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.dao.SubmitApplicationDao;

public class SubmitApplicationDelegate {

	public boolean completeRegistration(Company company, String reg_no, LocalDate date) throws SQLException {
		SubmitApplicationDao submitDao = new SubmitApplicationDao();
		return submitDao.submitApplication(company, reg_no, date);
	}

}
