package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.RetrieveDetailsService;

public class ViewEligiblityList {
	Logger log;
	ScanInput scan;

	public ViewEligiblityList() {
		log = Logger.getLogger("ViewEligiblityList.class");
		scan = new ScanInput();
	}

	public void viewEligibleList() {
		RetrieveDetailsService viewDetail = new RetrieveDetailsService();
		Company company = new Company();
		ArrayList<Company> list;
		ArrayList<Student> eligiblityList;
		try {
			list = viewDetail.viewCompanyDetails();
			log.info(ApplicationConstants.LIST_OF_COMPANIES);
			list.forEach(item -> log.info(
					ApplicationConstants.COMPANY_ID + item.getCompanyId() + "\n" + ApplicationConstants.COMPANY_NAME
							+ item.getCompanyName() + "\n" + ApplicationConstants.SEPARATING_STRING));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.COMPANY_ID);
			company.setCompanyId(Integer.parseInt(scan.getInput(Regex.NUMBER, ApplicationConstants.VALID_COMPANY_ID)));
			eligiblityList = viewDetail.viewEligiblityList(company);
			if (eligiblityList != null) {
				log.info(ApplicationConstants.TOTAL_STUDENTS_APPLIED + eligiblityList.size());
				eligiblityList.forEach(item -> log.info(item.toString()));
			} else {
				log.info(ApplicationConstants.VALID_COMPANY_ID);
				log.info(ApplicationConstants.SEPARATING_STRING);
			}
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);
			log.info(ApplicationConstants.SEPARATING_STRING);

		} catch (NumberFormatException e) {
			log.info(ApplicationConstants.VALID_COMPANY_ID);
			log.info(ApplicationConstants.SEPARATING_STRING);

		}

	}

}
