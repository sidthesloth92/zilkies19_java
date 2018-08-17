package com.zilker.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.bean.HallData;
import com.zilker.bean.LoginData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.OptionConstants;
import com.zilker.service.ConferenceHallRequestService;

public class AdminPage {

	Scanner in = new Scanner(System.in);

	Logger logger = Logger.getLogger(AdminPage.class.getName());

	SimpleDateFormat dateFormat = null;

	SimpleDateFormat timeFormat = null;

	Date currentDate = null;

	Date currentTime = null;

	Hall hall = new Hall();

	HallData hallData = new HallData();

	ConferenceHallRequestService hallRequests = new ConferenceHallRequestService();

	ApproveReject approveReject = new ApproveReject();

	ConferenceHistory conference_History = new ConferenceHistory();
	
	UpcomingConference upcomingConference = new UpcomingConference();

	boolean adminControl = true;

	public void getAdmin(UserData userData) {

		logger.info(StringConstants.TAB_SPACE + StringConstants.WELCOME_GREETING + userData.getUser_name());

		currentDate = new Date();

		currentTime = new Date();

		dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		userData.setCurrent_Date(dateFormat.format(currentDate));

		timeFormat = new SimpleDateFormat("HH:mm");

		userData.setCurrent_Time(timeFormat.format(currentTime));

		adminOptions(userData);

	}

	public void adminOptions(UserData userData) {

		while (adminControl) {
			logger.info(OptionConstants.ADMIN_HOME_OPTIONS);

			int option = in.nextInt();

			switch (option) {

			case 1:

				hall.addHall(hallData);

				break;

			case 2:

				hall.viewHallList();

				break;

			case 3:

				hallRequests.viewRequests(userData);

				break;

			case 4:

				approveReject.getConferenceId(userData);

				break;

			case 5:

				conference_History.getHistory(userData);

				break;

			case 6:
				
				upcomingConference.getHistory(userData);
				
				break;

			case 7:

				adminControl = false;

				break;
			}

		}

	}

}
