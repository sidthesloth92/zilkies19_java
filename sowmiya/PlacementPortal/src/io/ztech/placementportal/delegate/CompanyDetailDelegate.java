package io.ztech.placementportal.delegate;

import java.sql.SQLException;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.dao.AddDetailsDao;

public class CompanyDetailDelegate {

	public void addCompanyDetail(Company company, Eligiblity eligible) throws SQLException {
		AddDetailsDao addDao = new AddDetailsDao();
		addDao.addCompanyDetail(company, eligible);
	}

}
