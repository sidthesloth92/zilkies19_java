package com.employeeapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

import com.employeeapp.bean.Employee;
import com.employeeapp.constants.SqlConstants;
import com.employeeapp.dbutil.DbConnection;

@Repository
public class EmployeeDao {
	private Logger log = Logger.getLogger(EmployeeDao.class);

	public ArrayList<Employee> getAllEmployee() throws SQLException {

		log.info("EmployeeDao: getAllEmployee()");

		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GET_ALL_EMPLOYEE);
			resultSet = preparedStatement.executeQuery();
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeFirstName(resultSet.getString("firstname"));
				employee.setEmployeeLastName(resultSet.getString("lastname"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhoneNumber(resultSet.getLong("phone_number"));
				employee.setSalary(resultSet.getLong("salary"));
				employee.setEmployeeId(resultSet.getInt("id"));
				employeeList.add(employee);
			}
			return employeeList;

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

	public Employee getEmployee(int id) throws SQLException {

		log.info("EmployeeDao: getEmployee()");

		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GET_EMPLOYEE);
			preparedStatement.setInt(0, id);
			resultSet = preparedStatement.executeQuery();
			Employee employee = new Employee();
			while (resultSet.next()) {
				employee.setEmployeeFirstName(resultSet.getString("firstname"));
				employee.setEmployeeLastName(resultSet.getString("lastname"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhoneNumber(resultSet.getLong("phone_number"));
				employee.setSalary(resultSet.getLong("salary"));
			}
			return employee;

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

	public boolean addEmployee(Employee employee) throws SQLException {
		log.info("EmployeeDao: getEmployee()");

		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.ADD_EMPLOYEE);
			preparedStatement.setString(1, employee.getEmployeeFirstName());
			preparedStatement.setString(2, employee.getEmployeeLastName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setLong(4, employee.getPhoneNumber());
			preparedStatement.setLong(5, employee.getSalary());
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}

		} finally {
			DbConnection.closeConnection(null, preparedStatement, connection);
		}
		return false;
	}

	public boolean updateEmployee(Employee employee, int id) throws SQLException {
		log.info("EmployeeDao: updatetEmployee()");

		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.UPDATE_EMPLOYEE);
			preparedStatement.setString(1, employee.getEmployeeFirstName());
			preparedStatement.setString(2, employee.getEmployeeLastName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setLong(4, employee.getPhoneNumber());
			preparedStatement.setLong(5, employee.getSalary());
			preparedStatement.setInt(6, id);
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}

		} finally {
			DbConnection.closeConnection(null, preparedStatement, connection);
		}
		return false;
	}

	public boolean deleteEmployee(int id) throws SQLException {
		log.info("EmployeeDao: deleteEmployee()");

		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.DELETE_EMPLOYEE);
			preparedStatement.setInt(1, id);
			if (preparedStatement.executeUpdate() >= 0) {
				return true;
			}

		} finally {
			DbConnection.closeConnection(null, preparedStatement, connection);
		}

		return false;
	}

}
