package com.zilker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.service.GetUserRequestService;

public class UserRequestStatus {

	Logger logger = Logger.getLogger(UserRequestStatus.class.getName());

	public void getRequestStatus(UserData userData) {

		ArrayList<ConferenceData> conferenceListRequest = new ArrayList<ConferenceData>();

		try {
			conferenceListRequest = new GetUserRequestService().getUserRequests(userData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (ConferenceData conferenceData : conferenceListRequest) {

			logger.info(StringConstants.NEW_LINE +

					StringConstants.CONFERENCE_ID + conferenceData.getConferenceId() +

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

			if (conferenceData.getStatus() == 1) {

				logger.info(StringConstants.STATUS + StringConstants.ACCEPTED);
			} else if(conferenceData.getStatus()==2) {

				logger.info(StringConstants.STATUS + StringConstants.DECLINED);
			}else {
				logger.info(StringConstants.STATUS + StringConstants.SUBMITTED);
			}

		}
	}

}
