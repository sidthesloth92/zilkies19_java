package com.zilker.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zilker.bean.Employee;
import com.zilker.constants.DisplayConstants;
import com.zilker.dao.EmployeeDao;
import com.zilker.utils.Validation;

@Service
public class EmployeeDelegate {
	Logger logger = Logger.getLogger(EmployeeDelegate.class.getName());
	@Autowired
	private EmployeeDao employeeDao;
	Validation validation = new Validation();

	public String addEmployee(Employee employee) {
		try {
			if (validation.validateEmail(employee.getEmail())
					&& validation.validatePhoneNumber(employee.getPhoneNumber() + ""))
				employeeDao.addEmployee(employee);
			else
				return DisplayConstants.INSERTION_FAILED;
		} catch (SQLException e) {
			return DisplayConstants.INSERTION_FAILED;
		}
		return DisplayConstants.INSERTION_SUCCESS;
	}

	public ArrayList<Employee> getAllEmployees() {
		try {
			return employeeDao.getAllEmployee();
		} catch (SQLException e) {
			return null;
		}
	}

	public Object getEmployeeById(int id) {
		try {
			Employee employee = employeeDao.getEmployeeById(id);
			if (employee != null)
				return employee;
			else
				return DisplayConstants.NO_EMPLOYEES_FOUND;
		} catch (SQLException e) {
			return null;
		}
	}

	public String updateEmployeeById(int id, Employee employee) {
		try {
			employeeDao.updateEmployeeById(id, employee);
			return DisplayConstants.UPDATION_SUCCESS;
		} catch (SQLException e) {
			return DisplayConstants.UPDATION_FAILED;
		}
	}

	public String deleteEmployeeById(int id) {
		try {
			employeeDao.deleteEmployeeById(id);
			return DisplayConstants.DELETION_SUCCESS;
		} catch (SQLException e) {
			return DisplayConstants.DELETION_FAILED;
		}
	}
}
