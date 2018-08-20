package com.zilker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.HallData;
import com.zilker.constants.StringConstants;
import com.zilker.dao.AddNewHall;
import com.zilker.dao.RequestsAndHallFacilities;
import com.zilker.service.HallService;
import com.zilker.util.Inputs;

public class Hall {

	Inputs inputs = new Inputs();

	HallService hallService = new HallService();

	Logger logger = Logger.getLogger(Hall.class.getName());

	String res = null;

	ArrayList<Integer> facilityId = new ArrayList<Integer>();

	public void addHall(HallData hallData) {

		logger.info(StringConstants.ENTER_HALL_NAME);

		hallData.setHallName(inputs.getHallName());

		logger.info(StringConstants.ENTER_HALL_SIZE);

		hallData.setHallSize(inputs.getSize());

		logger.info(StringConstants.HALL_DESCRIPTION);

		hallData.setDescription(inputs.getDescription());

		while (true) {

			logger.info(StringConstants.ADD_FACILITY_PROMPT);

			String res = inputs.promptResult();

			if (res.equals("Y") || res.equals("y")) {

				// displayDAO.display_Facilities();

				ArrayList<String> hallFacilities = hallService.displayHallFacilities();

				for (String f_name : hallFacilities) {

					logger.info(f_name);

				}

				logger.info(StringConstants.ADD_FACILITY);

				facilityId.add(inputs.getFacilityId());

				inputs.promptResult();

			} else {

				break;

			}

		}

		hallData.setFacilityId(facilityId);

		try {
			hallService.hallValues(hallData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void viewHallList() {

		HallService hallService = new HallService();

		ArrayList<HallData> hallList = null;
		try {
			hallList = hallService.hallListService();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int sno = 1;

		for (HallData tempList : hallList) {

			logger.info(sno + StringConstants.PARENTHESIS +

					StringConstants.HALL_ID +

					tempList.getHallId() +

					StringConstants.NEW_LINE +

					StringConstants.HALL_NAME +

					tempList.getHallName() +

					StringConstants.NEW_LINE +

					StringConstants.HALL_SIZE +

					tempList.getHallSize()

			);

			logger.info(StringConstants.HALL_FACILITY);

			ArrayList<String> facilityName = tempList.getFacility_name();

			for (String fName : facilityName) {

				logger.info(fName);
			}
			sno++;

		}

	}

	public void resultViewHallList(HallData hallData, int sno) {

		logger.info(sno + StringConstants.PARENTHESIS +

				StringConstants.NEW_LINE +

				StringConstants.HALL_NAME +

				hallData.getHallName() +

				StringConstants.NEW_LINE +

				StringConstants.HALL_SIZE +

				hallData.getHallSize()

		);

		logger.info(StringConstants.HALL_FACILITY);		

	}

}
