package io.ztech.placementportal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.UpdateStudentDetailService;
import io.ztech.placementportal.services.ViewDetailsService;

public class UpdatePlacementStatus {
    Logger log=Logger.getLogger("UpdatePlacementStatus.class");
    Scanner scan=new Scanner(System.in);
	public void getPlacementDetail(PlacedDetail student) {
		ViewDetailsService viewJob = new ViewDetailsService();
		UpdateStudentDetailService update=new UpdateStudentDetailService();
		ArrayList<HashMap<String, String>> list;
		list = viewJob.viewCompanyDetails();
		int choice;
		for (HashMap<String, String> entry : list) {
          System.out.println(ApplicationConstants.COMPANY_ID+entry.get(ApplicationConstants.COMPANY_ID)+" "
		   +ApplicationConstants.COMPANY_NAME+entry.get(ApplicationConstants.COMPANY_NAME));
		   	}
	  log.info(ApplicationConstants.ENTER+ApplicationConstants.COMPANY_ID);
	  student.setCompany_id(scan.nextInt());
	  log.info(ApplicationConstants.ENTER+ApplicationConstants.JOB_STATUS);
	  choice=scan.nextInt();
	  if(choice==1)
		  student.setJobStatus("INTERN");
	  if(choice==2)
		  student.setJobStatus("OFFER");
	  if(update.updatePlacementDetail(student))
		  log.info(ApplicationConstants.UPADTED);
	  else
		  log.info(ApplicationConstants.ERROR);
		
	}

}
