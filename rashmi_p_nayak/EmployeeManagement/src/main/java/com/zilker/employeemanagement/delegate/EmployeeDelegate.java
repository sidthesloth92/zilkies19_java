package com.zilker.employeemanagement.delegate;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zilker.employeemanagement.beans.Employee;
import com.zilker.employeemanagement.constants.StatusMessage;
import com.zilker.employeemanagement.dao.EmployeeDao;

@Service
public class EmployeeDelegate {

	@Autowired
	private EmployeeDao employeeDao;
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeDelegate.class.getName());

	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = null;
		try {
			employeeList = employeeDao.getAllEmployees();
		} catch (SQLException e) {
			LOGGER.info(e.getMessage());
		}
		return employeeList;
	}

	public Optional<Employee> getEmployee(int id) {
		Optional<Employee> employee = null;
		try {
			employee = employeeDao.getEmployee(id);
		} catch (SQLException e) {
			LOGGER.info(e.getMessage());
		}
		return employee;
	}

	public String addEmployee(Employee employee) {
		Object isSuccessObject = null;
		try {
			isSuccessObject = employeeDao.addEmployee(employee);
		} catch (SQLException e) {
			LOGGER.info(e.getMessage());
			return StatusMessage.SOMETHING_WENT_WRONG;
		}
		if (isSuccessObject != null) {
			return StatusMessage.EMPLOYEE_ADDED_SUCCESSFULLY;
		} else {
			return StatusMessage.EMPLOYEE_NOT_ADDED;
		}
	}

	public String updateEmployee(int id, Employee employee) {
		Optional<Employee> existingEmployee = null;
		try {
			existingEmployee = employeeDao.getEmployee(id);
		} catch (SQLException e) {
			LOGGER.info(e.getMessage());
			return StatusMessage.SOMETHING_WENT_WRONG;
		}
		if (existingEmployee != null) {
			employee.setId(id);
			Object isSuccessObject = null;
			try {
				isSuccessObject = employeeDao.updateEmployee(employee);
			} catch (SQLException e) {
				LOGGER.info(e.getMessage());
				return StatusMessage.SOMETHING_WENT_WRONG;
			}
			if (isSuccessObject != null) {
				return StatusMessage.EMPLOYEE_UPDATED_SUCCESSFULLY;
			} else {
				return StatusMessage.EMPLOYEE_NOT_UPDATED;
			}
		}
		else {
			return StatusMessage.EMPLOYEE_DOES_NOT_EXIST;
		}
	}
	
	public String deleteEmployee(int id) {
		Optional<Employee> existingEmployee = null;
		try {
			existingEmployee = employeeDao.getEmployee(id);
		} catch (SQLException e) {
			LOGGER.info(e.getMessage());
			return StatusMessage.SOMETHING_WENT_WRONG;
		}
		if(existingEmployee != null) {
			try {
				employeeDao.deleteEmployee(id);
			} catch (SQLException e) {
				LOGGER.info(e.getMessage());
				return StatusMessage.SOMETHING_WENT_WRONG;
			}
			return StatusMessage.EMPLOYEE_DELETED_SUCESSFULLY;
		}
		else {
			return StatusMessage.EMPLOYEE_DOES_NOT_EXIST;
		}
	}
}
