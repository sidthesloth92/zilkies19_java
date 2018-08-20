package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class SubmitApplicationDao {

	public boolean submitApplication(Company company, String reg_no, LocalDate date) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int success;
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.APPLICATION);
			preparedStatement.setString(1, reg_no);
			preparedStatement.setInt(2, company.getCompanyId());
			preparedStatement.setString(3, date.toString());
			success = preparedStatement.executeUpdate();
			if (success >= 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

}
