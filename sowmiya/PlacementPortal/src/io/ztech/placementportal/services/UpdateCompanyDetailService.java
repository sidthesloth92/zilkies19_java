package io.ztech.placementportal.services;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.delegate.UpdateCompanyDetailDelegate;

public class UpdateCompanyDetailService {
   UpdateCompanyDetailDelegate companyDelegate=new UpdateCompanyDetailDelegate();
	public boolean updateCompanyDetail(Company company) {
		return companyDelegate.updateCompany(company);
	}

}
