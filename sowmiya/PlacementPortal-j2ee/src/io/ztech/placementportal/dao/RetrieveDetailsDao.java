package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class RetrieveDetailsDao {
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Logger log = Logger.getLogger("RetrieveDetailsDao.class");

	public ArrayList<Student> getStudentDetail() throws SQLException {
		log.info("Entering getStudentDetail()");
		Connection connection = DbConnection.getConnection();
		ArrayList<Student> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETSTUDENTDETAIL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setStudentId(resultSet.getString("student_id"));
				student.setName(resultSet.getString("student_name"));
				student.setDepartment(resultSet.getString("department"));
				student.setMarkX(Float.parseFloat(resultSet.getString("mark_x")));
				student.setMarkXII(Float.parseFloat(resultSet.getString("mark_xii")));
				student.setCgpa(Float.parseFloat(resultSet.getString("cgpa")));
				student.setArrearCount(Integer.parseInt(resultSet.getString("arrear_count")));
				list.add(student);
			}
			return list;

		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		log.info("Exiting getStudentDetail()");
		return null;
	}

	public Student getSpecificStudentDetail(String id) {
		Connection connection = DbConnection.getConnection();
		try {
			Student student = new Student();
			preparedStatement = connection.prepareStatement(SqlConstants.GETSTUDENTDETAIL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				student.setStudentId(resultSet.getString("student_id"));
				student.setName(resultSet.getString("student_name"));
				student.setDepartment(resultSet.getString("department"));
				student.setMarkX(Float.parseFloat(resultSet.getString("mark_x")));
				student.setMarkXII(Float.parseFloat(resultSet.getString("mark_xii")));
				student.setCgpa(Float.parseFloat(resultSet.getString("cgpa")));
				student.setArrearCount(Integer.parseInt(resultSet.getString("arrear_count")));
				student.setPlaced(Integer.parseInt(resultSet.getString("isPlaced")));
			}
			return student;

		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

		return null;

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
				profile.setTitle(resultSet.getString(3));
				profile.setDescription(resultSet.getString(4));
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
				companyDetail.setArrearCount(Integer.parseInt(resultSet.getString("arrear_count")));
				companyDetail.setCgpa(Float.parseFloat(resultSet.getString("cgpa")));
				companyDetail.setMarkX(Float.parseFloat(resultSet.getString("mark_x")));
				companyDetail.setMarkXII(Float.parseFloat(resultSet.getString("mark_xii")));
				list.add(companyDetail);
			}
			return list;

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
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
				profile.setTitle(resultSet.getString(3));
				profile.setDescription(resultSet.getString(4));
				profile.setProfileDetailId(Integer.parseInt(resultSet.getString(1)));
			}
			return profile;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return profile;
	}

	public boolean checkIsAvailable(String input, String sql) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, input);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return false;
			} else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
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
				companyDetail.setArrearCount(Integer.parseInt(resultSet.getString("arrear_count")));
				companyDetail.setCgpa(Float.parseFloat(resultSet.getString("cgpa")));
				companyDetail.setMarkX(Float.parseFloat(resultSet.getString("mark_x")));
				companyDetail.setMarkXII(Float.parseFloat(resultSet.getString("mark_xii")));

			}
			return companyDetail;

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

	public ArrayList<Company> getRegistrationLog(Student student) {
		log.info("in getRegistrationLog()");
		Connection connection = DbConnection.getConnection();
		ArrayList<Company> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETREGISTRATIONLOG);
			preparedStatement.setString(1, student.getStudentId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company company = new Company();
				company.setCompanyName(resultSet.getString("company_name"));
				company.setDayOfRecruitment(resultSet.getString("day_of_recruitment"));
				company.setPayment(resultSet.getString("payment"));
				company.setRegisteredDate(resultSet.getString("date_of_registration"));
				list.add(company);
			}
			return list;
		} catch (SQLException e) {
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

		return null;
	}

	public ArrayList<PlacedDetail> getPlacementDetail() throws SQLException {
		log.info("in getPlacementDetail()");
		Connection connection = DbConnection.getConnection();
		ArrayList<PlacedDetail> list = new ArrayList<>();
		PreparedStatement totalCount = null;
		PreparedStatement departmentCount = null;
		ResultSet resultSet1 = null, resultSet2 = null;
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETCOMPANYDETAIL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				totalCount = connection.prepareStatement(SqlConstants.GETCOUNTOFSTUDENTSPLACED);
				totalCount.setInt(1, resultSet.getInt("company_id"));
				resultSet1 = totalCount.executeQuery();
				while (resultSet1.next()) {
					PlacedDetail placement = new PlacedDetail();
					placement.setCompanyId(resultSet.getInt("company_id"));
					placement.setCompanyName(resultSet.getString("company_name"));
					placement.setTotalCount(resultSet1.getInt("count"));
					departmentCount = connection.prepareStatement(SqlConstants.GETDEPARTMENTCOUNT);
					departmentCount.setInt(1, placement.getCompanyId());
					resultSet2 = departmentCount.executeQuery();
					while (resultSet2.next()) {
						if (resultSet2.getString("department").equals("CSE")) {
							placement.setCse(resultSet2.getInt(1));
						} else if (resultSet2.getString("department").equals("ECE")) {
							placement.setEce(resultSet2.getInt(1));

						} else if (resultSet2.getString("department").equals("EEE")) {
							placement.setEee(resultSet2.getInt(1));

						} else if (resultSet2.getString("department").equals("ECE")) {
							placement.setEce(resultSet2.getInt(1));

						} else if (resultSet2.getString("department").equals("EI")) {
							placement.setEi(resultSet2.getInt(1));

						} else if (resultSet2.getString("department").equals("MECH")) {
							placement.setMech(resultSet2.getInt(1));

						}
					}
					list.add(placement);
				}
			}
			return list;

		} catch (SQLException e) {
		}

		return null;
	}

	public ArrayList<Student> getStudentsPlacedInACompany(Company company) {
		log.info("Entering getStudentsPlacedInACompanyDetail()");
		Connection connection = DbConnection.getConnection();
		ArrayList<Student> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETSTUDENTPLACEDINACOMPANYDETAIL);
			preparedStatement.setInt(1, company.getCompanyId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setStudentId(resultSet.getString("student_id"));
				student.setName(resultSet.getString("student_name"));
				student.setDepartment(resultSet.getString("department"));
				student.setMarkX(Float.parseFloat(resultSet.getString("mark_x")));
				student.setMarkXII(Float.parseFloat(resultSet.getString("mark_xii")));
				student.setCgpa(Float.parseFloat(resultSet.getString("cgpa")));
				student.setArrearCount(Integer.parseInt(resultSet.getString("arrear_count")));
				list.add(student);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		log.info("Exiting getStudentsPlacedInACompanyDetail() ");
		return null;
	}

}
