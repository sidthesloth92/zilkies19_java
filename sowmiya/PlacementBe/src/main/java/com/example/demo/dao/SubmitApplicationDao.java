package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.example.demo.beans.Company;
import com.example.demo.beans.Student;
import com.example.demo.constants.ApplicationConstants;
import com.example.demo.constants.SqlConstants;
import com.example.demo.dbutil.DbConnection;

public class SubmitApplicationDao {

	public boolean submitApplication(Company company, Student studentDetail, String currentDate) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int success;
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.APPLICATION);
			preparedStatement.setString(1, studentDetail.getStudentId());
			preparedStatement.setInt(2, company.getCompanyId());
			preparedStatement.setString(3, currentDate);
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
