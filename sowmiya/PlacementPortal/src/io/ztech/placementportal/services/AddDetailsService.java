package io.ztech.placementportal.services;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.delegate.CompanyDetailDelegate;
import io.ztech.placementportal.delegate.StudentDetailDelegate;

public class AddDetailsService {
	StudentDetailDelegate studentDelegate = new StudentDetailDelegate();

	public boolean addStudentDetail(Student student, Marks mark) {
		return studentDelegate.addStudentDetail(student, mark);

	}

	public boolean addCompanyDetail(Company company, Eligiblity eligible) {
		CompanyDetailDelegate company_delegate = new CompanyDetailDelegate();
		return company_delegate.addCompanyDetail(company, eligible);

	}

	public boolean addPersonalInfo(PersonalInfo personalInfo) {

		return studentDelegate.addPersonalDetail(personalInfo);
	}

}
