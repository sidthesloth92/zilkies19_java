package com.zilker.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.zilker.bean.ConferenceData;
import com.zilker.constants.StringConstants;

public class ConferenceRequests {
	
	Logger logger = Logger.getLogger(ConferenceRequests.class.getName());

	public void conferenceRequest(ArrayList<ConferenceData> list) {
		
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
		}
		
	}

}
