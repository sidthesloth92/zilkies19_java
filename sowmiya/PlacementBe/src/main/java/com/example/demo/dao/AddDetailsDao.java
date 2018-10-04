package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.example.demo.beans.Company;
import com.example.demo.beans.PersonalInfo;
import com.example.demo.beans.Profile;
import com.example.demo.beans.Student;
import com.example.demo.constants.ApplicationConstants;
import com.example.demo.constants.SqlConstants;
import com.example.demo.dbutil.DbConnection;

public class AddDetailsDao {
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Logger log;

	public AddDetailsDao() {
		super();
		log = Logger.getLogger("AddDetailsDao.class");
	}

	public boolean addStudnetDetails(Student student) {
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
			log.warning(e.toString());
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;

	}

	public boolean addCompanyDetail(Company company) {
		log.info("Entering addCompanyDetail()");
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
			log.info(e.toString());
		}
		log.info("Exiting addCompanyDetail()");
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

	public boolean addToProfile(Profile profile, String sql) {
		log.info("Entering addToProfile() in AddDetailsDao");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, profile.getStudent_id());
			preparedStatement.setString(2, profile.getTitle());
			preparedStatement.setString(3, profile.getDescription());
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}
		} catch (SQLException e) {
			log.info(e.toString());
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;
	}

}
