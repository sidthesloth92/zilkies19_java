package io.ztech.placementportal.ui;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.Driver;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.services.ViewDetailsService;

public class StudentDashboard {
	Logger log=Logger.getLogger("StudentDashboard.class");
	static Scanner scan=new Scanner(System.in);
	
	public void viewDashboard(Register login) {
		int option;
		char continueChoice =' ';
		showDetails(login);
		do {
		log.info(ApplicationConstants.STUDENT_DASHBOARD);
		option=scan.nextInt();
		switch(option) {
		case 1:Profile profile=new Profile();
		      profile.buildProfile(login);
			   break;
		case 2:JobDetail job=new JobDetail();
		       job.viewJobs();
		       break;
		      
		case 3:break;
		case 4:Driver.main(null);
		       break;
		default:log.info(ApplicationConstants.WRONG_CHOICE);
		}
		log.info(ApplicationConstants.CONTINUE_CHOICE);
		continueChoice = scan.next().charAt(0);

		}while(continueChoice=='Y');
		log.info(ApplicationConstants.THANKYOU);

	}
	public void showDetails(Register login) {
		ViewDetailsService viewDetail=new ViewDetailsService();
		HashMap<String,String> map=new LinkedHashMap<>();
		HashMap<String,Float>markMap=new LinkedHashMap<>();
		map=viewDetail.viewStudentDetail(login.getReg_no());
		map.forEach((k,v)->System.out.println( k+"" + v));
		markMap=viewDetail.viewMarkDetail(login.getReg_no());
		markMap.forEach((k,v)->System.out.println( k+"" + v));
	}

}
