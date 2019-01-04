package com.ztech.employee.delegate;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztech.employee.bean.Employee;
import com.ztech.employee.constants.ApplicationConstants;
import com.ztech.employee.dao.EmployeeDao;

@Service
public class EmployeeDelegate {
	@Autowired
	private EmployeeDao employeeDao;

	private Logger log = Logger.getLogger("EmployeeDelegate.class");

	public List<Employee> getAllEmployees() {

		log.info("EmployeeDelegate: getAllEmployees()");

		try {
			List<Employee> employeesList = employeeDao.getAllEmployees();
			return employeesList;
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	public String addEmployee(Employee employee) {

		log.info("EmployeeDelegate: addEmployee()");

		try {
			Employee insertedEmployee = (Employee) employeeDao.addEmployee(employee);
			if (insertedEmployee == null) {
				return ApplicationConstants.ERROR;
			}
			return ApplicationConstants.INSERTION_SUCCESS;
		} catch (Exception e) {
			log.info(e.getMessage());
			return ApplicationConstants.ERROR;
		}

	}

	public String updateEmployee(Employee employee, int id) {

		log.info("EmployeeDelegate: updateEmployee()");

		try {
			boolean isExists = employeeDao.checkIsAvailable(id);
			if (isExists) {
				Employee updatedEmployee = (Employee) employeeDao.updateEmployee(employee, id);
				if (updatedEmployee == null) {
					return ApplicationConstants.ERROR;
				}
				return ApplicationConstants.UPDATION_SUCCESS;
			} else {
				return ApplicationConstants.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;

	}

	public String deleteEmployee(int id) {

		log.info("EmployeeDelegate: deleteEmployee()");

		try {
			boolean isExists = employeeDao.checkIsAvailable(id);
			if (isExists) {
				employeeDao.deleteEmployee(id);
				return ApplicationConstants.DELETION_SUCCESS;
			}
			return ApplicationConstants.ERROR;
		} catch (Exception e) {
			log.info(e.getMessage());
			return ApplicationConstants.ERROR;
		}
	}

	public Object getEmployee(int id) {
		log.info("EmployeeDelegate: getEmployee()");

		try {
			Optional<Employee> employee = employeeDao.getEmployee(id);
			if (employee.isPresent()) {
				return employee;
			} else {
				return ApplicationConstants.ERROR;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			return ApplicationConstants.ERROR;
		}
	}

}
