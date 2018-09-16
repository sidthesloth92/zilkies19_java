package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.ApplicationValidateService;
import io.ztech.placementportal.services.RetrieveDetailsService;
import io.ztech.placementportal.services.SubmitApplicationService;

public class JobDetail {
	private Logger log;
	private Scanner scan;

	public JobDetail() {
		log = Logger.getLogger("JobDetail.class");
		scan = new Scanner(System.in);
	}

	public void viewJobs() {
		char choice;
		ApplicationValidateService service = new ApplicationValidateService();
		SubmitApplicationService submit_application = new SubmitApplicationService();
		Company company = new Company();
		try {
			showJobDetails();
			log.info(ApplicationConstants.APPLY_CHOICE);
			choice = scan.nextLine().charAt(0);
			do {
				if (choice == 'Y' || choice == 'y') {
					log.info(ApplicationConstants.APPLY_COMPANY);
					company.setCompanyId(scan.nextInt());
					scan.nextLine();
					try {
						if (!service.alreadyApplied(company, LoginPortal.reg_no)) {
							if (service.checkDate(java.time.LocalDate.now(), company)) {
								if (service.checkeligible(company, LoginPortal.reg_no)) {
									if (submit_application.completeRegistration(company, LoginPortal.reg_no))
										log.info(ApplicationConstants.APPLICATION_SUCCESS);
									else
										log.info(ApplicationConstants.ERROR);
								} else
									log.info(ApplicationConstants.NOT_ELIGIBLE);
							} else
								log.info(ApplicationConstants.APPLICATION_CLOSED);
						} else
							log.info(ApplicationConstants.ALREADY_APPLIED);
					} catch (NullPointerException e) {
						log.info(ApplicationConstants.ERROR);
					}
					log.info(ApplicationConstants.APPLY_ANOTHER_COMPANY);
					choice = scan.nextLine().charAt(0);
				}

			} while (choice == 'Y' || choice == 'y');
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
		}
	}

	private void showJobDetails() throws SQLException {
		RetrieveDetailsService viewJob = new RetrieveDetailsService();
		ArrayList<Company> list;
		list = viewJob.viewCompanyDetails();
		list.forEach(item -> {
			log.info(item.toString());
			log.info(ApplicationConstants.SEPARATING_STRING);
		});
	}

}
