package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.config.Config;
import com.zilker.constants.SqlConstants;

public class FacultySubjectDetailsDao {
	
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;
	
	public boolean insertFacultySubjectDetails(long facultyRegistrationNumber, String subjectCode) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_FACULTY_SUBJECT);
			stmt.setLong(1, facultyRegistrationNumber);
			stmt.setString(2, subjectCode);
			int count=stmt.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public boolean deleteFacultySubjectDetails(int facultySubjectId) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_FACULTY_SUBJECT);
			stmt.setInt(1, facultySubjectId);
			int count=stmt.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public ArrayList<LinkedHashMap<String, String>> getFacultySubjectDetailsById(long facultyRegistrationNumber) {
		ArrayList<LinkedHashMap<String, String>> list=new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_SUBJECTS_ENROLLED);
			stmt.setLong(1, facultyRegistrationNumber);
			rs = stmt.executeQuery();
			while (rs.next()){
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("Id: ", rs.getString("faculty_subject_id"));
				map.put("Subject Code: ", rs.getString("subject_code"));
				map.put("Subject Name: ", rs.getString("subject_name"));
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
