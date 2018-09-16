package io.ztech.placementportal.delegate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.dao.UpdateCompanyDetailDao;

public class UpdateCompanyDetailDelegate {
	UpdateCompanyDetailDao updateDao = new UpdateCompanyDetailDao();

	public boolean updateCompany(Company company) {
		return updateDao.updateCompany(company);
	}

}
