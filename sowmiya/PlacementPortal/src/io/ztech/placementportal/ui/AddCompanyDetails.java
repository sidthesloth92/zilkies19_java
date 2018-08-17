package io.ztech.placementportal.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.AddDetailsService;

public class AddCompanyDetails {

	Logger log = Logger.getLogger("AddCompanyDetails.class");
	Scanner scan = new Scanner(System.in);

	public void addCompanyDetail() {
		Company company = new Company();
		Eligiblity eligible = new Eligiblity();
		AddDetailsService add_company = new AddDetailsService();
		String input;
		boolean isSuccess;
		log.info(ApplicationConstants.COMPANY_NAME);
		input = scan.nextLine();
		company.setCompany_name(input);
		log.info(ApplicationConstants.COMPANY_TYPE);
		input = scan.nextLine();
		company.setCompany_type(input);
		log.info(ApplicationConstants.COMPANY_DESCRIPTION);
		input = input + scan.nextLine();
		company.setCompany_description(input);
		log.info(ApplicationConstants.DESIGNATION);
		input = scan.nextLine();
		company.setDesignation(input);
		log.info(ApplicationConstants.PAYMENT);
		input = scan.nextLine();
		company.setPayment(input);
		log.info(ApplicationConstants.JOB_LOCATION);
		input = scan.nextLine();
		company.setJob_location(input);
		log.info(ApplicationConstants.RECRUITMENT__DATE);
		input = scan.nextLine();
		company.setDay_of_recruitment(input);
		log.info(ApplicationConstants.ELIGIBLITY);
		log.info(ApplicationConstants.CGPA);
		eligible.setCgpa(scan.nextFloat());
		log.info(ApplicationConstants.PERCENTAGE_X);
		eligible.setMark_X(scan.nextFloat());
		log.info(ApplicationConstants.PERCENTAGE_XII);
		eligible.setMark_XII(scan.nextFloat());
		log.info(ApplicationConstants.ARREAR_COUNT);
		eligible.setArrear_count(scan.nextInt());
		isSuccess = add_company.addCompanyDetail(company, eligible);
		if (isSuccess)
			log.info(ApplicationConstants.UPADTED);
		else
			log.info(ApplicationConstants.ERROR);

	}

}
