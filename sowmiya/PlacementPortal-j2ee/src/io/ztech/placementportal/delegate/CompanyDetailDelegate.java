package io.ztech.placementportal.delegate;

import java.sql.SQLException;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.dao.AddDetailsDao;

public class CompanyDetailDelegate {

	public boolean addCompanyDetail(Company company) throws SQLException {
		AddDetailsDao addDao = new AddDetailsDao();
		return addDao.addCompanyDetail(company);
	}

}
