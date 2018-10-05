package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.AddDetailsService;

public class AddCompanyDetails {

	private Logger log;
	private ScanInput scanInput;
	private Scanner scan;

	public AddCompanyDetails() {
		scanInput = new ScanInput();
		scan = new Scanner(System.in);
		log = Logger.getLogger("AddCompanyDetails.class");

	}

	public void addCompanyDetail() {
		Company company = new Company();
		Eligiblity eligible = new Eligiblity();
		AddDetailsService add_company = new AddDetailsService();
		String input;
		try {
			log.info(ApplicationConstants.ENTER + ApplicationConstants.COMPANY_NAME);
			input = scan.nextLine();
			company.setCompanyName(input);
			log.info(ApplicationConstants.ENTER + ApplicationConstants.COMPANY_TYPE);
			input = scan.nextLine();
			company.setCompanyType(input);
			log.info(ApplicationConstants.ENTER + ApplicationConstants.COMPANY_DESCRIPTION);
			input = input + scan.nextLine();
			company.setCompanyDescription(input);
			log.info(ApplicationConstants.ENTER + ApplicationConstants.DESIGNATION);
			input = scan.nextLine();
			company.setDesignation(input);
			log.info(ApplicationConstants.ENTER + ApplicationConstants.PAYMENT);
			input = scan.nextLine();
			company.setPayment(input);
			log.info(ApplicationConstants.ENTER + ApplicationConstants.JOB_LOCATION);
			input = scan.nextLine();
			company.setJobLocation(input);
			log.info(ApplicationConstants.ENTER + ApplicationConstants.RECRUITMENT_DATE);
			input = scanInput.getInput(Regex.DATE, ApplicationConstants.VALID_DATE);
			company.setDayOfRecruitment(input);
			log.info(ApplicationConstants.ENTER + ApplicationConstants.LAST_DATE_FOR_REGISTRATION);
			input = scanInput.getInput(Regex.DATE, ApplicationConstants.VALID_DATE);
			company.setLastDateForRegistration(input);
			log.info(ApplicationConstants.ELIGIBLITY);
			log.info(ApplicationConstants.ENTER + ApplicationConstants.CGPA);
			eligible.setCgpa(Float.parseFloat(scanInput.getInput(Regex.CGPA, ApplicationConstants.VALID_CGPA)));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.PERCENTAGE_X);
			eligible.setMarkX(Float.parseFloat(scanInput.getInput(Regex.MARKS, ApplicationConstants.VALID_PERCENTAGE)));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.PERCENTAGE_XII);
			eligible.setMarkXII(
					Float.parseFloat(scanInput.getInput(Regex.MARKS, ApplicationConstants.VALID_PERCENTAGE)));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.ARREAR_COUNT);
			eligible.setArrearCount(
					Integer.parseInt(scanInput.getInput(Regex.ARREAR_COUNT, ApplicationConstants.VALID_ARREAR_COUNT)));
			add_company.addCompanyDetail(company, eligible);
			log.info(ApplicationConstants.ADDED_COMPANY);
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);

		}

	}
}
