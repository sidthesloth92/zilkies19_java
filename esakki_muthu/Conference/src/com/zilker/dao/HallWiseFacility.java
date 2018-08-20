package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.bean.HallData;
import com.zilker.constants.DBConstants;
import com.zilker.constants.SqlConstants;
import com.zilker.util.DatabaseConfig;

public class HallWiseFacility {

	DatabaseConfig databaseConfig = new DatabaseConfig();

	Connection connection = null;

	PreparedStatement prepareStmt = null;

	ResultSet resultSet = null;

	public ArrayList<HallData> getFacility(ArrayList<Integer> facilityId) throws SQLException {

		ArrayList<HallData> hallList = new ArrayList<HallData>();

		try {

			connection = databaseConfig.getConnection();

			prepareStmt = connection.prepareStatement(SqlConstants.SELECT_ALL_HALL);

			resultSet = prepareStmt.executeQuery();

			while (resultSet.next()) {

				int hallId = resultSet.getInt(DBConstants.HALL_ID);

				PreparedStatement hallWiseStmt = connection.prepareStatement(SqlConstants.LIST_BY_HALL_WISE_FACILITY);

				hallWiseStmt.setInt(1, hallId);

				ResultSet hallSet = hallWiseStmt.executeQuery();

				while (hallSet.next()) {
					
					int count = 0;										

					for(int pos=0;pos<facilityId.size();pos++) {
						
						if(facilityId.get(pos) == hallSet.getInt(DBConstants.FACILITY_ID)) {													
							
							count++;							
						}
						
					}
										
					
					if(count == facilityId.size()) {
						
						HallData hallData = new HallData();
						
						hallData.setFacilityId(facilityId);
						
						hallData.setHallName(hallSet.getString(DBConstants.HALL_NAME));
						
						hallData.setHallId(hallId);
						
						hallData.setHallSize(hallSet.getInt(DBConstants.HALL_SIZE));
						
						hallList.add(hallData);
						
					}
					
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			databaseConfig.closeConnection(connection, resultSet, prepareStmt);

		}

		return hallList;
	}

}
