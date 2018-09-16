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
			preparedStatement.setInt(8, company.getCompanyId());
			if (preparedStatement.executeUpdate() >= 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;

	}

}
