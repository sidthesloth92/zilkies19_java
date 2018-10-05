package io.ztech.jkingsley.hrmanagement.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.ztech.jkingsley.hrmanagement.beans.objects.Employee;
import io.ztech.jkingsley.hrmanagement.beans.types.AccountType;
import io.ztech.jkingsley.hrmanagement.beans.types.BloodGroup;
import io.ztech.jkingsley.hrmanagement.beans.types.EmployeeStatus;
import io.ztech.jkingsley.hrmanagement.beans.types.Gender;
import io.ztech.jkingsley.hrmanagement.beans.types.MaritalStatus;
import io.ztech.jkingsley.hrmanagement.beans.types.Qualification;
import io.ztech.jkingsley.hrmanagement.constants.Fields;
import io.ztech.jkingsley.hrmanagement.constants.Query;
import io.ztech.jkingsley.hrmanagement.utils.DBUtils;
import io.ztech.jkingsley.hrmanagement.utils.PersistenceException;

public class EmployeeDAO {
	/*
	 * BigInteger emp_id; String emp_name; String password; AccountType
	 * account_type; Gender gender; MaritalStatus marital_status; BigInteger
	 * designation_id; Date dob; Date doj; Qualification highest_qualification;
	 * BloodGroup bloodGroup; String pan; String aadhar; String uan; String
	 * present_address; String permanent_address; EmployeeStatus emp_status;
	 */

	public BigInteger addEmployee(Employee employee) throws PersistenceException {

		BigInteger emp_id = BigInteger.ZERO;
		Connection connection = DBUtils.getConnection();

		

		ResultSet generatedKeys = null;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_EMPLOYEE,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, employee.getEmp_name());

			String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(employee.getEmp_password());

			preparedStatement.setString(2, sha256hex);
			preparedStatement.setString(3, employee.getAccount_type().toString());
			preparedStatement.setString(4, employee.getGender().toString());
			preparedStatement.setString(5, employee.getMarital_status().toString());
			preparedStatement.setString(6, employee.getDesignation_id().toString());
			preparedStatement.setDate(7, employee.getDob());
			preparedStatement.setDate(8, employee.getDoj());
			preparedStatement.setString(9, employee.getHighest_qualification().toString());
			preparedStatement.setString(10, employee.getBloodGroup().toString());
			preparedStatement.setString(11, employee.getPan());
			preparedStatement.setString(12, employee.getAadhar());
			preparedStatement.setString(13, employee.getUan());
			preparedStatement.setString(14, employee.getPresent_address());
			preparedStatement.setString(15, employee.getPermanent_address());

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenceException("Error while adding employee");
			}
			generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				emp_id = BigInteger.valueOf(generatedKeys.getLong(1));
			} else {
				throw new PersistenceException("Creating user failed, no ID obtained.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Error while adding employee", e);
		} finally {
			DBUtils.closeConnection(connection);

		}

		return emp_id;
	}


	public Employee findEmployeeById(BigInteger emp_id) throws PersistenceException {
		Connection connection = DBUtils.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.FIND_EMPLOYEE_BY_ID);
			preparedStatement.setString(1, emp_id.toString());

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.first()) {
				Employee employee = new Employee();
				employee.setAadhar(rs.getString(Fields.EMPLOYEE_KEY_AADHAR));
				employee.setAccount_type(AccountType.valueOf(rs.getString(Fields.EMPLOYEE_KEY_ACCOUNT_TYPE)));
				employee.setBloodGroup(BloodGroup.valueOf(rs.getString(Fields.EMPLOYEE_KEY_BLOOD_GROUP)));
				employee.setDesignation_id(BigInteger.valueOf(rs.getLong(Fields.EMPLOYEE_KEY_DESIGNATION_ID)));
				employee.setDob(rs.getDate(Fields.EMPLOYEE_KEY_DOB));
				employee.setDoj(rs.getDate(Fields.EMPLOYEE_KEY_DOJ));
				employee.setEmp_id(emp_id);
				employee.setEmp_name(rs.getString(Fields.EMPLOYEE_KEY_NAME));
				employee.setEmp_status(EmployeeStatus.valueOf(rs.getString(Fields.EMPLOYEE_KEY_EMP_STATUS)));
				employee.setGender(Gender.valueOf(rs.getString(Fields.EMPLOYEE_KEY_GENDER)));
				employee.setHighest_qualification(
						Qualification.valueOf(rs.getString(Fields.EMPLOYEE_KEY_HIGHEST_QUALIFICATION)));
				employee.setMarital_status(MaritalStatus.valueOf(rs.getString(Fields.EMPLOYEE_KEY_MARITAL_STATUS)));
				employee.setPan(rs.getString(Fields.EMPLOYEE_KEY_PAN));
				employee.setEmp_password(rs.getString(Fields.EMPLOYEE_KEY_PASSWORD));
				employee.setPermanent_address(rs.getString(Fields.EMPLOYEE_KEY_PERMANENT_ADDRESS));
				employee.setPresent_address(rs.getString(Fields.EMPLOYEE_KEY_PRESENT_ADDRESS));
				employee.setUan(rs.getString(Fields.EMPLOYEE_KEY_UAN));

				return employee;
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Error in querying Employee", e);

		} finally {
			DBUtils.closeConnection(connection);
		}
	}

	public ArrayList<Employee> findAllEmployees() {
		return null;
	}

	public String findPasswordOfEmployee(BigInteger emp_id) throws PersistenceException {

		Connection connection = DBUtils.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.FIND_PASSWORD_OF_EMPLOYEE);
			preparedStatement.setString(1, emp_id.toString());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.first()) {
				return rs.getString(Fields.EMPLOYEE_KEY_PASSWORD);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Error in querying password", e);

		} finally {
			DBUtils.closeConnection(connection);
		}

	}


	public boolean updateEmployeeStatus(BigInteger empID, EmployeeStatus employeeStatus) throws PersistenceException {
		Connection connection = DBUtils.getConnection();

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(Query.UPDATE_EMPLOYEE_STATUS);
			preparedStatement.setString(1, employeeStatus.toString());
			preparedStatement.setString(2, empID.toString());

			int affectedRows = preparedStatement.executeUpdate();
			
			if(affectedRows == 0) {
				return false;
			}
			
			return true;

		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new PersistenceException("Error while updating employee status");
			
		} finally {
			DBUtils.closeConnection(connection);
		}
	}

}
