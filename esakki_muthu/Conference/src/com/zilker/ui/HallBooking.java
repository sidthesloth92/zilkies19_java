package com.zilker.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.bean.Availability;
import com.zilker.bean.ConferenceData;
import com.zilker.bean.HallData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.OptionConstants;
import com.zilker.service.DisplayWiseFacilityService;
import com.zilker.service.AddBookingService;
import com.zilker.service.HallService;
import com.zilker.util.Inputs;

public class HallBooking {

	Logger logger = Logger.getLogger(HallBooking.class.getName());

	Scanner in = new Scanner(System.in);

	public void hallBookingOptions(UserData userData) {

		int option;

		boolean bookingControl = true;

		while (bookingControl) {

			logger.info(OptionConstants.BOOKING_OPTIONS);

			option = in.nextInt();

			switch (option) {

			case 1: {

				int result = bookById(userData);

				if (result == 1) {

					logger.info(StringConstants.REQUEST_SUCCESS);

				} else {

					logger.info(StringConstants.REQUEST_ERROR);

				}

				break;
			}

			case 2: {

				int result = bookByFacility(userData);

				if (result == 1) {

					logger.info(StringConstants.REQUEST_SUCCESS);

				} else {

					logger.info(StringConstants.REQUEST_ERROR);

				}

				break;
			}

			case 3:

				Hall hall = new Hall();

				hall.viewHallList();

				break;

			default:

				bookingControl = false;

				break;

			}
		}

	}

	public void hallByList(UserData userData) {

	}

	public int bookById(UserData userData) {

		ConferenceData conferenceData = new ConferenceData();

		logger.info(StringConstants.ENTER_HALL_ID);

		int hall_id = in.nextInt();

		conferenceData.setHallId(hall_id);

		in.nextLine();

		return get_Details(userData, conferenceData);

	}

	public int bookByFacility(UserData userData) {

		Inputs inputs = new Inputs();

		ArrayList<Integer> facilityId = new ArrayList<Integer>();

		while (true) {

			logger.info(StringConstants.ADD_FACILITY_PROMPT);

			String res = inputs.promptResult();

			if (res.equals("Y") || res.equals("y")) {
				
				HallService hallService = new HallService();

				ArrayList<String> hallFacilities = hallService.displayHallFacilities();

				for (String fName : hallFacilities) {

					logger.info(fName);

				}

				logger.info(StringConstants.ADD_FACILITY);

				facilityId.add(inputs.getFacilityId());

				inputs.promptResult();

			} else {

				break;

			}
						

		}		

		ArrayList<HallData> hall_List = new DisplayWiseFacilityService().displayFacility(facilityId);

		int sno = 1;

		for (HallData list : hall_List) {

			logger.info(sno + StringConstants.PARENTHESIS + StringConstants.NEW_LINE +

					StringConstants.HALL_ID + list.getHallId() +

					StringConstants.NEW_LINE +

					StringConstants.HALL_NAME + list.getHallName()
					
					+StringConstants.NEW_LINE +
					
					StringConstants.HALL_SIZE + list.getHallSize()
					
					);

		}

		// hallDatas.setFacility_id(facility_id);
		
		

		return bookById(userData);
	}

	public int get_Details(UserData userData, ConferenceData conferenceData) {

		logger.info(StringConstants.ENTER_CONFERENCE_NAME);

		conferenceData.setConferenceName(in.nextLine());

		logger.info(StringConstants.ENTER_FROM_DATE);

		String from_date = in.nextLine();

		conferenceData.setFromDate(from_date);

		logger.info(StringConstants.ENTER_TO_DATE);

		String to_date = in.nextLine();

		conferenceData.setToDate(to_date);

		logger.info(StringConstants.ENTER_FROM_HOUR);

		String from_hour = in.nextLine();

		conferenceData.setFrom_hour(from_hour);

		logger.info(StringConstants.ENTER_MINUTES);

		String from_min = in.nextLine();

		conferenceData.setFrom_min(from_min);

		logger.info(StringConstants.ENTER_TO_HOUR);

		String to_hour = in.nextLine();

		conferenceData.setTo_hour(to_hour);

		logger.info(StringConstants.ENTER_MINUTES);

		String to_min = in.nextLine();

		conferenceData.setTo_min(to_min);

		conferenceData.setUserId(userData.getUser_id());

		return new AddBookingService().book_By_Id(conferenceData);

	}

}
