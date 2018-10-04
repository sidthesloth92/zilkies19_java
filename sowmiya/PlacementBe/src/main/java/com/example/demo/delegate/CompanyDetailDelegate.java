package com.example.demo.delegate;

import java.util.logging.Logger;

import com.example.demo.beans.Company;
import com.example.demo.dao.AddDetailsDao;
import com.example.demo.dao.DeleteDetailsDao;
import com.example.demo.dao.UpdateCompanyDetailDao;

public class CompanyDetailDelegate {
	private Logger log;

	public CompanyDetailDelegate() {
		log = Logger.getLogger("CompanyDetailDelegate.class");
	}

	public boolean addCompanyDetail(Company company) {
		try {
			AddDetailsDao addDao = new AddDetailsDao();
			return addDao.addCompanyDetail(company);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		return false;
	}

	public boolean updateCompany(Company company) {
		try {
			UpdateCompanyDetailDao updateDao = new UpdateCompanyDetailDao();
			return updateDao.updateCompany(company);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		return false;
	}

	public boolean deleteCompany(String id) {
		try {
			DeleteDetailsDao deleteDao = new DeleteDetailsDao();
			return deleteDao.deleteCompany(id);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		return false;
	}

}
