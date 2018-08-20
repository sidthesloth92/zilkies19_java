package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.zilker.bean.ConferenceData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.DBConstants;
import com.zilker.constants.DateConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.util.DatabaseConfig;

public class AddNewBooking {

	DatabaseConfig databaseConfig = new DatabaseConfig();

	Connection connection = null;

	PreparedStatement prepareStmt = null;

	ResultSet resultSet = null;

	Date currentDate = new Date();

	SimpleDateFormat dateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);

	int fromHH, fromMM, toHH, toMM;

	int userFromTime, userToTime;

	int bookedFromTime;

	int bookedToTime;

	Date fromDate = new Date();

	SimpleDateFormat timeFormat = null;

	Calendar calendar = null;

	String fDate, tDate;

	int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0;

	public int bookById(ConferenceData conferenceData) {

		try {

			fDate = conferenceData.getFromDate();

			calendar = Calendar.getInstance();

			Date date = dateFormat.parse(fDate);

			// System.out.println(date.getTime()+"");

			int year = Integer.parseInt(new SimpleDateFormat(DateConstants.YEAR).format(date));

			int month = Integer.parseInt(new SimpleDateFormat(DateConstants.MONTH).format(date));

			int day = Integer.parseInt(new SimpleDateFormat(DateConstants.DAY).format(date));

			calendar.set(Calendar.YEAR, year);

			calendar.set(Calendar.MONTH, month - 1);

			calendar.set(Calendar.DAY_OF_MONTH, day);

			connection = databaseConfig.getConnection();

			prepareStmt = connection.prepareStatement(SqlConstants.GET_CONFERENCE_BY_DATE);

			prepareStmt.setInt(1, conferenceData.getHall_id());

			prepareStmt.setInt(2, 1);

			prepareStmt.setString(3, conferenceData.getFromDate());

			prepareStmt.setString(4, conferenceData.getToDate());

			resultSet = prepareStmt.executeQuery();

			int flag = 0;

			if (resultSet.next()) {

				flag = 1;

				timeFormat = new SimpleDateFormat(DateConstants.HOUR_FORMAT);

				fromHH = Integer.parseInt(timeFormat.format(resultSet.getTime(DBConstants.FROM_TIME)));

				timeFormat = new SimpleDateFormat("mm");

				fromMM = Integer.parseInt(timeFormat.format(resultSet.getTime(DBConstants.FROM_TIME)));

				timeFormat = new SimpleDateFormat("HH");

				toHH = Integer.parseInt(timeFormat.format(resultSet.getTime(DBConstants.FROM_TIME)));

				timeFormat = new SimpleDateFormat("mm");

				toMM = Integer.parseInt(timeFormat.format(resultSet.getTime(DBConstants.FROM_TIME)));

				// to_HH = resultSet.getString("to_hour");

				bookedFromTime = (fromHH * 60) + fromMM;

				bookedToTime = (toHH * 60) + toMM;

				userFromTime = (Integer.parseInt(conferenceData.getFrom_hour()) * 60)
						+ Integer.parseInt(conferenceData.getFrom_min());

				userToTime = (Integer.parseInt(conferenceData.getTohour()) * 60)
						+ Integer.parseInt(conferenceData.getTomin());

				if (bookedFromTime <= userFromTime && userFromTime <= bookedToTime) {
					flag1 = 1;
				}
				if (bookedFromTime <= userToTime && userToTime <= bookedToTime) {
					flag2 = 1;
				}
				if (bookedFromTime >= userFromTime && userToTime >= bookedToTime) {
					flag3 = 1;
				}
				if (bookedFromTime <= userToTime && bookedToTime <= userToTime) {
					flag4 = 1;
				}
				if (flag1 == 0 && flag2 == 0 && flag3 == 0) {
					flag = 0;
				}

			} else {

				flag = 0;

			}

			if (flag == 0) {

				bookHall(conferenceData);

			} else {

				return 0;
			}

		} catch (Exception e) {

			e.printStackTrace();

			try {
				// connection.commit();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();

		} finally {

			databaseConfig.closeConnection(connection, resultSet, prepareStmt);
		}

		return 1;
	}

	public void bookHall(ConferenceData conferenceData) throws SQLException {

		try {

			PreparedStatement get_History_Stmt = connection.prepareStatement(SqlConstants.GET_MAX_CONFERENCE_ID);

			ResultSet max_Set = get_History_Stmt.executeQuery();

			int conference_id = 1;

			if (max_Set.next()) {

				conference_id = max_Set.getInt(DBConstants.CONFERENCE_ID) + 1;

			}

			max_Set.close();

			get_History_Stmt.close();

			PreparedStatement add_History_Stmt = connection.prepareStatement(SqlConstants.ADD_HISTORY);

			add_History_Stmt.setString(1, conferenceData.getConference_name());

			add_History_Stmt.setInt(2, conferenceData.getHall_id());

			add_History_Stmt.setInt(3, conferenceData.getUser_id());

			add_History_Stmt.setString(4, conferenceData.getFromDate());

			add_History_Stmt.setString(5, conferenceData.getToDate());

			add_History_Stmt.setString(6,
					conferenceData.getFrom_hour() + StringConstants.COLON + conferenceData.getFrom_min());

			add_History_Stmt.setString(7,
					conferenceData.getTohour() + StringConstants.COLON + conferenceData.getTomin());

			add_History_Stmt.setInt(8, 0);

			add_History_Stmt.executeUpdate();

			fDate = conferenceData.getFromDate();

			tDate = conferenceData.getToDate();

			PreparedStatement stmt = connection.prepareStatement(SqlConstants.CONFERENCE_DATE);

			stmt.setInt(1, conference_id);

			stmt.setString(2, fDate);

			stmt.setInt(3, conferenceData.getHall_id());

			stmt.executeUpdate();

			stmt.close();

			while (true) {

				if (fDate.equals(tDate)) {

					break;
				}

				calendar.add(Calendar.DATE, 1);

				fromDate = calendar.getTime();

				fDate = dateFormat.format(fromDate);

				PreparedStatement stmt1 = connection.prepareStatement(SqlConstants.CONFERENCE_DATE);

				stmt1.setInt(1, conference_id);

				stmt1.setString(2, fDate);

				stmt1.setInt(3, conferenceData.getHall_id());

				stmt1.executeUpdate();

				stmt1.close();

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

		}

	}

}
