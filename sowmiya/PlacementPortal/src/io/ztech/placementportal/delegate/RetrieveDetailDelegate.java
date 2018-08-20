package io.ztech.placementportal.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dao.RetrieveDetailsDao;

public class RetrieveDetailDelegate {
	RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();

	public Student viewStudentDetail(String reg_no) throws SQLException {
		return viewDetail.getStudentDetail(reg_no);

	}

	public Marks getMarkDetail(String reg_no) throws SQLException {
		return viewDetail.getMarkDetail(reg_no);
	}

	public ArrayList<Company> getCompanyDetails() throws SQLException {
		return viewDetail.getCompanyDetail();
	}

	public Eligiblity getEligiblityDetail(Company company) {
		return viewDetail.getEligiblityDetail(company);
	}

	public ArrayList<Student> getEligiblityList(Company company) throws SQLException {
		return viewDetail.getEligiblityList(company);
	}

	public ArrayList<Profile> viewProfileDetail(String reg_no, String category) throws SQLException {
		// TODO Auto-generated method stub
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			return viewDetail.getProfileDetail(reg_no, SqlConstants.GETACHIEVEMENT);
		if (category.equals(ApplicationConstants.PROJECT))
			return viewDetail.getProfileDetail(reg_no, SqlConstants.GETPROJECT);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			return viewDetail.getProfileDetail(reg_no, SqlConstants.GETCOURSES);
		return null;
	}

	public Profile viewSpecificProfileDetail(int profileDetailId, String category) throws SQLException {
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			return viewDetail.viewSpecificProfileDetail(profileDetailId, SqlConstants.GETSPECIFICACHIEVEMENT);
		if (category.equals(ApplicationConstants.PROJECT))
			return viewDetail.viewSpecificProfileDetail(profileDetailId, SqlConstants.GETSPECIFICPROJECT);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			return viewDetail.viewSpecificProfileDetail(profileDetailId, SqlConstants.GETSPECIFICCOURSES);

		return null;
	}

	public PersonalInfo viewPersonalInfo(String student_id) throws SQLException {
		return viewDetail.getPersonalDetail(student_id);
	}

	public boolean checkIsAvailable(String input, String field) throws SQLException {
		if (field.equals(ApplicationConstants.USERNAME))
			return viewDetail.checkIsAvailable(input, SqlConstants.CHECK_USER);
		if (field.equals(ApplicationConstants.EMAIL_ID))
			return viewDetail.checkIsAvailable(input, SqlConstants.CHECK_EMAIL);
		if (field.equals(ApplicationConstants.REGISTER_NO))
			return viewDetail.checkIsAvailable(input, SqlConstants.CHECK_REGISTER_NO);

		return false;
	}

	public Company getSpecificCompanyDetail(Company company) throws SQLException {
		return viewDetail.getSpecificCompany(company);
	}

}
