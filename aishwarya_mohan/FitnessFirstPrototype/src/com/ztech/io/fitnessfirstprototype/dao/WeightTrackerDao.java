package com.ztech.io.fitnessfirstprototype.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ztech.io.fitnessfirstprototype.beans.User;
import com.ztech.io.fitnessfirstprototype.beans.WeightLog;
import com.ztech.io.fitnessfirstprototype.constants.SQLQueryStrings;
import com.ztech.io.fitnessfirstprototype.dbutils.DBConfiguration;

public class WeightTrackerDao {
	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static DBConfiguration connectionObject;

	public WeightTrackerDao() {
		connectionObject = new DBConfiguration();
	}

	public ArrayList<WeightLog> getWeightLog(User user) throws ClassNotFoundException, SQLException {
		ArrayList<WeightLog> logList = new ArrayList<WeightLog>();

		try {
			System.out.println("weight tracker dao");
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_WEIGHT_LOGS);
			ps.setString(1, user.getUsername());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("entry_date");
				float weight = rs.getFloat("weight");

				System.out.println(date + " " + weight);

				WeightLog log = new WeightLog();
				log.setDate(date.toString());
				log.setWeight(weight);

				System.out.println(log.getDate() + " " + log.getWeight());
				logList.add(log);
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return logList;
	}

	public boolean addWeightLog(WeightLog log) throws SQLException, ClassNotFoundException {
		try {
			System.out.println("weight tracker dao");
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.INSERT_WEIGHT_LOGS);
			ps.setString(1, log.getUserName());
			ps.setString(2, log.getDate());
			ps.setFloat(3, log.getWeight());

			int updated = ps.executeUpdate();
			if (updated > 0) {
				return true;
			}

		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	public boolean updateWeight(WeightLog log) throws SQLException, ClassNotFoundException {
		try {
			System.out.println("weight tracker dao");
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.SET_WEIGHT);
			ps.setFloat(1, log.getWeight());
			ps.setString(2, log.getUserName());

			int updated = ps.executeUpdate();
			if (updated > 0) {
				return true;
			}

		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}
}
