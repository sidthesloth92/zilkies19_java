package com.example.demo.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.example.demo.beans.Company;
import com.example.demo.beans.PersonalInfo;
import com.example.demo.beans.PlacedDetail;
import com.example.demo.beans.Profile;
import com.example.demo.beans.Student;
import com.example.demo.constants.ApplicationConstants;
import com.example.demo.constants.SqlConstants;
import com.example.demo.dao.RetrieveDetailsDao;

public class RetrieveDetailDelegate {
	private Logger log;

	public RetrieveDetailDelegate() {
		log = Logger.getLogger("RetrieveDetailDelegate.class");
	}

	public Student getSpecificStudentDetail(String registerNumber) {
		log.info("Entering getSpecificStudentDetail()");
		try {
			RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
			return viewDetail.getSpecificStudentDetail(registerNumber);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting getSpecificStudentDetail()");
		return null;

	}

	public ArrayList<Student> getStudentDetails() {
		log.info("Entering getStudentDetails()");
		try {
			RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
			return viewDetail.getStudentDetail();
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("EXiting getStudentDetails()");
		return null;
	}

	public ArrayList<Company> getCompanyDetails() {
		RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
		return viewDetail.getCompanyDetail();
	}

	public ArrayList<Student> getEligiblityList(String companyId) {
		try {
			RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
			return viewDetail.getEligiblityList(companyId);
		} catch (Exception e) {
			log.info(e.toString());
		}
		return null;
	}

	public ArrayList<Profile> getProfileDetail(String reg_no, String category) {
		log.info("Entering getProfileDetail() in RetrieveDetailDelegate ");
		try {
			RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
			if (category.equals(ApplicationConstants.ACHIEVEMENT))
				return viewDetail.getProfileDetail(reg_no, SqlConstants.GETACHIEVEMENT);
			if (category.equals(ApplicationConstants.PROJECT))
				return viewDetail.getProfileDetail(reg_no, SqlConstants.GETPROJECT);
			if (category.equals(ApplicationConstants.CERTIFICATIONS)) {
				return viewDetail.getProfileDetail(reg_no, SqlConstants.GETCOURSES);
			}
			return null;
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting getProfileDetail() in RetrieveDetailDelegate ");
		return null;
	}

	public Profile viewSpecificProfileDetail(int profileDetailId, String category) throws SQLException {
		RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			return viewDetail.viewSpecificProfileDetail(profileDetailId, SqlConstants.GETSPECIFICACHIEVEMENT);
		if (category.equals(ApplicationConstants.PROJECT))
			return viewDetail.viewSpecificProfileDetail(profileDetailId, SqlConstants.GETSPECIFICPROJECT);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			return viewDetail.viewSpecificProfileDetail(profileDetailId, SqlConstants.GETSPECIFICCOURSES);

		return null;
	}

	public PersonalInfo viewPersonalInfo(String student_id) throws SQLException {
		RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
		return viewDetail.getPersonalDetail(student_id);
	}

	public boolean checkIsAvailable(String input, String field) throws SQLException {
		RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
		if (field.equals(ApplicationConstants.USERNAME))
			return viewDetail.checkIsAvailable(input, SqlConstants.CHECK_USER);
		if (field.equals(ApplicationConstants.EMAIL_ID))
			return viewDetail.checkIsAvailable(input, SqlConstants.CHECK_EMAIL);
		if (field.equals(ApplicationConstants.REGISTER_NO)) {
			return viewDetail.checkIsAvailable(input, SqlConstants.CHECK_REGISTER_NO);
		}
		return false;
	}

	public Company getSpecificCompanyDetail(Company company) {
		RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
		return viewDetail.getSpecificCompany(company);
	}

	public ArrayList<Company> getRegistrationLog(Student student) {
		RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
		return viewDetail.getRegistrationLog(student);
	}

	public ArrayList<PlacedDetail> getPlacementDetails() {
		try {
			RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
			return viewDetail.getPlacementDetail();
		} catch (Exception e) {
			log.info(e.toString());
		}
		return null;
	}

	public ArrayList<Student> getStudentsPlacedInASpecificCompany(String id) {
		try {
			RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();
			return viewDetail.getStudentsPlacedInACompany(id);
		} catch (Exception e) {
			log.info(e.toString());
		}
		return null;
	}
}
