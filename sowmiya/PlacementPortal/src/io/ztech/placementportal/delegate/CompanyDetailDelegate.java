package io.ztech.placementportal.delegate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.dao.AddDetailsDao;

public class CompanyDetailDelegate {

	public boolean addCompanyDetail(Company company, Eligiblity eligible) {
		AddDetailsDao addDao = new AddDetailsDao();
		return addDao.addCompanyDetail(company, eligible);
	}

}
