package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.constants.DBConstants;
import com.zilker.constants.DateConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.util.DatabaseConfig;

public class GetUserRequests {

	DatabaseConfig databaseConfig = new DatabaseConfig();

	Connection connection = null;

	PreparedStatement prepareStmt = null;

	ResultSet resultSet = null;

	ArrayList<ConferenceData> conferenceList = null;

	public ArrayList<ConferenceData> getRequests(UserData userData) throws SQLException {

		conferenceList = new ArrayList<ConferenceData>();

		connection = databaseConfig.getConnection();

		Date currentDate = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);

		SimpleDateFormat timeFormat = new SimpleDateFormat(DateConstants.TIME_FORMAT);

		try {

			String current_Date = dateFormat.format(currentDate);

			SimpleDateFormat hourFormat = new SimpleDateFormat(DateConstants.HOUR_FORMAT);

			SimpleDateFormat minFormat = new SimpleDateFormat(DateConstants.MIN_FORMAT);

			int current_HH = Integer.parseInt(hourFormat.format(currentDate));

			int current_MM = Integer.parseInt(minFormat.format(currentDate));

			prepareStmt = connection.prepareStatement(SqlConstants.CONFERENCE_REQUEST_BY_USER_ID);

			prepareStmt.setInt(1, userData.getUserId());

			prepareStmt.setString(2, current_Date);

			resultSet = prepareStmt.executeQuery();

			int sno = 1;

			while (resultSet.next()) {

				String date = dateFormat.format(currentDate);

				currentDate = dateFormat.parse(date);

				if (date.equals(resultSet.getString(DBConstants.FROM_DATE))) {

					Date from_time = timeFormat.parse(resultSet.getString(DBConstants.FROM_TIME));

					int conf_HH = Integer.parseInt(hourFormat.format(from_time));

					int conf_MM = Integer.parseInt(minFormat.format(from_time));					
					
					if (current_HH <= conf_HH) {

						if (current_HH == conf_HH) {

							if (current_MM < conf_MM) {

								displayRequests(resultSet, sno);

								sno++;

							}

						} else {

							if (current_HH < conf_HH) {

								displayRequests(resultSet, sno);

								sno++;
							}

						}
					}

				} else {
					
					
					displayRequests(resultSet, sno);

					sno++;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			databaseConfig.closeConnection(connection, resultSet, prepareStmt);
		}

		return conferenceList;

	}

	public void displayRequests(ResultSet resultSet, int sno) throws SQLException {

		try {

			ConferenceData conferenceData = new ConferenceData();

			conferenceData.setConferenceId(resultSet.getInt(DBConstants.CONFERENCE_ID));

			conferenceData.setConferenceName(resultSet.getString(DBConstants.CONFERENCE_NAME));

			conferenceData.setHallId(resultSet.getInt(DBConstants.HALL_ID));

			conferenceData.setHallName(resultSet.getString(DBConstants.HALL_NAME));

			conferenceData.setFromDate(resultSet.getString(DBConstants.FROM_DATE));

			conferenceData.setToDate(resultSet.getString(DBConstants.TO_DATE));

			conferenceData.setFromTime(resultSet.getString(DBConstants.FROM_TIME));

			conferenceData.setToTime(resultSet.getString(DBConstants.TO_TIME));

			conferenceData.setStatus(resultSet.getInt(DBConstants.STATUS));

			conferenceData.setSno(sno);

			conferenceList.add(conferenceData);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
