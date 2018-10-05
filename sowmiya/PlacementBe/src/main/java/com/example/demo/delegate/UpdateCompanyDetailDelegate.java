package com.example.demo.delegate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.doa.UpdateCompanyDetailDao;

public class UpdateCompanyDetailDelegate {
	UpdateCompanyDetailDao updateDao = new UpdateCompanyDetailDao();

	public boolean updateCompany(CompanyController company) {
		return updateDao.updateCompany(company);
	}

}
