package com.zilker.ui;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.OptionConstants;
import com.zilker.service.GetConferenceDatasService;

public class ApproveReject {

	ConferenceData conferenceData = new ConferenceData();

	GetConferenceDatasService getConferenceDatasService = new GetConferenceDatasService();

	Scanner in = new Scanner(System.in);

	Logger logger = Logger.getLogger(ApproveReject.class.getName());

	public void getConferenceId(UserData userData)  {

		logger.info(StringConstants.ENTER_CONFERENCE_Id);

		int conferenceId = in.nextInt();

		try {
			conferenceData = getConferenceDatasService.getDatas(conferenceId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (conferenceData.getFound() == 1) {

			logger.info(StringConstants.NEW_LINE +

					StringConstants.CONFERENCE_ID + conferenceData.getConferenceId() +

					StringConstants.NEW_LINE +

					StringConstants.NAME + conferenceData.getUserName() +

					StringConstants.NEW_LINE +

					StringConstants.EMAIL + conferenceData.getEmail() +

					StringConstants.NEW_LINE +

					StringConstants.HALL_NAME + conferenceData.getHallName() +

					StringConstants.NEW_LINE +

					StringConstants.DATE + conferenceData.getFromDate() +

					StringConstants.DASH +

					conferenceData.getToDate() +

					StringConstants.NEW_LINE +

					StringConstants.TIME + conferenceData.getFromTime() +

					StringConstants.DASH +

					conferenceData.getToTime() +

					StringConstants.NEW_LINE);

		
			logger.info(OptionConstants.REQUEST_RESPONSE_OPTIONS);
			
			int option = in.nextInt();
			
			switch(option) {
			
			case 1:
				
				try {
					getConferenceDatasService.setAccept(userData,conferenceId,1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
				
			case 2:
				
				try {
					getConferenceDatasService.setAccept(userData,conferenceId,2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
				
			default:
				
				break;
			
			}
			
		}

		else
			logger.info(StringConstants.CONFERENCE_REQUEST_NOT_FOUND);

	}

}
