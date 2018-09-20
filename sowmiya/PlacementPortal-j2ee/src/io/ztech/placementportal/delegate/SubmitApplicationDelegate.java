package io.ztech.placementportal.delegate;

import java.sql.SQLException;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.dao.SubmitApplicationDao;

public class SubmitApplicationDelegate {

	public boolean completeRegistration(Company company, Student studentDetail, String currentDate) throws SQLException {
		SubmitApplicationDao submitDao = new SubmitApplicationDao();
		if(company.getCgpa()<=studentDetail.getCgpa()&&
		company.getMarkX()<=studentDetail.getMarkX()&&
		company.getMarkXII()<=studentDetail.getMarkXII()&&
		company.getArrearCount()>=studentDetail.getArrearCount()) {
		return submitDao.submitApplication(company, studentDetail, currentDate);
	}
		return false;

}
}
