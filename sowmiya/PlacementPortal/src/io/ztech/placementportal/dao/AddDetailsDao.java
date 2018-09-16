package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class AddDetailsDao {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public void addStudnetDetails(Student student, Marks mark) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SqlConstants.STUDENT);
			preparedStatement.setString(1, student.getStudentId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setInt(3, student.getPlaced());
			preparedStatement.setString(4, student.getDepartment());
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(SqlConstants.ACADEMIC_DETAILS);
			preparedStatement.setString(1, student.getStudentId());
			preparedStatement.setFloat(2, mark.getMarkX());
			preparedStatement.setFloat(3, mark.getMarkXII());
			preparedStatement.setFloat(4, mark.getCgpa());
			preparedStatement.setInt(5, mark.getArrearCount());
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			connection.rollback();
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

	public void addCompanyDetail(Company company, Eligiblity eligible) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			int id = 0;
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SqlConstants.COMPANY);
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyDescription());
			preparedStatement.setString(3, company.getCompanyType());
			preparedStatement.setString(4, company.getJobLocation());
			preparedStatement.setString(5, company.getDesignation());
			preparedStatement.setString(6, company.getPayment());
			preparedStatement.setString(7, company.getDayOfRecruitment());
			preparedStatement.setString(8, company.getLastDateForRegistration());
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(SqlConstants.GET_RECENT_COMPANY);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			preparedStatement = connection.prepareStatement(SqlConstants.ELIGIBLITY);
			preparedStatement.setInt(1, id);
			preparedStatement.setFloat(2, eligible.getMarkX());
			preparedStatement.setFloat(3, eligible.getMarkXII());
			preparedStatement.setFloat(4, eligible.getCgpa());
			preparedStatement.setInt(5, eligible.getArrearCount());
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			connection.rollback();
			throw new SQLException(ApplicationConstants.ERROR);

		}
	}

	public void addPersonalInfo(PersonalInfo personalInfo) throws SQLException {
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
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

}
