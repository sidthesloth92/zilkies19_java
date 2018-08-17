package io.ztech.placementportal.services;

import java.util.ArrayList;
import java.util.HashMap;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

public class ViewDetailsService {
	RetrieveDetailDelegate viewDetail = new RetrieveDetailDelegate();

	public HashMap<String, String> viewStudentDetail(String reg_no) {
		return viewDetail.viewStudentDetail(reg_no);
	}

	public HashMap<String, Float> viewMarkDetail(String reg_no) {

		return viewDetail.getMarkDetail(reg_no);
	}

	public ArrayList<HashMap<String, String>> viewCompanyDetails() {
		return viewDetail.getCompanyDetails();
	}

	public ArrayList<HashMap<String, String>> viewEligiblityList(Company company) {
		// TODO Auto-generated method stub
		return viewDetail.getEligiblityList(company);
	}

	public ArrayList<HashMap<String, String>> viewProfileDetail(String reg_no, String category) {
		return viewDetail.viewProfileDetail(reg_no, category);
	}
	public HashMap<String, String> viewProfileDetail(int profileDetailId, String category) {
		return viewDetail.viewSpecificProfileDetail(profileDetailId,category);

	}

	public HashMap<String, String> viewPersonalInfo(String student_id) {
		return viewDetail.viewPersonalInfo(student_id);
	}
}
