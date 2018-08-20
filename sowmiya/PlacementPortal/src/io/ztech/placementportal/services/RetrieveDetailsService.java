package io.ztech.placementportal.services;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

public class RetrieveDetailsService {
	RetrieveDetailDelegate viewDetail = new RetrieveDetailDelegate();

	public Student viewStudentDetail(String reg_no) throws SQLException {
		return viewDetail.viewStudentDetail(reg_no);
	}

	public Marks viewMarkDetail(String reg_no) throws SQLException {

		return viewDetail.getMarkDetail(reg_no);
	}

	public ArrayList<Company> viewCompanyDetails() throws SQLException {
		return viewDetail.getCompanyDetails();
	}
    public Company viewSpecificCompany(Company company) throws SQLException{
    	return viewDetail.getSpecificCompanyDetail(company);
    }

	public ArrayList<Student> viewEligiblityList(Company company) throws SQLException {
		// TODO Auto-generated method stub
		return viewDetail.getEligiblityList(company);
	}

	public ArrayList<Profile> viewProfileDetail(String reg_no, String category) throws SQLException {
		return viewDetail.viewProfileDetail(reg_no, category);
	}
	public Profile viewProfileDetail(int profileDetailId, String category) throws SQLException {
		return viewDetail.viewSpecificProfileDetail(profileDetailId,category);

	}

	public PersonalInfo viewPersonalInfo(String student_id) throws SQLException {
		return viewDetail.viewPersonalInfo(student_id);
	}

	public boolean checkIsAvailable(String input, String field) throws SQLException {
		return viewDetail.checkIsAvailable(input,field);
	}
}
