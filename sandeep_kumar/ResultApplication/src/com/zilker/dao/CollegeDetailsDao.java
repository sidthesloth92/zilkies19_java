package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.zilker.config.Config;
import com.zilker.constants.SqlConstants;
import com.zilker.beans.*;
public class CollegeDetailsDao {
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection con;

	public boolean insertCollegeDetails(CollegeData obj)throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.INSERT_COLLEGE);
			stmt.setInt(1, obj.getCollegeCode());
			stmt.setString(2, obj.getCollegeName());
			int count=stmt.executeUpdate();
			if(count>0) {
				return true;
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public boolean deleteCollegeDetails(int collegeCode)throws SQLException {
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.DELETE_COLLEGE);
			stmt.setInt(1, collegeCode);
			int count=stmt.executeUpdate();
			if(count>0) {
				return true;
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return false;
	}
	
	public ArrayList<LinkedHashMap<String, String>> getAllCollegeDetails()throws SQLException {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		try {
			con = Config.getConnection();
			stmt = Config.conn.prepareStatement(SqlConstants.GET_ALL_COLLEGE_DETAILS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("College Code: ", rs.getString("college_code"));
				map.put("College Name: ", rs.getString("college_name"));
				list.add(map);
			}
		}finally {
			Config.closeConnection(con, stmt, rs);
		}
		return list;
	}
}
