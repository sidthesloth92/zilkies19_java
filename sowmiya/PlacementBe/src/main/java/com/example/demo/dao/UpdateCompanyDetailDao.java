package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.example.demo.beans.Company;
import com.example.demo.constants.SqlConstants;
import com.example.demo.dbutil.DbConnection;

public class UpdateCompanyDetailDao {
	private Logger log;

	public UpdateCompanyDetailDao() {
		log = Logger.getLogger("UpdateCompanyDetailDao.class");
	}

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public boolean updateCompany(Company company) {
		log.info("Entering updateCompany()");
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
			preparedStatement.setInt(13, company.getCompanyId());
			if (preparedStatement.executeUpdate() >= 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			log.warning(e.toString());
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		log.info("Exiting updateCompany()");
		return false;

	}

}
