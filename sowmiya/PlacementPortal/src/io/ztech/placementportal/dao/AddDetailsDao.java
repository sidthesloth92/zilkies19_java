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
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class AddDetailsDao {
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean addStudnetDetails(Student student, Marks mark) {
		Connection connection = DbConnection.getConnection();
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(SqlConstants.STUDENT);
			ps.setString(1, student.getStudent_id());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getPlaced());
			ps.executeUpdate();
			ps = connection.prepareStatement(SqlConstants.ACADEMIC_DETAILS);
			ps.setString(1, student.getStudent_id());
			ps.setFloat(2, mark.getMark_X());
			ps.setFloat(3, mark.getMark_XII());
			ps.setFloat(4, mark.getCgpa());
			ps.setInt(5, mark.getArrear_count());
			ps.executeUpdate();
			connection.commit();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}

		return false;
	}

	public boolean addCompanyDetail(Company company, Eligiblity eligible) {
		Connection connection = DbConnection.getConnection();
		try {
			int id = 0;
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(SqlConstants.COMPANY);
			ps.setString(1, company.getCompany_name());
			ps.setString(2, company.getCompany_description());
			ps.setString(3, company.getCompany_type());
			ps.setString(4, company.getJob_location());
			ps.setString(5, company.getDesignation());
			ps.setString(6, company.getPayment());
			ps.setString(7, company.getDay_of_recruitment());
			ps.executeUpdate();
			ps = connection.prepareStatement(SqlConstants.GET_RECENT_COMPANY);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			ps = connection.prepareStatement(SqlConstants.ELIGIBLITY);
			ps.setInt(1, id);
			ps.setFloat(2, eligible.getMark_X());
			ps.setFloat(3, eligible.getMark_XII());
			ps.setFloat(4, eligible.getCgpa());
			ps.setInt(5, eligible.getArrear_count());
			ps.executeUpdate();
			connection.commit();
			return true;

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
		return false;
	}

	public boolean addPersonalInfo(PersonalInfo personalInfo) {
		Connection connection = DbConnection.getConnection();
		try {
			ps = connection.prepareStatement(SqlConstants.PERSONAL_INFO);
			ps.setString(1, personalInfo.getStudent_id());
			ps.setString(2, personalInfo.getD_O_B());
			ps.setString(3, personalInfo.getBlood_group());
			ps.setString(4, personalInfo.getPhone_number());
			ps.setString(5, personalInfo.getAlternate_phone());
			ps.setString(6, personalInfo.getEmail());
			ps.setString(7, personalInfo.getAlternate_email());
			ps.setString(8, personalInfo.getLocation());
			ps.setString(9, personalInfo.getGender());
			ps.executeUpdate();
			if (ps.executeUpdate() >= 0) {
				return true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}

		return false;
	}

}
