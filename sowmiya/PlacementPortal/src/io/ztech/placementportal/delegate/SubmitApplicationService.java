package io.ztech.placementportal.delegate;

import java.time.LocalDate;

import io.ztech.placementportal.bean.Company;

public class SubmitApplicationService {

	public boolean completeRegistration(Company company, String reg_no) {
		SubmitApplicationDelegate submitDelegate=new SubmitApplicationDelegate();
		LocalDate date=java.time.LocalDate.now();
		return submitDelegate.completeRegistration(company,reg_no,date);
		
	}

}
