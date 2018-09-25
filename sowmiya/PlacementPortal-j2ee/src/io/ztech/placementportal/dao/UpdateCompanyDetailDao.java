package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class UpdateCompanyDetailDao {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public boolean updateCompany(Company company) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.EDIT_COMPANY);
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyDescription());
			preparedStatement.setString(3, company.getCompanyType());
			preparedStatement.setString(4, company.getJobLocation());
			preparedStatement.setString(5, company.getDesignation());
			preparedStatement.setString(6, company.getPayment());
			preparedStatement.setString(7, company.getDayOfRecruitment());
			preparedStatement.setFloat(8, company.getCgpa());
			preparedStatement.setFloat(9, company.getMarkX());
			preparedStatement.setFloat(10, company.getMarkXII());
			preparedStatement.setInt(11, company.getArrearCount());
			preparedStatement.setString(12, company.getLastDateForRegistration());
			preparedStatement.setInt(13,company.getCompanyId());
			if (preparedStatement.executeUpdate() >= 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;

	}

}
