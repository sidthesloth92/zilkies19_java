package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.beans.*;
import com.zilker.config.Config;
import com.zilker.constants.SqlConstants;

public class ResultDetailsDao {

	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public boolean insertResults(ResultData obj) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_RESULT);
			stmt.setLong(1, obj.getStudentRegistrationNumber());
			stmt.setString(2, obj.getSubjectCode());
			stmt.setString(3, obj.getGrade());
			stmt.setInt(4, obj.getWrittenIn());
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	public ArrayList<LinkedHashMap<String, String>> getResultById(long studentRegistrationNumber, int semester,
			int flag) throws SQLException {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			if (flag == 1) {
				stmt = Config.conn.prepareStatement(SqlConstants.GET_RESULTS_BY_ID);
			} else {
				stmt = Config.conn.prepareStatement(SqlConstants.GET_RESULTS_BY_ID_NOT_APPLIED);
			}
			stmt.setLong(1, studentRegistrationNumber);
			stmt.setInt(2, semester);
			rs = stmt.executeQuery();
			while (rs.next()) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("Result Id: ", Integer.toString(rs.getInt("result_id")));
				map.put("Subject Code: ", rs.getString("subject_code"));
				map.put("Grade: ", rs.getString("grade"));
				list.add(map);
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return list;
	}

	public ArrayList<LinkedHashMap<String, String>> getResultByDept(int collegeCode, String dept, int semester)
			throws SQLException {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_RESULT_BY_DEPT);
			stmt.setInt(1, collegeCode);
			stmt.setString(2, dept);
			stmt.setInt(3, semester);
			rs = stmt.executeQuery();
			while (rs.next()) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("Result Id: ", Integer.toString(rs.getInt("result_id")));
				map.put("Subject Code: ", rs.getString("subject_code"));
				map.put("Grade: ", rs.getString("grade"));
				list.add(map);
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return list;
	}
}