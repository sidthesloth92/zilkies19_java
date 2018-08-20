package com.zilker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.service.ConferenceHistoryService;

public class ConferenceHistory {
	
	Logger logger = Logger.getLogger(ConferenceHistory.class.getName());		
	
	public void getHistory(UserData userData) {
		
		ConferenceHistoryService conferenceHistoryService = new ConferenceHistoryService();
		
		ArrayList<ConferenceData> list = null;
		try {
			list = conferenceHistoryService.getHistory(userData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		for(ConferenceData conferenceData : list) {

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
			
			if(conferenceData.getStatus()==1) {
				
				logger.info(StringConstants.STATUS+StringConstants.ACCEPTED);
			}else {
				
				logger.info(StringConstants.STATUS+StringConstants.DECLINED);
			}
	}
	}

}
