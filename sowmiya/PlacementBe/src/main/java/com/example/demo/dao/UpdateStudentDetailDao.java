package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.beans.PersonalInfo;
import com.example.demo.beans.PlacedDetail;
import com.example.demo.beans.Student;
import com.example.demo.constants.ApplicationConstants;
import com.example.demo.constants.SqlConstants;
import com.example.demo.dbutil.DbConnection;


public class UpdateStudentDetailDao {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public void updatePlacementDetail(PlacedDetail student) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.PLACED_DETAIL);
			preparedStatement.setString(1, student.getStudentId());
			preparedStatement.setInt(2, student.getCompanyId());
			preparedStatement.setString(3, student.getJobStatus());
			if (preparedStatement.executeUpdate() >= 0) {
				preparedStatement = connection.prepareStatement(SqlConstants.UPDATE_PLACED);
				preparedStatement.setString(1, student.getStudentId());
				preparedStatement.executeUpdate();
			}

		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}


	public boolean updateDetail(Student studentDetail) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.EDIT_STUDENT);
			preparedStatement.setString(1, studentDetail.getName());
			preparedStatement.setString(2, studentDetail.getDepartment());
			preparedStatement.setFloat(3, studentDetail.getMarkX());
			preparedStatement.setFloat(4, studentDetail.getMarkXII());
			preparedStatement.setFloat(5, studentDetail.getCgpa());
			preparedStatement.setInt(6, studentDetail.getArrearCount());
			preparedStatement.setString(7, studentDetail.getStudentId());
			if(preparedStatement.executeUpdate()>=0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;
	}

	public void updatePersonalDetail(PersonalInfo personalInfo) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.UPDATE_PERSONAL_INFO);
			preparedStatement.setString(1, personalInfo.getStudentId());
			preparedStatement.setString(2, personalInfo.getDateOfBirth());
			preparedStatement.setString(3, personalInfo.getBloodGroup());
			preparedStatement.setString(4, personalInfo.getPhoneNumber());
			preparedStatement.setString(5, personalInfo.getAlternatePhone());
			preparedStatement.setString(6, personalInfo.getEmail());
			preparedStatement.setString(7, personalInfo.getAlternateEmail());
			preparedStatement.setString(8, personalInfo.getLocation());
			preparedStatement.setString(9, personalInfo.getGender());
			preparedStatement.executeUpdate();

		} catch (SQLException e1) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}
}