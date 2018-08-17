package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class UpdateCompanyDetailDao {
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean updateCompany(Company company) {
		Connection connection = DbConnection.getConnection();
		try {
			ps = connection.prepareStatement(SqlConstants.EDIT_COMPANY);
			ps.setString(1, company.getCompany_name());
			ps.setString(2, company.getCompany_description());
			ps.setString(3, company.getCompany_type());
			ps.setString(4, company.getJob_location());
			ps.setString(5, company.getDesignation());
			ps.setString(6, company.getPayment());
			ps.setString(7, company.getDay_of_recruitment());
			ps.setInt(8, company.getCompany_id());
			if (ps.executeUpdate() >= 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return false;

	}

}
