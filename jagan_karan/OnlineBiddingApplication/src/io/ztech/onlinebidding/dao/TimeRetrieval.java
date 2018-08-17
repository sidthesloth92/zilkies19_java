package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class TimeRetrieval implements SqlQueries, ConstantDisplayStatement, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();
	ArrayList<Date> startEndTimeList = new ArrayList<Date>(2);

	public ArrayList<Date> retreiveTime(String bidItemId) {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement selectTime = databaseConnection.prepareStatement(SELECT_TIME);
			selectTime.setInt(1, Integer.parseInt(bidItemId));
			ResultSet time = selectTime.executeQuery();
			while (time.next()) {
				startEndTimeList.add(time.getTimestamp(DB_STARTTIME));
				startEndTimeList.add(time.getTimestamp(DB_ENDTIME));
			}
			databaseConnection.commit();
		} catch (Exception e) {
			try {
				databaseConnection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
		return startEndTimeList;
	}
}
