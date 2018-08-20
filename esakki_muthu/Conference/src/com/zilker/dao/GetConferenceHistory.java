package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.DBConstants;
import com.zilker.constants.DateConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.util.DatabaseConfig;

public class GetConferenceHistory {

	DatabaseConfig databaseConfig = new DatabaseConfig();

	ConferenceData conferenceData = new ConferenceData();

	ArrayList<ConferenceData> datas = new ArrayList<ConferenceData>();

	Connection connection = null;

	PreparedStatement prepareStmt = null;

	ResultSet resultSet = null;

	Date currentDate = new Date();

	Date currentTime = new Date();

	Date confTime = null;

	int currentHH, currentMM;

	SimpleDateFormat dateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);

	SimpleDateFormat timeFormat = null;

	Logger logger = Logger.getLogger(GetConferenceHistory.class.getName());

	public ArrayList<ConferenceData> getHistory(UserData userData) {

		ConferenceData conferenceData = new ConferenceData();

		try {

			timeFormat = new SimpleDateFormat(DateConstants.HOUR_FORMAT);

			int current_HH = Integer.parseInt(timeFormat.format(currentTime));

			timeFormat = new SimpleDateFormat(DateConstants.MIN_FORMAT);

			int current_MM = Integer.parseInt(timeFormat.format(currentTime));

			connection = databaseConfig.getConnection();

			String date = dateFormat.format(currentDate);

			currentDate = dateFormat.parse(date);

			if (userData.getRoleId() == 1) {

				prepareStmt = connection.prepareStatement(SqlConstants.VIEW_HISTORY_BY_USER_ID);

				prepareStmt.setInt(1, 0);

				prepareStmt.setInt(2, userData.getUser_id());

				prepareStmt.setString(3, date);

			} else {

				prepareStmt = connection.prepareStatement(SqlConstants.VIEW_HISTORY);

				prepareStmt.setInt(1, 0);

				prepareStmt.setString(2, date);
			}

			java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

			// System.out.println("hello" + sqlDate);

			resultSet = prepareStmt.executeQuery();

			PreparedStatement hallStmt = null;

			PreparedStatement userStmt = null;

			ResultSet hallSet = null;

			ResultSet userSet = null;

			logger.info(StringConstants.VIEW_REQUESTS_TITLE);

			int pos = 0;

			while (resultSet.next()) {

				conferenceData = new ConferenceData();

				timeFormat = new SimpleDateFormat(DateConstants.TIME_FORMAT);

				SimpleDateFormat hourFormat, minFormat;

				hourFormat = new SimpleDateFormat(DateConstants.HOUR_FORMAT);

				minFormat = new SimpleDateFormat(DateConstants.MIN_FORMAT);

				Date conf_Time = timeFormat.parse(resultSet.getString(DBConstants.FROM_TIME));

				int conf_HH = Integer.parseInt(hourFormat.format(conf_Time));

				// System.out.println(conf_Time + " : ");

				timeFormat = new SimpleDateFormat("mm");

				int conf_MM = Integer.parseInt(minFormat.format(conf_Time));

				if (currentDate.equals(resultSet.getDate(DBConstants.FROM_DATE))) {

					if (current_HH >= conf_HH) {

						if (current_HH == conf_HH) {

							if (current_MM > conf_MM) {

								getRequests(conferenceData, pos, hallStmt, hallSet, userStmt, userSet);

								pos++;
							}

						} else {

							if (current_HH > conf_HH) {

								getRequests(conferenceData, pos, hallStmt, hallSet, userStmt, userSet);

								pos++;

							}

						}
						
					}
				} else {

					getRequests(conferenceData, pos, hallStmt, hallSet, userStmt, userSet);

					pos++;

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				databaseConfig.closeConnection(connection, resultSet, prepareStmt);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

		return datas;
	}

	public void getRequests(ConferenceData conferenceData, int pos, PreparedStatement hallStmt, ResultSet hallSet,
			PreparedStatement userStmt, ResultSet userSet) {

		try {

			conferenceData.setConferenceId(resultSet.getInt(DBConstants.CONFERENCE_ID));

			conferenceData.setConferenceName(resultSet.getString(DBConstants.CONFERENCE_NAME));

			conferenceData.setFromDate(resultSet.getString(DBConstants.FROM_DATE));

			conferenceData.setToDate(resultSet.getString(DBConstants.TO_DATE));

			conferenceData.setFromTime(resultSet.getString(DBConstants.FROM_TIME));

			conferenceData.setToTime(resultSet.getString(DBConstants.TO_TIME));

			conferenceData.setHallId(resultSet.getInt(DBConstants.HALL_ID));

			conferenceData.setUserId(resultSet.getInt(DBConstants.USER_ID));

			conferenceData.setStatus(resultSet.getInt(DBConstants.STATUS));

			hallStmt = connection.prepareStatement(SqlConstants.SELECT_HALL);

			hallStmt.setInt(1, resultSet.getInt(DBConstants.HALL_ID));

			hallSet = hallStmt.executeQuery();

			if (hallSet.next()) {

				conferenceData.setHallName(hallSet.getString(DBConstants.HALL_NAME));

				conferenceData.setHallSize(hallSet.getInt(DBConstants.HALL_SIZE));

			}

			userStmt = connection.prepareStatement(SqlConstants.SELECT_USER);

			userStmt.setInt(1, resultSet.getInt(DBConstants.USER_ID));

			userSet = userStmt.executeQuery();

			if (userSet.next()) {

				conferenceData.setUserName(userSet.getString(DBConstants.USER_NAME));

				conferenceData.setPhoneNo(userSet.getString(DBConstants.PHONE_NO));

				conferenceData.setEmail(userSet.getString(DBConstants.EMAIL));

			}

			datas.add(conferenceData);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				userSet.close();

				hallSet.close();

			} catch (Exception e) {

			}

		}
	}

}
