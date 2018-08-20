package io.ztech.placementportal.services;

import java.sql.SQLException;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.delegate.CompanyDetailDelegate;
import io.ztech.placementportal.delegate.StudentDetailDelegate;

public class AddDetailsService {
	StudentDetailDelegate studentDelegate = new StudentDetailDelegate();

	public void addStudentDetail(Student student, Marks mark) throws SQLException {
		studentDelegate.addStudentDetail(student, mark);

	}

	public void addCompanyDetail(Company company, Eligiblity eligible) throws SQLException {
		CompanyDetailDelegate company_delegate = new CompanyDetailDelegate();
		company_delegate.addCompanyDetail(company, eligible);

	}

	public void addPersonalInfo(PersonalInfo personalInfo) throws SQLException {

		studentDelegate.addPersonalDetail(personalInfo);
	}

}
