package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.zilker.bean.Availability;
import com.zilker.bean.ConferenceData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.DBConstants;
import com.zilker.constants.DateConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.util.DatabaseConfig;

public class AvailabilityChecking {

	DatabaseConfig databaseConfig = new DatabaseConfig();

	Connection connection = null;

	PreparedStatement prepareStmt = null;

	ResultSet resultSet = null;

	Date currentDate = new Date();

	Date toDate = new Date();

	SimpleDateFormat dateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);

	Calendar calendar = null;

	public ArrayList<Availability> getAvailability() {

		ArrayList<Availability> availabilityList = new ArrayList<Availability>();

		connection = databaseConfig.getConnection();

		try {

			calendar = Calendar.getInstance();

			calendar.setTime(currentDate);

			calendar.add(Calendar.DATE, 5);

			toDate = calendar.getTime();

			String fDate = dateFormat.format(currentDate);

			String tDate = dateFormat.format(toDate);

			prepareStmt = connection.prepareStatement(SqlConstants.SELECT_ALL_HALL);

			resultSet = prepareStmt.executeQuery();

			int sno=1;
			
			while (resultSet.next()) {

				Availability availability = new Availability();

				// PreparedStatement
				
				availability.setAvailability_id(sno);

				int hallId = resultSet.getInt(DBConstants.HALL_ID);

				// setting hall id
				availability.setHallId(hallId);
				
				availability.setHallName(resultSet.getString(DBConstants.HALL_NAME));
				
				availability.setHallSize(resultSet.getInt(DBConstants.HALL_SIZE));
				
				ResultSet hallSet = null;

				PreparedStatement hallPrepare = connection.prepareStatement(SqlConstants.CHECK_DATES);

				hallPrepare.setString(1, fDate);

				hallPrepare.setString(2, tDate);

				hallPrepare.setInt(3, 1);

				hallPrepare.setInt(4, hallId);

				hallSet = hallPrepare.executeQuery();
				
				ArrayList<String> dates,times;
				
				dates = new ArrayList<String>();
				
				times = new ArrayList<String>();

				while (hallSet.next()) {

					ResultSet conferenceSet = null;

					int conferenceId = hallSet.getInt(DBConstants.CONFERENCE_ID);
															
					dates.add(hallSet.getString(DBConstants.DATE)+StringConstants.TAB_SPACE+hallSet.getString(DBConstants.FROM_TIME)+StringConstants.DASH+hallSet.getString(DBConstants.TO_TIME));				
										
					availability.setBookedDates(dates);
					
					availability.setBookedTimes(times);
					
				}
				
				availabilityList.add(availability);
				
				sno++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return availabilityList;
	}

}
