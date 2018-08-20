package com.zilker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.zilker.bean.Availability;
import com.zilker.constants.StringConstants;
import com.zilker.service.AvailabilityCheckingService;

public class DisplayAvailability {

	Logger logger = Logger.getLogger(DisplayAvailability.class.getName());

	ArrayList<Availability> list_Datas = new ArrayList<Availability>();

	public void checkAvailability() {

		displayHallAvailability();
	}

	public void displayHallAvailability() {

		try {
			list_Datas = new AvailabilityCheckingService().displayAvailability();

			for (Availability availability : list_Datas) {

				int flag = 0;

				logger.info(availability.getAvailabilityId() + StringConstants.PARENTHESIS

						+ StringConstants.NEW_LINE +

						StringConstants.HALL_ID + availability.getHallId() +

						StringConstants.NEW_LINE +

						StringConstants.HALL_NAME + availability.getHallName() +

						StringConstants.NEW_LINE +

						StringConstants.HALL_SIZE + availability.getHallSize() +

						StringConstants.NEW_LINE

				);

				for (String dates : availability.getBookedDates()) {

					logger.info(StringConstants.NEW_LINE + StringConstants.BOOKED_DATE_TIME + dates
							+ StringConstants.NEW_LINE);

					flag = 1;

				}
				if (flag == 0) {

					logger.info(StringConstants.NEW_LINE + StringConstants.NOT_BOOKED + StringConstants.NEW_LINE);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
