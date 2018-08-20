package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Eligiblity;
import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class RetrieveDetailsDao {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public Student getStudentDetail(String reg_no) throws SQLException {
		Connection connection = DbConnection.getConnection();
		Student student = new Student();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETSTUDENTDETAIL);
			preparedStatement.setString(1, reg_no);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				student.setStudentId(resultSet.getString("student_id"));
				student.setName(resultSet.getString("student_name"));
				student.setDepartment(resultSet.getString("department"));
			}
			return student;

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

	public Marks getMarkDetail(String reg_no) throws SQLException {
		Connection connection = DbConnection.getConnection();
		Marks mark = new Marks();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETMARKDETAIL);
			preparedStatement.setString(1, reg_no);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				mark.setMarkX(resultSet.getFloat("mark_x"));
				mark.setMarkXII(resultSet.getFloat("mark_xii"));
				mark.setCgpa(resultSet.getFloat("cgpa"));
				mark.setArrearCount(resultSet.getInt("arrear_count"));
			}
			return mark;

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

	public PersonalInfo getPersonalDetail(String reg_no) throws SQLException {
		Connection connection = DbConnection.getConnection();
		PersonalInfo personalInfo = new PersonalInfo();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETPERSONALDETAIL);
			preparedStatement.setString(1, reg_no);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				personalInfo.setDateOfBirth(resultSet.getString("date_of_birth"));
				personalInfo.setGender(resultSet.getString("gender"));
				personalInfo.setBloodGroup(resultSet.getString("blood_group"));
				personalInfo.setPhoneNumber(resultSet.getString("phone_number"));
				personalInfo.setAlternatePhone(resultSet.getString("alternate_phone"));
				personalInfo.setEmail(resultSet.getString("email"));
				personalInfo.setAlternateEmail(resultSet.getString("alternate_email"));
				personalInfo.setLocation(resultSet.getString("location"));
			}
			return personalInfo;

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

	public ArrayList<Profile> getProfileDetail(String reg_no, String sql) throws SQLException {
		Connection connection = DbConnection.getConnection();
		Profile profile;
		ArrayList<Profile> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reg_no);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				profile = new Profile();
				profile.setProfileDetailId(resultSet.getInt(1));
				profile.setTitle(resultSet.getString(2));
				profile.setDescription(resultSet.getString(3));
				list.add(profile);
			}
			return list;

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

	public ArrayList<Company> getCompanyDetail() throws SQLException {
		Connection connection = DbConnection.getConnection();
		ArrayList<Company> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETCOMPANYDETAIL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company companyDetail = new Company();
				companyDetail.setCompanyId(resultSet.getInt("company_id"));
				companyDetail.setCompanyName(resultSet.getString("company_name"));
				companyDetail.setCompanyType(resultSet.getString("company_type"));
				companyDetail.setCompanyDescription(resultSet.getString("company_description"));
				companyDetail.setJobLocation(resultSet.getString("job_location"));
				companyDetail.setPayment(resultSet.getString("payment"));
				companyDetail.setDayOfRecruitment(resultSet.getString("day_of_recruitment"));
				companyDetail.setDesignation(resultSet.getString("designation"));
				companyDetail.setLastDateForRegistration(resultSet.getString("last_date"));
				list.add(companyDetail);
			}
			return list;

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

	public Eligiblity getEligiblityDetail(Company company) {
		Connection connection = DbConnection.getConnection();
		Eligiblity eligiblityDetail = new Eligiblity();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETELIGIBLITYDETAIL);
			preparedStatement.setInt(1, company.getCompanyId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				eligiblityDetail.setMarkX(resultSet.getFloat("percent10"));
				eligiblityDetail.setMarkXII(resultSet.getFloat("percent12"));
				eligiblityDetail.setCgpa(resultSet.getFloat("cgpa"));
				eligiblityDetail.setArrearCount(resultSet.getInt("arrear_count"));
			}
			return eligiblityDetail;

		} catch (SQLException e) {

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return null;
	}

	public ArrayList<Student> getEligiblityList(Company company) throws SQLException {
		Connection connection = DbConnection.getConnection();
		ArrayList<Student> list = new ArrayList<>();
		Student student;
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETELIGIBLITYLISt);
			preparedStatement.setInt(1, company.getCompanyId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				student = new Student();
				student.setStudentId(resultSet.getString("student_id"));
				student.setName(resultSet.getString("student_name"));
				student.setDepartment(resultSet.getString("department"));
				list.add(student);
			}
			return list;
		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

	public Profile viewSpecificProfileDetail(int profileDetailId, String sql) throws SQLException {
		Profile profile = new Profile();
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, profileDetailId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("yes");
				profile.setTitle(resultSet.getString(3));
				profile.setDescription(resultSet.getString(4));
			}
			return profile;
		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

	public boolean checkIsAvailable(String input, String sql) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, input);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.isBeforeFirst())
				return true;
			else
				return false;
		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

	public Company getSpecificCompany(Company companyDetail) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETSPECIFICCOMPANY);
			preparedStatement.setInt(1, companyDetail.getCompanyId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				companyDetail.setCompanyId(resultSet.getInt("company_id"));
				companyDetail.setCompanyName(resultSet.getString("company_name"));
				companyDetail.setCompanyType(resultSet.getString("company_type"));
				companyDetail.setCompanyDescription(resultSet.getString("company_description"));
				companyDetail.setJobLocation(resultSet.getString("job_location"));
				companyDetail.setPayment(resultSet.getString("payment"));
				companyDetail.setDayOfRecruitment(resultSet.getString("day_of_recruitment"));
				companyDetail.setDesignation(resultSet.getString("designation"));
				companyDetail.setLastDateForRegistration(resultSet.getString("last_date"));

			}
			return companyDetail;

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}
}
