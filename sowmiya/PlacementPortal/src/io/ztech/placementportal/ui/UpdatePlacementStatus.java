package io.ztech.placementportal.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.services.RetrieveDetailsService;
import io.ztech.placementportal.services.UpdateStudentDetailService;

public class UpdatePlacementStatus {
	private Logger log;
	private ScanInput scan;

	public UpdatePlacementStatus() {
		log = Logger.getLogger("UpdatePlacementStatus.class");
		scan = new ScanInput();
	}

	public void getPlacementDetail(PlacedDetail student) {
		RetrieveDetailsService viewJob = new RetrieveDetailsService();
		UpdateStudentDetailService update = new UpdateStudentDetailService();
		ArrayList<Company> list;
		try {
			list = viewJob.viewCompanyDetails();
			int choice;
			list.forEach(item -> log.info(ApplicationConstants.COMPANY_ID + item.getCompanyId() + "\n"
					+ ApplicationConstants.COMPANY_NAME + item.getCompanyName()));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.COMPANY_ID);
			student.setCompanyId(Integer.parseInt(scan.getInput(Regex.NUMBER, ApplicationConstants.VALID_COMPANY_ID)));
			log.info(ApplicationConstants.ENTER + ApplicationConstants.JOB_STATUS);
			choice = Integer.parseInt(scan.getInput(Regex.NUMBER, ApplicationConstants.VALID_OPTION));
			if (choice == 1)
				student.setJobStatus("INTERN");
			if (choice == 2)
				student.setJobStatus("OFFER");
			update.updatePlacementDetail(student);
			log.info(ApplicationConstants.UPDATED);
		} catch (SQLException e) {
			log.info(ApplicationConstants.ERROR);

		}

	}
}
