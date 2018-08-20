package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.zilker.bean.ConferenceData;
import com.zilker.bean.UserData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.DBConstants;
import com.zilker.constants.DateConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.util.DatabaseConfig;

public class FetchConferenceDetails {

	DatabaseConfig databaseConfig = new DatabaseConfig();

	ConferenceData conferenceData = new ConferenceData();

	Connection connection = null;

	PreparedStatement prepareStmt = null;

	ResultSet resultSet = null;

	Date currentDate = new Date();

	Date currentTime = new Date();

	Date confTime = null;

	int currentHH, currentMM;

	SimpleDateFormat dateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);

	SimpleDateFormat timeFormat = null;

	public ConferenceData getDetails(int conference_id) {

		try {

			timeFormat = new SimpleDateFormat(DateConstants.HOUR_FORMAT);

			int currentHH1 = Integer.parseInt(timeFormat.format(currentTime));

			timeFormat = new SimpleDateFormat(DateConstants.MIN_FORMAT);

			int currentMM1 = Integer.parseInt(timeFormat.format(currentTime));

			connection = databaseConfig.getConnection();

			prepareStmt = connection.prepareStatement(SqlConstants.VIEW_REQUEST_BY_ID);

			String date = dateFormat.format(currentDate);

			currentDate = dateFormat.parse(date);

			java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
			
			prepareStmt.setInt(1, 0);

			prepareStmt.setInt(2, conference_id);

			prepareStmt.setDate(3, sqlDate);

			//prepareStmt.setDate(4, sqlDate);		

			resultSet = prepareStmt.executeQuery();

			PreparedStatement hallStmt = null;

			PreparedStatement userStmt = null;

			ResultSet hallSet = null;

			ResultSet userSet = null;

			// logger.info(Labels.VIEW_REQUESTS_TITLE);

			int pos = 0;

			if (resultSet.next()) {

				conferenceData = new ConferenceData();

				timeFormat = new SimpleDateFormat(DateConstants.TIME_FORMAT);

				SimpleDateFormat hourFormat, minFormat;

				hourFormat = new SimpleDateFormat(DateConstants.HOUR_FORMAT);

				minFormat = new SimpleDateFormat(DateConstants.MIN_FORMAT);

				Date conf_Time = timeFormat.parse(resultSet.getString(DBConstants.FROM_TIME));

				int conf_HH = Integer.parseInt(hourFormat.format(conf_Time)); 

				timeFormat = new SimpleDateFormat(DateConstants.MIN_FORMAT);

				int conf_MM = Integer.parseInt(minFormat.format(conf_Time));				

				if (currentDate.equals(resultSet.getDate(DBConstants.FROM_DATE))) {

					if (currentHH1 == conf_HH) {

						if (currentMM1 < conf_MM) {

							getRequests(hallStmt, hallSet, userStmt, userSet);

							pos++;

							conferenceData.setFound(1);

						}

					} else {

						if (currentHH1 < conf_HH) {

							getRequests(hallStmt, hallSet, userStmt, userSet);

							pos++;

							conferenceData.setFound(1);

						}

					}
				} else {

					getRequests(hallStmt, hallSet, userStmt, userSet);

					pos++;

					conferenceData.setFound(1);

				}

			} else {

				conferenceData.setFound(0);

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

		return conferenceData;
	}	

	public void getRequests(PreparedStatement hallStmt, ResultSet hallSet, PreparedStatement userStmt,
			ResultSet userSet) {

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
	
	public int setAcceptOrDecline(UserData userData,int conferenceId,int option) {
		
		connection = databaseConfig.getConnection();
		
		try {
			
			prepareStmt = connection.prepareStatement(SqlConstants.UPDATE_REQUEST);
			
			prepareStmt.setInt(1,userData.getUser_id());
			
			prepareStmt.setInt(2, option);
			
			prepareStmt.setInt(3, conferenceId);
			
			prepareStmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			databaseConfig.closeConnection(connection, resultSet, prepareStmt);
		}
		
		return 0;
	}

}
