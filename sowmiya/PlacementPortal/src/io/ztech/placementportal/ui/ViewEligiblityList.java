package io.ztech.placementportal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.ViewDetailsService;

public class ViewEligiblityList {
	static Logger log = Logger.getLogger("ViewEligiblityList.class");
	static Scanner scan = new Scanner(System.in);

	public void viewEligibleList() {
		ViewDetailsService viewDetail = new ViewDetailsService();
		Company company = new Company();
		ArrayList<HashMap<String, String>> list, eligiblityList;
		list = viewDetail.viewCompanyDetails();
		log.info(ApplicationConstants.LIST_OF_COMPANIES);
		for (HashMap<String, String> entry : list) {
			System.out.println(ApplicationConstants.COMPANY_ID + entry.get(ApplicationConstants.COMPANY_ID) + " "
					+ ApplicationConstants.COMPANY_NAME + entry.get(ApplicationConstants.COMPANY_NAME));
		}
		log.info(ApplicationConstants.ENTER + ApplicationConstants.COMPANY_ID);
		company.setCompany_id(scan.nextInt());
		eligiblityList = viewDetail.viewEligiblityList(company);
		if (eligiblityList.size() == 0) {
			System.out.println(ApplicationConstants.NO_REGISTRATION);
		}
		System.out.println(ApplicationConstants.TOTAL_STUDENTS_APPLIED + eligiblityList.size());
		System.out.println(ApplicationConstants.ELIGIBLITY_LIST);
		eligiblityList.forEach(item -> {
			item.forEach((k, v) -> {
				System.out.print(v + "\t\t");
			});
			System.out.println();
		});

	}

}
