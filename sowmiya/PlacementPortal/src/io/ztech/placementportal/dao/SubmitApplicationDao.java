package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class SubmitApplicationDao {

	public boolean submitApplication(Company company, String reg_no, LocalDate date) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int success;
		try {
			ps = connection.prepareStatement(SqlConstants.APPLICATION);
			ps.setString(1, reg_no);
			ps.setInt(2, company.getCompany_id());
			ps.setString(3, date.toString());
			success = ps.executeUpdate();
			if (success >= 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}

		return false;
	}

}
