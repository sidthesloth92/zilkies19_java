package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.RetrieveDetailsService;
import io.ztech.placementportal.services.UpdateCompanyDetailService;

public class UpdateCompanyDetail {
	Logger log;
	Scanner scan;

	public UpdateCompanyDetail() {
		log = Logger.getLogger("UpdateCompany.class");
		scan = new Scanner(System.in);
	}

	public void getNewDetail() {
		int choice;
		char continueChoice = ' ';
		Company company = new Company();
		RetrieveDetailsService viewJob = new RetrieveDetailsService();
		UpdateCompanyDetailService update = new UpdateCompanyDetailService();
		ArrayList<Company> list;
		try {
			list = viewJob.viewCompanyDetails();
			list.forEach(item -> log.info(item.toString() + ApplicationConstants.SEPARATING_STRING));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.COMPANY_ID);
			company.setCompanyId(scan.nextInt());
			company = viewJob.viewSpecificCompany(company);
			do {
				log.info(ApplicationConstants.EDIT_COMPANY);
				choice = scan.nextInt();
				scan.nextLine();
				switch (choice) {
				case 1:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.COMPANY_NAME);
					company.setCompanyName(scan.nextLine());
					break;
				case 2:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.COMPANY_TYPE);
					company.setCompanyType(scan.nextLine());
					break;
				case 3:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.COMPANY_DESCRIPTION);
					company.setCompanyDescription(scan.nextLine());
					break;
				case 4:
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.JOB_LOCATION);
					company.setJobLocation(scan.nextLine());
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
					log.info(ApplicationConstants.ENTER + " " + ApplicationConstants.RECRUITMENT_DATE);
					company.setDayOfRecruitment(scan.nextLine());
					break;
				default:
					log.info(ApplicationConstants.VALID_INPUT);
					break;
				}
				log.info(ApplicationConstants.CONTINUE_EDIT);
				continueChoice = scan.nextLine().charAt(0);

			} while (continueChoice == 'Y' || continueChoice == 'y');
			update.updateCompanyDetail(company);
			log.info(ApplicationConstants.SEPARATING_STRING);
			log.info(ApplicationConstants.UPDATED);
			log.info(ApplicationConstants.SEPARATING_STRING);
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
			log.info(ApplicationConstants.SEPARATING_STRING);
		} catch (NumberFormatException e) {
			log.info(ApplicationConstants.VALID_NUMBER);
			log.info(ApplicationConstants.SEPARATING_STRING);

		} catch (InputMismatchException e) {
			log.info(ApplicationConstants.VALID_INPUT);
		}
	}

}
