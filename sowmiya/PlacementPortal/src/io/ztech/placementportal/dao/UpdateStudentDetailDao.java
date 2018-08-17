package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class UpdateStudentDetailDao {
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean updatePlacementDetail(PlacedDetail student) {
		Connection connection = DbConnection.getConnection();
		try {
			ps = connection.prepareStatement(SqlConstants.PLACED_DETAIL);
			ps.setString(1, student.getStudent_id());
			ps.setInt(2, student.getCompany_id());
			ps.setString(3, student.getJobStatus());
			if (ps.executeUpdate() >= 0) {
				ps = connection.prepareStatement(SqlConstants.UPDATE_PLACED);
				ps.setString(1, student.getStudent_id());
				if (ps.executeUpdate() >= 0)
					return true;
				else
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return false;

	}

	public boolean updateMarkDetail(Marks mark) {
		Connection connection = DbConnection.getConnection();
		try {
			ps = connection.prepareStatement(SqlConstants.EDITED_MARK_DETAIL);
			ps.setFloat(1, mark.getMark_X());
			ps.setFloat(2, mark.getMark_XII());
			ps.setFloat(3, mark.getCgpa());
			ps.setInt(4, mark.getArrear_count());
			ps.setString(5, mark.getStudent_id());
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

	public boolean updateMarkDetail(Student studentDetail) {
		Connection connection = DbConnection.getConnection();
		try {
			ps = connection.prepareStatement(SqlConstants.EDITED_DETAIL);
			ps.setString(1, studentDetail.getName());
			ps.setString(2, studentDetail.getDepartment());
			ps.setString(3, studentDetail.getStudent_id());
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

	public boolean updatePersonalDetail(PersonalInfo personalInfo) {
		Connection connection = DbConnection.getConnection();
		try {
			ps = connection.prepareStatement(SqlConstants.UPDATE_PERSONAL_INFO);
			ps.setString(1, personalInfo.getD_O_B());
			ps.setString(2, personalInfo.getBlood_group());
			ps.setString(3, personalInfo.getPhone_number());
			ps.setString(4, personalInfo.getAlternate_phone());
			ps.setString(5, personalInfo.getEmail());
			ps.setString(6, personalInfo.getAlternate_email());
			ps.setString(7, personalInfo.getLocation());
			ps.setString(8, personalInfo.getGender());
			ps.setString(9, personalInfo.getStudent_id());
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