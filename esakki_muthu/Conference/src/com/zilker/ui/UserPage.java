package com.zilker.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.bean.LoginData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.OptionConstants;

public class UserPage {
	
	Logger logger=Logger.getLogger(UserPage.class.getName());
	
	SimpleDateFormat  dateFormat = null;
	
	SimpleDateFormat  timeFormat = null;
	
	Date currentDate = null;
	
	Date currentTime = null;
	
	boolean userControl = true;
	
	Scanner in = new Scanner(System.in);
	
	public void getHome(UserData userData) {
				
		logger.info(StringConstants.TAB_SPACE + StringConstants.WELCOME_GREETING + userData.getUser_name());

		currentDate = new Date();

		currentTime = new Date();

		dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		userData.setCurrent_Date(dateFormat.format(currentDate));		

		timeFormat = new SimpleDateFormat("HH:mm");
		
		userData.setCurrent_Time(timeFormat.format(currentTime));
		
		getUserOptions(userData);
	}
	
	public void getUserOptions(UserData userData)
	{
		
		while(userControl) {
			
			logger.info(OptionConstants.USER_HOME_OPTIONS);
			
			int option = in.nextInt();
			
			switch(option) {
			
			case 1:
				
				HallBooking hallBooking = new HallBooking();
				
				hallBooking.hallBookingOptions(userData);
				
				break;
			case 2:
				
				DisplayAvailability displayAvailability = new DisplayAvailability();
				
				displayAvailability.checkAvailability();
												
				break;
				
			case 3:
				
				UserRequestStatus  userRequestStatus = new UserRequestStatus();
				
				userRequestStatus.getRequestStatus(userData);
				
				break;
				
			case 4:
				
				ConferenceHistory conferenceHistory = new ConferenceHistory();
				
				conferenceHistory.getHistory(userData);
				
				break;
				
			default:
				
				userControl = false;
				
				break;
			
			}
			
		}
		
	}

}
