package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.config.*;
import com.zilker.constants.SqlConstants;
import com.zilker.beans.*;
public class FacultyDetailsDao{
	
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;
	
	public boolean insertFacultyDetails(FacultyData obj) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_FACULTY);
			stmt.setLong(1, obj.getFacultyRegistrationNumber());
			stmt.setString(2, obj.getName());
			stmt.setString(3, obj.getCollegeCode());
			stmt.setString(4, obj.getDepartment());
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
	
	public boolean deleteFacultyDetails(long facultyRegistrationNumber) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_FACULTY);
			stmt.setLong(1, facultyRegistrationNumber);
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
	
	public boolean updateFacultyDetails(long facultyRegistrationNumber, String dataToValidate, int column, int flag) {
		try {
			con = Config.getConnection();
			if (column == 1) {
				stmt = Config.conn.prepareStatement("UPDATE faculty_details SET ?=? WHERE faculty_registration_number=?");
			}
			else if (column == 2) {
				stmt = Config.conn.prepareStatement("UPDATE faculty_details SET studnt_name=? WHERE faculty_registration_number=?");
			}
			else if (column == 3) {
				stmt = Config.conn.prepareStatement("UPDATE faculty_details SET department=? WHERE faculty_registration_number=?");
			}
			if (flag == 1) {
				stmt.setInt(2, Integer.parseInt(dataToValidate));
			} else {
				stmt.setString(2, dataToValidate);
			}

			stmt.setLong(3, facultyRegistrationNumber);
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

	
	public ArrayList<LinkedHashMap<String, String>> getAllFacultyDetails() {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_FACULTY_DETAILS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("Registration Number: ", Long.toString(rs.getLong("faculty_registration_number")));
				map.put("Student Name: ", rs.getString("faculty_name"));
				map.put("College Code: ", rs.getString("college_code"));
				map.put("Department: ", rs.getString("department"));
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
