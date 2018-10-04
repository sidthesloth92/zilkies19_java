package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class AddDetailsDao {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public boolean addStudnetDetails(Student student) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.STUDENT);
			preparedStatement.setString(1, student.getStudentId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setInt(3, student.getPlaced());
			preparedStatement.setString(4, student.getDepartment());
			preparedStatement.setFloat(5, student.getMarkX());
			preparedStatement.setFloat(6, student.getMarkXII());
			preparedStatement.setFloat(7, student.getCgpa());
			preparedStatement.setInt(8, student.getArrearCount());
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;

	}

	public boolean addCompanyDetail(Company company) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.COMPANY);
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyDescription());
			preparedStatement.setString(3, company.getCompanyType());
			preparedStatement.setString(4, company.getJobLocation());
			preparedStatement.setString(5, company.getDesignation());
			preparedStatement.setString(6, company.getPayment());
			preparedStatement.setString(7, company.getDayOfRecruitment());
			preparedStatement.setString(8, company.getLastDateForRegistration());
			preparedStatement.setFloat(9, company.getCgpa());
			preparedStatement.setFloat(10, company.getMarkX());
			preparedStatement.setFloat(11, company.getMarkXII());
			preparedStatement.setInt(12, company.getArrearCount());
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}

		} catch (Exception e) {
			throw new SQLException(ApplicationConstants.ERROR);

		}
		return false;
	}

	public boolean addPersonalInfo(PersonalInfo personalInfo) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.PERSONAL_INFO);
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
			return true;
		} catch (SQLException e1) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

}
