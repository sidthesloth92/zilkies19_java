package com.employeeapp.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeapp.bean.Employee;
import com.employeeapp.constants.ApplicationConstants;
import com.employeeapp.dao.EmployeeDao;

@Service
public class EmployeeDelegate {
	@Autowired
	private EmployeeDao employeeDao;
	private Logger log = Logger.getLogger(EmployeeDelegate.class);

	public ArrayList<Employee> getAllEmployee() {

		log.info("EmployeeDelegate: getAllEmployee()");
		try {
			ArrayList<Employee> employeeList = employeeDao.getAllEmployee();
			return employeeList;
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return null;
	}

	public Employee getEmployee(int id) {

		log.info("EmployeeDelegate: getEmployee()");
		try {
			Employee employee = employeeDao.getEmployee(id);
			return employee;
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return null;
	}

	public String addEmployee(Employee employee) {

		log.info("EmployeeDelegate: addEmployee()");

		if (!(Validator.validateName(employee.getEmployeeFirstName())
				&& Validator.validateName(employee.getEmployeeLastName()))) {
			return ApplicationConstants.NAME_VALIDATION_FAILS;
		}
		if (!(Validator.validateEmail(employee.getEmail()))) {
			return ApplicationConstants.EMAIL_VALIDATION_FAILS;
		}
		if (!(Validator.validPhoneNumber(employee.getPhoneNumber()))) {
			return ApplicationConstants.PHONE_NUMBER_VALIDATION_FAILS;
		}
		if (!(Validator.validSalary(employee.getSalary()))) {
			return ApplicationConstants.SALARY_VALIDATION_FAILS;
		}

		try {
			boolean success = employeeDao.addEmployee(employee);
			if (success) {
				return ApplicationConstants.INSERTION_SUCCESS;
			} else {
				return ApplicationConstants.ERROR;
			}
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return null;
	}

	public String updateEmployee(Employee employee, int id) {

		log.info("EmployeeDelegate: updateEmployee()");

		if (!Validator.validSalary(employee.getSalary())) {
			return ApplicationConstants.SALARY_VALIDATION_FAILS;
		}
		try {
			boolean success = employeeDao.updateEmployee(employee, id);
			if (success) {
				return ApplicationConstants.UPDATION_SUCCESS;
			} else {
				return ApplicationConstants.ERROR;
			}
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return null;

	}

	public boolean deleteEmployee(int id) {

		log.info("EmployeeDelegate: deleteEmployee()");
		try {
			boolean success = employeeDao.deleteEmployee(id);
			return success;
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return false;

	}

}
