package com.ztech.employee.dao;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ztech.employee.bean.Address;
import com.ztech.employee.bean.Employee;
import com.ztech.employee.repository.AddressRepository;
import com.ztech.employee.repository.EmployeeRepository;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private AddressRepository addressRepository;

	private Logger log = Logger.getLogger("EmployeeDao.class");

	public List<Employee> getAllEmployees() {

		log.info("EmployeeDao: getAllEmployees()");

		List<Employee> employeesList = (List<Employee>) employeeRepository.findAll();
		return employeesList;
	}

	public Employee addEmployee(Employee employee) {

		log.info("EmployeeDao: addEmployee()");

		Address address = addressRepository.save(employee.getAddress());
		employee.setAddress(address);
		Employee insertedEmployee = employeeRepository.save(employee);
		return insertedEmployee;

	}

	public boolean checkIsAvailable(int id) {

		log.info("EmployeeDao: checkIsAvailable()");

		Employee employee = (Employee) employeeRepository.getOne(id);
		if (employee != null) {
			return true;
		}
		return false;
	}

	public Employee updateEmployee(Employee employee, int id) {

		log.info("EmployeeDao: updateEmployee()");

		if (employee.getAddress() != null) {
			Address address = addressRepository.save(employee.getAddress());
			employee.setAddress(address);
		}
		employee.setId(id);
		Employee updatedEmployee = employeeRepository.save(employee);
		return updatedEmployee;
	}

	public void deleteEmployee(int id) {

		log.info("EmployeeDao: deleteEmployee()");

		employeeRepository.deleteById(id);
	}

	public Optional<Employee> getEmployee(int id) {

		log.info("EmployeeDao: getEmployee()");

		Optional<Employee> employee =  employeeRepository.findById(id);
		return employee;
	}

}
