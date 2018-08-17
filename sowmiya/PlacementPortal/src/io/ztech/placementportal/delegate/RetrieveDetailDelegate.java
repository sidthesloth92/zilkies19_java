package io.ztech.placementportal.delegate;

import java.util.ArrayList;
import java.util.HashMap;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dao.RetrieveDetailsDao;

public class RetrieveDetailDelegate {
	RetrieveDetailsDao viewDetail = new RetrieveDetailsDao();

	public HashMap<String, String> viewStudentDetail(String reg_no) {
		return viewDetail.getStudentDetail(reg_no);

	}

	public HashMap<String,Float> getMarkDetail(String reg_no) {
		return viewDetail.getMarkDetail(reg_no);
	}

	public ArrayList<HashMap<String, String>> getCompanyDetails() {
		return viewDetail.getCompanyDetail();
	}

	public HashMap<String,Float> getEligiblityDetail(Company company) {
		return viewDetail.getEligiblityDetail(company);
	}

	public ArrayList<HashMap<String, String>> getEligiblityList(Company company) {
		return viewDetail.getEligiblityList(company);
	}

	public ArrayList<HashMap<String, String>> viewProfileDetail(String reg_no,String category) {
		// TODO Auto-generated method stub
		if(category.equals(ApplicationConstants.ACHIEVEMENT))
			return viewDetail.viewProfile(reg_no,SqlConstants.GETACHIEVEMENT);
		if(category.equals(ApplicationConstants.PROJECT))
			return viewDetail.viewProfile(reg_no,SqlConstants.GETPROJECT);
		if(category.equals(ApplicationConstants.CERTIFICATIONS))
			return viewDetail.viewProfile(reg_no,SqlConstants.GETCOURSES);
		return null;
		}

	public HashMap<String, String> viewSpecificProfileDetail(int profileDetailId,String category) {
		if(category.equals(ApplicationConstants.ACHIEVEMENT))
			return viewDetail.viewSpecificProfileDetail(profileDetailId,SqlConstants.GETSPECIFICACHIEVEMENT);
		if(category.equals(ApplicationConstants.PROJECT))
			return viewDetail.viewSpecificProfileDetail(profileDetailId,SqlConstants.GETSPECIFICPROJECT);
		if(category.equals(ApplicationConstants.CERTIFICATIONS))
			return viewDetail.viewSpecificProfileDetail(profileDetailId,SqlConstants.GETSPECIFICCOURSES);
		
		return null;
	}

	public HashMap<String, String> viewPersonalInfo(String student_id) {
				return viewDetail.getPersonalDetail(student_id);
	}

	
}
