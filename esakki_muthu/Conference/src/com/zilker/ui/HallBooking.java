package com.zilker.ui;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

		int hallId = in.nextInt();

		conferenceData.setHallId(hallId);

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

		ArrayList<HallData> hallList = null;
		try {
			hallList = new DisplayWiseFacilityService().displayFacility(facilityId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int sno = 1;

		for (HallData list : hallList) {

			logger.info(sno + StringConstants.PARENTHESIS + StringConstants.NEW_LINE +

					StringConstants.HALL_ID + list.getHallId() +

					StringConstants.NEW_LINE +

					StringConstants.HALL_NAME + list.getHallName()

					+ StringConstants.NEW_LINE +

					StringConstants.HALL_SIZE + list.getHallSize()

			);

		}

		// hallDatas.setFacility_id(facility_id);

		return bookById(userData);
	}

	public int get_Details(UserData userData, ConferenceData conferenceData) {

		Inputs inputs = new Inputs();

		logger.info(StringConstants.ENTER_CONFERENCE_NAME);

		conferenceData.setConferenceName(in.nextLine());

		while (true) {

			logger.info(StringConstants.ENTER_FROM_DATE);

			String fromDate = inputs.dateInput();

			conferenceData.setFromDate(fromDate);

			logger.info(StringConstants.ENTER_TO_DATE);

			String toDate = inputs.dateInput();

			conferenceData.setToDate(toDate);

			logger.info(StringConstants.ENTER_FROM_HOUR);

			String fromHour = in.nextLine();

			conferenceData.setFrom_hour(fromHour);

			logger.info(StringConstants.ENTER_MINUTES);

			String fromMin = in.nextLine();

			conferenceData.setFrom_min(fromMin);

			logger.info(StringConstants.ENTER_TO_HOUR);

			String toHour = in.nextLine();

			conferenceData.setTo_hour(toHour);

			logger.info(StringConstants.ENTER_MINUTES);

			String toMin = in.nextLine();

			conferenceData.setTo_min(toMin);

			if (validateDates(conferenceData)) {

				break;
			}
		}

		conferenceData.setUserId(userData.getUserId());

		// return 0;
		return new AddBookingService().bookById(conferenceData);

	}

	public boolean validateDates(ConferenceData conferenceData) {

		Date currentDate = new Date();

		Date date = new Date();

		String fromDateString = conferenceData.getFromDate();

		System.out.println(fromDateString);

		int fromDateYear = Integer.parseInt(fromDateString.substring(0, 4));

		int fromDateMonth = Integer.parseInt(fromDateString.substring(5, 7));

		int fromDateDay = Integer.parseInt(fromDateString.substring(8));

		Calendar cal = Calendar.getInstance();

		cal.set(fromDateYear, fromDateMonth - 1, fromDateDay, Integer.parseInt(conferenceData.getFrom_hour()),
				Integer.parseInt(conferenceData.getFrom_min()));

		System.out.println(cal.getTime());

		currentDate = cal.getTime();

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");
		System.out.println(simpleDate.format(date) + " " + simpleDate.format(currentDate));

		System.out.println(currentDate.getTime() + "from " + date.getTime() + " current");

		if (currentDate.getTime() > date.getTime()) {

			String toDateString = conferenceData.getToDate();

			System.out.println(fromDateString);

			int toDateYear = Integer.parseInt(toDateString.substring(0, 4));

			int toDateMonth = Integer.parseInt(toDateString.substring(5, 7));

			int toDateDay = Integer.parseInt(toDateString.substring(8));

			Calendar cal2 = Calendar.getInstance();

			cal2.set(toDateYear, toDateMonth - 1, toDateDay, Integer.parseInt(conferenceData.getTohour()),
					Integer.parseInt(conferenceData.getTomin()));

			Date toDate = cal2.getTime();

			if (toDate.getTime() > currentDate.getTime()) {
				System.out.println(toDate.getTime() + "to" + currentDate.getTime() + "from");
				return true;
			}

			return false;
		} else {
			return false;
		}

	}

}
