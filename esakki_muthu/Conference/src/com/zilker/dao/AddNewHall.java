package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.zilker.bean.HallData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.util.DatabaseConfig;

public class AddNewHall {

	Connection connection = null;

	ResultSet resultSet = null;

	PreparedStatement prepareStmt = null;

	DatabaseConfig databaseConfig = new DatabaseConfig();
	
	Logger logger = Logger.getLogger(AddNewHall.class.getName());

	public void insertHall(HallData hallData) throws SQLException {

		try {

			connection = databaseConfig.getConnection();
			
			prepareStmt = connection.prepareStatement(SqlConstants.SELECT_MAX_HALL_ID);
			
			resultSet = prepareStmt.executeQuery();
			
			if(resultSet.next()) {
				
				int hall_id = resultSet.getInt("hall_id") + 1;
				
				//Add New Hall
				
				prepareStmt = connection.prepareStatement(SqlConstants.ADD_HALL);
				
				prepareStmt.setInt(1,hall_id);
				
				prepareStmt.setString(2, hallData.getHallName());
				
				prepareStmt.setInt(3, hallData.getHallSize());
				
				prepareStmt.executeUpdate();
				
				prepareStmt.close();
				
				//Add Hall Facility
				
				ArrayList<Integer> facility_id = hallData.getFacility_id();
				
				for(int f_id : facility_id) {
					
					prepareStmt = connection.prepareStatement(SqlConstants.ADD_FACILITY);
					
					prepareStmt.setInt(1, hall_id);
					
					prepareStmt.setInt(2, f_id);
					
					prepareStmt.executeUpdate();
					
					prepareStmt.close();
					
				}
				
				logger.info(StringConstants.HALL_ADDED);
				
			}
			

		} catch (Exception e) {
			
			e.printStackTrace();

		} finally {

			databaseConfig.closeConnection(connection, resultSet, prepareStmt);

		}

	}

}
