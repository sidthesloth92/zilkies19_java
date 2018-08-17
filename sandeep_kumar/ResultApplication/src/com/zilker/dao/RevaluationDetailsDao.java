package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.beans.LoggedInUserData;
import com.zilker.config.Config;
import com.zilker.constants.SqlConstants;

public class RevaluationDetailsDao {

	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public boolean applyRevaluation(int resultId) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.APPLY_REVAL);
			stmt.setInt(1, resultId);
			stmt.setString(2, "applied");
			int count=stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	public int findRevaluationCount(LoggedInUserData currentUser) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.REVAL_COUNT);
			stmt.setLong(1, currentUser.getRegistrationNumber());
			stmt.setInt(2, (currentUser.getSemester()) - 1);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return 0;
	}

	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusById(long registrationNumber, int semester) {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_REVAL_DETAILS_BY_STUDENT_ID);
			stmt.setLong(1, registrationNumber);
			stmt.setInt(2, semester);
			rs = stmt.executeQuery();
			while (rs.next()) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("Revaluation Id: ", Integer.toString(rs.getInt("revaluation_id")));
				map.put("Subject Code: ", rs.getString("subject_code"));
				map.put("Status: ", rs.getString("revaluation_status"));
				map.put("Updated Grade: ", rs.getString("updated_grade"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return list;
	}
	
	public ArrayList<LinkedHashMap<String, String>> checkRevaluationStatusBySubject(long registrationNumber,int collegeCode) {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_REVAL_DETAILS_BY_SUBJECT);
			stmt.setInt(1, collegeCode);
			stmt.setLong(2, registrationNumber);
			rs = stmt.executeQuery();
			while (rs.next()) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("Revaluation Id: ", Integer.toString(rs.getInt("revaluation_id")));
				map.put("Subject Code: ", rs.getString("subject_code"));
				map.put("Status: ", rs.getString("revaluation_status"));
				map.put("Updated Grade: ", rs.getString("updated_grade"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return list;
	}

	public boolean changeStatusbyFaculty(int revalId, String status) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_STATUS_BY_FACULTY);
			stmt.setString(1, status);
			stmt.setInt(2, revalId);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public char getCurrentGrade(int revalId) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.CURRENT_GRADE);
			stmt.setInt(1, revalId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("grade").charAt(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return 'o';
	}

	public boolean updateGrade(int revalId, String grade, String status) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.UPDATE_GRADE);
			stmt.setString(1, grade);
			stmt.setString(2, status);
			stmt.setInt(3, revalId);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public ArrayList<LinkedHashMap<String, String>> getApprovedRevaluationList() {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_APPROVED_REVAL_REQUEST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("Revaluation Id: ", Integer.toString(rs.getInt("revaluation_id")));
				map.put("Subject Code: ", rs.getString("subject_code"));
				map.put("Status: ", rs.getString("revaluation_status"));
				map.put("Updated Grade: ", rs.getString("updated_grade"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return list;
	}
}