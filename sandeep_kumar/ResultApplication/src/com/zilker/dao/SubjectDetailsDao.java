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

public class SubjectDetailsDao {
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public boolean insertSubjectDetails(SubjectData obj) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_SUBJECT);
			stmt.setString(1, obj.getSubjectCode());
			stmt.setString(2, obj.getSubjectName());
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	public boolean deleteSubjectDetails(String subjectCode) throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_SUBJECT);
			stmt.setString(1, subjectCode);
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	public ArrayList<LinkedHashMap<String, String>> getSubjectDetailsById(long facultyRegistrationNumber, int flag)
			throws SQLException {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			if (flag == 1) {
				stmt = Config.conn.prepareStatement(SqlConstants.GET_SUBJECTS_NOT_ENROLLED);
				stmt.setLong(1, facultyRegistrationNumber);
			} else {
				stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_SUBJECT_DETAILS);
			}

			rs = stmt.executeQuery();
			while (rs.next()) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("Subject Code: ", rs.getString("subject_code"));
				map.put("Subject Name: ", rs.getString("subject_name"));
				list.add(map);
			}
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return list;
	}
}
