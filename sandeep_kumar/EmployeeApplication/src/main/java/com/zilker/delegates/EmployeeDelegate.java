package com.zilker.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zilker.beans.EmployeeData;
import com.zilker.dao.EmployeeDao;

@Service
public class EmployeeDelegate {
	@Autowired
	private EmployeeDao employeeDao;
	private final static Logger LOGGER = Logger.getLogger(EmployeeDelegate.class.getName());

	public ArrayList<EmployeeData> getAllEmployees() {
		try {
			return employeeDao.getAllEmployees();
		} catch (SQLException e) {
			LOGGER.info("SQL Exception Occured!");
		}
		return null;
	}

	public Object getEmployeeById(long employeeId) {
		try {
			Optional<EmployeeData> employee=employeeDao.getEmployeeById(employeeId);
			if(employee.isPresent()) {
				return employee; 
			}
		} catch (SQLException e) {
			LOGGER.info("SQL Exception Occured!");
		}
		return "No Employee Found!";
	}

	public boolean addEmployee(EmployeeData employee) {
		try {
			if (employeeDao.addEmployee(employee)==null) {
				return false;
			}
		} catch (SQLException e) {
			LOGGER.info("SQL Exception Occured!");
		}
		return true;
	}

	public boolean updateEmployee(long employeeId, EmployeeData employee) {
		if (employeeDao.existsById(employeeId)) {
			try {
				employeeDao.updateEmployee(employeeId, employee);
			} catch (SQLException e) {
				LOGGER.info("SQL Exception Occured!");
			}
			return true;
		}
		return false;

	}

	public boolean deleteEmployee(long employeeId) {
		try {
			if (employeeDao.existsById(employeeId)) {
				employeeDao.deleteEmployee(employeeId);
				return true;
			}
		} catch (SQLException e) {
			LOGGER.info("SQL Exception Occured!");
		}
		return false;
	}

}
