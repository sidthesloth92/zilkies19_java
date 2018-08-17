package io.ztech.placementportal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.UpdateCompanyDetailService;
import io.ztech.placementportal.services.ViewDetailsService;

public class UpdateCompanyDetail {
	Logger log = Logger.getLogger("UpdateCompany.class");
	Scanner scan = new Scanner(System.in);

	public void getNewDetail() {
		int choice;
		char continueChoice = ' ';
		Company company = new Company();
		ViewDetailsService viewJob = new ViewDetailsService();
		UpdateCompanyDetailService update = new UpdateCompanyDetailService();
		ArrayList<HashMap<String, String>> list;
		list = viewJob.viewCompanyDetails();
		list.forEach(item -> item.forEach((k, v) -> System.out.println(k + "" + v)));
		log.info(ApplicationConstants.ENTER + ApplicationConstants.COMPANY_ID);
		company.setCompany_id(scan.nextInt());
		do {
			log.info(ApplicationConstants.EDIT_COMPANY);
			choice = scan.nextInt();
			scan.nextLine();
			switch (choice) {
			case 1:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.COMPANY_NAME);
				company.setCompany_name(scan.nextLine());
				break;
			case 2:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.COMPANY_TYPE);
				company.setCompany_type(scan.nextLine());
				break;
			case 3:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.COMPANY_DESCRIPTION);
				company.setCompany_description(scan.nextLine());
				break;
			case 4:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.JOB_LOCATION);
				company.setJob_location(scan.nextLine());
				break;
			case 5:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.PAYMENT);
				company.setPayment(scan.nextLine());
				break;
			case 6:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.DESIGNATION);
				company.setDesignation(scan.nextLine());
				break;
			case 7:
				log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.RECRUITMENT__DATE);
				company.setDay_of_recruitment(scan.nextLine());
				break;
			default:
				log.info(ApplicationConstants.VALID_INPUT);
				break;
			}
			log.info(ApplicationConstants.CONTINUE_EDIT);
			continueChoice = scan.nextLine().charAt(0);

		} while (continueChoice == 'Y' || continueChoice == 'y');
		if (update.updateCompanyDetail(company))
			log.info(ApplicationConstants.UPADTED);
		else
			log.info(ApplicationConstants.ERROR);
	}

}
