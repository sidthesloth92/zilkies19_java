package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class RetrieveDetailsDao {
	PreparedStatement ps = null;
	ResultSet rs = null;

	public HashMap<String, String> getStudentDetail(String reg_no) {
		Connection connection = DbConnection.getConnection();
		HashMap<String, String> map = new LinkedHashMap<>();
		try {
			ps = connection.prepareStatement(SqlConstants.GETSTUDENTDETAIL);
			ps.setString(1, reg_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(ApplicationConstants.STUDENT_NAME, rs.getString("student_name"));
				map.put(ApplicationConstants.DEPARTMENT, rs.getString("department"));
				map.put(ApplicationConstants.PLACEMENT_STATUS, rs.getString("isPlaced"));
			}
			return map;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public HashMap<String, Float> getMarkDetail(String reg_no) {
		Connection connection = DbConnection.getConnection();
		HashMap<String, Float> map = new LinkedHashMap<>();
		try {
			ps = connection.prepareStatement(SqlConstants.GETMARKDETAIL);
			ps.setString(1, reg_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(ApplicationConstants.PERCENTAGE_X, rs.getFloat("mark_x"));
				map.put(ApplicationConstants.PERCENTAGE_XII, rs.getFloat("mark_xii"));
				map.put(ApplicationConstants.CGPA, rs.getFloat("cgpa"));
				map.put(ApplicationConstants.ARREAR_COUNT, (float) rs.getInt("arrear_count"));
			}
			return map;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public HashMap<String, String> getPersonalDetail(String reg_no) {
		Connection connection = DbConnection.getConnection();
		HashMap<String, String> map = new LinkedHashMap<>();
		try {
			ps = connection.prepareStatement(SqlConstants.GETPERSONALDETAIL);
			ps.setString(1, reg_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(ApplicationConstants.D_O_B, rs.getString("date_of_birth"));
				map.put(ApplicationConstants.GENDER, rs.getString("gender"));
				map.put(ApplicationConstants.BLOOD_GROUP, rs.getString("blood_group"));
				map.put(ApplicationConstants.PHONE_NUMBER, rs.getString("phone_number"));
				map.put(ApplicationConstants.ALTERNATE_PHONE, rs.getString("alternate_phone"));
				map.put(ApplicationConstants.EMAIL_ID, rs.getString("email"));
				map.put(ApplicationConstants.ALTERNATE_EMAIL_ID, rs.getString("alternate_email"));
				map.put(ApplicationConstants.LOCATION, rs.getString("location"));
			}
			return map;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public ArrayList<HashMap<String, String>> getAcheivementDetail(String reg_no) {
		Connection connection = DbConnection.getConnection();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try {
			ps = connection.prepareStatement(SqlConstants.GETACHIEVEMENT);
			ps.setString(1, reg_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new LinkedHashMap<>();
				map.put(ApplicationConstants.ACHIEVEMENT_ID, rs.getString("achievement_id"));
				map.put(ApplicationConstants.TITLE, rs.getString("title"));
				map.put(ApplicationConstants.DESCRPTION, rs.getString("description"));
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public ArrayList<HashMap<String, String>> getProjectDetail(String reg_no) {
		Connection connection = DbConnection.getConnection();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try {
			ps = connection.prepareStatement(SqlConstants.GETPROJECT);
			ps.setString(1, reg_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new LinkedHashMap<>();
				map.put(ApplicationConstants.PROJECT_ID, rs.getString("project_id"));
				map.put(ApplicationConstants.PROJECT_TITLE, rs.getString("project_title"));
				map.put(ApplicationConstants.DESCRPTION, rs.getString("descrption"));
				list.add(map);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public ArrayList<HashMap<String, String>> getCourseDetail(String reg_no) {
		Connection connection = DbConnection.getConnection();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try {
			ps = connection.prepareStatement(SqlConstants.GETCOURSES);
			ps.setString(1, reg_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new LinkedHashMap<>();
				map.put(ApplicationConstants.COURSE_ID, rs.getString("project_id"));
				map.put(ApplicationConstants.COURSE_TITLE, rs.getString("course_title"));
				map.put(ApplicationConstants.CERTIFIED_BY, rs.getString("certified_institution"));
				list.add(map);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public ArrayList<HashMap<String, String>> getCompanyDetail() {
		Connection connection = DbConnection.getConnection();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try {
			ps = connection.prepareStatement(SqlConstants.GETCOMPANYDETAIL);
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new LinkedHashMap<>();
				map.put(ApplicationConstants.COMPANY_ID, rs.getString("company_id"));
				map.put(ApplicationConstants.COMPANY_NAME, rs.getString("company_name"));
				map.put(ApplicationConstants.COMPANY_TYPE, rs.getString("company_description"));
				map.put(ApplicationConstants.COMPANY_DESCRIPTION, rs.getString("company_description"));
				map.put(ApplicationConstants.JOB_LOCATION, rs.getString("job_location"));
				map.put(ApplicationConstants.PAYMENT, rs.getString("payment"));
				map.put(ApplicationConstants.RECRUITMENT__DATE, rs.getString("day_of_recruitment"));
				map.put(ApplicationConstants.DESIGNATION, rs.getString("designation"));
				list.add(map);

			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public HashMap<String, Float> getEligiblityDetail(Company company) {
		Connection connection = DbConnection.getConnection();
		HashMap<String, Float> map = new LinkedHashMap<>();
		try {
			ps = connection.prepareStatement(SqlConstants.GETELIGIBLITYDETAIL);
			ps.setInt(1, company.getCompany_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(ApplicationConstants.PERCENTAGE_X, rs.getFloat("percent10"));
				map.put(ApplicationConstants.PERCENTAGE_XII, rs.getFloat("percent12"));
				map.put(ApplicationConstants.CGPA, rs.getFloat("cgpa"));
				map.put(ApplicationConstants.ARREAR_COUNT, (float) rs.getInt("arrear_count"));
			}
			return map;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public ArrayList<HashMap<String, String>> getEligiblityList(Company company) {
		Connection connection = DbConnection.getConnection();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try {
			ps = connection.prepareStatement(SqlConstants.GETELIGIBLITYLISt);
			ps.setInt(1, company.getCompany_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new LinkedHashMap<>();
				map.put(ApplicationConstants.REGISTER_NO, rs.getString("student_id"));
				map.put(ApplicationConstants.NAME, rs.getString("student_name"));
				map.put(ApplicationConstants.DEPARTMENT, rs.getString("department"));
				list.add(map);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public ArrayList<HashMap<String, String>> viewProfile(String reg_no, String sql) {
		Connection connection = DbConnection.getConnection();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, reg_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new LinkedHashMap<>();
				map.put(ApplicationConstants.ID, rs.getString(1));
				map.put(ApplicationConstants.TITLE, rs.getString(3));
				map.put(ApplicationConstants.DESCRPTION, rs.getString(4));
				list.add(map);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;
	}

	public HashMap<String, String> viewSpecificProfileDetail(int profileDetailId, String sql) {
		HashMap<String, String> profile = new HashMap<>();
		Connection connection = DbConnection.getConnection();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, profileDetailId);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("yes");
				profile.put(ApplicationConstants.TITLE, rs.getString(3));
				profile.put(ApplicationConstants.DESCRPTION, rs.getString(4));
			}
			return profile;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}

		return null;
	}
}
