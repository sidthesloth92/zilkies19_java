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

public class StudentDetailsDao {

	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public boolean insertStudentDetails(StudentData obj) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_STUDENT);
			stmt.setLong(1, obj.getStudentRegistrationNumber());
			stmt.setString(2, obj.getName());
			stmt.setString(3, obj.getCollegeCode());
			stmt.setString(4, obj.getDepartment());
			stmt.setInt(5, obj.getSemester());
			int count = stmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			
		} finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}

	public boolean deleteStudentDetails(long studentRegistrationNumber) {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_STUDENT);
			stmt.setLong(1, studentRegistrationNumber);
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

	public boolean updateStudentDetails(long studentRegistrationNumber, String dataToValidate, int column, int flag) {
		try {
			con = Config.getConnection();
			if (column == 1) {
				stmt = Config.conn.prepareStatement("UPDATE student_details SET ?=? WHERE student_registration_number=?");
			}
			else if (column == 2) {
				stmt = Config.conn.prepareStatement("UPDATE student_details SET studnt_name=? WHERE student_registration_number=?");
			}
			else if (column == 3) {
				stmt = Config.conn.prepareStatement("UPDATE student_details SET department=? WHERE student_registration_number=?");
			}
			else if (column == 4) {
				stmt = Config.conn.prepareStatement("UPDATE student_details SET semester=? WHERE student_registration_number=?");
			}
			if (flag == 1) {
				stmt.setInt(1, Integer.parseInt(dataToValidate));
			} else {
				stmt.setString(1, dataToValidate);
			}

			stmt.setLong(2, studentRegistrationNumber);
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

	public ArrayList<LinkedHashMap<String, String>> getAllStudentDetails() {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_STUDENT_DETAILS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("Registration Number: ", Long.toString(rs.getLong("student_registration_number")));
				map.put("Student Name: ", rs.getString("student_name"));
				map.put("College Code: ", rs.getString("college_code"));
				map.put("Department: ", rs.getString("department"));
				map.put("Semester: ", rs.getString("semester"));
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
