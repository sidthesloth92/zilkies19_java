package io.ztech.placementportal.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;

public class UpdateStudentDetail {
    Logger log=Logger.getLogger("UpdateStudentDetail.class");
	Scanner scan = new Scanner(System.in);

	public void chooseCategory() {
		int choice;
		char continueChoice = ' ';
		String id;
		log.info(ApplicationConstants.ENTER+" "+ApplicationConstants.STUDENT_ID);
		id=scan.nextLine();
		do {
			log.info(ApplicationConstants.CATEGORY);
			choice=scan.nextInt();
			System.out.println(choice);
			switch (choice) {
			case 1:Student studentDetail=new Student();
			     UpdateDetails updateDetail=new UpdateDetails();
			     studentDetail.setStudent_id(id);
			     updateDetail.getNewDetail(studentDetail);
				break;
			case 2:Marks mark=new Marks();
				  UpdateMarks updateMark=new UpdateMarks();
				  mark.setStudent_id(id);
			      updateMark.getMarks(mark);
				break;
			case 3:
				  PlacedDetail student=new PlacedDetail();
			      UpdatePlacementStatus update=new UpdatePlacementStatus();
				  student.setStudent_id(id);
			      update.getPlacementDetail(student);
			      break;
				
			default:
				break;
			}
			scan.nextLine();
			log.info(ApplicationConstants.CONTINUE_EDIT);
			continueChoice=scan.next().charAt(0);
			
		}while(continueChoice=='Y'||continueChoice=='y');
		
		
	}
	
	
}
