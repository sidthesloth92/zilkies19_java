package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.HallData;
import com.zilker.bean.LoginData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.DBConstants;
import com.zilker.constants.DateConstants;
import com.zilker.constants.OptionConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.delegate.RequestsAndHallFacilitiesDelegate;
import com.zilker.delegate.AddHallDelegate;
import com.zilker.util.DatabaseConfig;

public class RequestsAndHallFacilities {

	DatabaseConfig databaseConfig = new DatabaseConfig();

	ConferenceData conferenceData = new ConferenceData();

	ArrayList<ConferenceData> datas = new ArrayList<ConferenceData>();

	Connection connection = null;

	PreparedStatement prepareStmt = null;

	ResultSet resultSet = null;

	Date currentDate = new Date();

	Date currentTime = new Date();

	Date confTime = null;

	int curHH, curMM;

	SimpleDateFormat dateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);

	SimpleDateFormat timeFormat = null;

	Logger logger = Logger.getLogger(RequestsAndHallFacilities.class.getName());

	public ArrayList<ConferenceData> fetchRequests(UserData userData) throws SQLException {

		ConferenceData conferenceData = new ConferenceData();

		try {

			timeFormat = new SimpleDateFormat(DateConstants.HOUR_FORMAT);

			int currentHH = Integer.parseInt(timeFormat.format(currentTime));

			timeFormat = new SimpleDateFormat(DateConstants.MIN_FORMAT);

			int current_MM = Integer.parseInt(timeFormat.format(currentTime));

			connection = databaseConfig.getConnection();

			prepareStmt = connection.prepareStatement(SqlConstants.VIEW_ALL_REQUESTS);

			String date = dateFormat.format(currentDate);

			currentDate = dateFormat.parse(date);

			java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

			prepareStmt.setInt(1, 0);

			prepareStmt.setDate(2, sqlDate);

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

				Date confTime = timeFormat.parse(resultSet.getString(DBConstants.FROM_TIME));

				int confHH = Integer.parseInt(hourFormat.format(confTime));

				timeFormat = new SimpleDateFormat(DateConstants.MIN_FORMAT);

				int confMM = Integer.parseInt(minFormat.format(confTime));

				if (currentDate.equals(resultSet.getDate(DBConstants.FROM_DATE))) {

					if (currentHH == confHH) {

						if (current_MM < confMM) {

							getConferenceRequests(conferenceData, pos, hallStmt, hallSet, userStmt, userSet);

							pos++;
						}

					} else {

						if (currentHH < confHH) {

							getConferenceRequests(conferenceData, pos, hallStmt, hallSet, userStmt, userSet);

							pos++;

						}

					}
				} else {

					getConferenceRequests(conferenceData, pos, hallStmt, hallSet, userStmt, userSet);

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

	public ArrayList<String> getFacilitiyDetails() {

		ArrayList<String> facilityName = new ArrayList<String>();

		try {

			connection = databaseConfig.getConnection();

			prepareStmt = connection.prepareStatement(SqlConstants.FACILITY_LIST);

			resultSet = prepareStmt.executeQuery();

			int pos = 1;

			while (resultSet.next()) {

				facilityName.add(resultSet.getInt(DBConstants.FACILITY_ID) + StringConstants.PARENTHESIS
						+ resultSet.getString(DBConstants.FACILITY_NAME));

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			databaseConfig.closeConnection(connection, resultSet, prepareStmt);
		}

		return facilityName;

	}

	public ArrayList<HallData> getHallsWithFacility() throws SQLException {

		connection = databaseConfig.getConnection();

		ArrayList<HallData> hallList = new ArrayList<HallData>();

		try {

			prepareStmt = connection.prepareStatement(SqlConstants.SELECT_ALL_HALL);

			resultSet = prepareStmt.executeQuery();

			int sno = 1;

			while (resultSet.next()) {

				HallData hallDatas = new HallData();

				ArrayList<String> facilityName = new ArrayList<String>();

				int hallId = resultSet.getInt(DBConstants.HALL_ID);

				hallDatas.setHallId(hallId);

				hallDatas.setHallName(resultSet.getString(DBConstants.HALL_NAME));

				hallDatas.setHallSize(resultSet.getInt(DBConstants.HALL_SIZE));

				PreparedStatement facilityIdPrepare = connection.prepareStatement(SqlConstants.HALL_WISE_FACILITY);

				facilityIdPrepare.setInt(1, hallId);

				ResultSet facilityIdSet = facilityIdPrepare.executeQuery();

				while (facilityIdSet.next()) {

					int facilityId = facilityIdSet.getInt(DBConstants.FACILITY_ID);

					PreparedStatement facilityNamePrepare = connection
							.prepareStatement(SqlConstants.FACILITY_WISE_ID);

					facilityNamePrepare.setInt(1, facilityId);

					ResultSet facilityNameSet = facilityNamePrepare.executeQuery();

					if (facilityNameSet.next()) {

						facilityName.add(facilityNameSet.getString(DBConstants.FACILITY_NAME));

					}

					facilityNameSet.close();

					facilityNamePrepare.close();

				}
				hallDatas.setFacility_name(facilityName);

				facilityIdSet.close();

				hallList.add(hallDatas);

				sno++;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			databaseConfig.closeConnection(connection, resultSet, prepareStmt);
		}

		return hallList;
	}

	public void getConferenceRequests(ConferenceData conferenceData, int pos, PreparedStatement hallStmt, ResultSet hallSet,
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
