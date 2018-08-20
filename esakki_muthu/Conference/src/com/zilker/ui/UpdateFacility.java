package com.zilker.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.bean.HallData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.service.HallService;
import com.zilker.util.Inputs;

public class UpdateFacility {
	
	Inputs inputs = new Inputs();	
	
	Scanner in =  new Scanner(System.in);
	
	Logger logger = Logger.getLogger(UpdateFacility.class.getName());
		
	public int updateFacility(UserData userData) {
				
		HallService hallService = new HallService();

		ArrayList<HallData> hallList = hallService.hallListService();
		
		logger.info(StringConstants.ENTER_HALL_ID);
				
		int hallId = in.nextInt();										
		
		return 1;
		 
	}

}
