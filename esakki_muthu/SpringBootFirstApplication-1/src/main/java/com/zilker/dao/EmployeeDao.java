package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zilker.bean.Address;
import com.zilker.bean.Employee;
import com.zilker.config.DatabaseConfig;
import com.zilker.constants.DatabaseConstants;
import com.zilker.repository.AddressRepository;
import com.zilker.repository.EmployeeRepository;

@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AddressRepository addressRepository;

	private static final Logger logger = Logger.getLogger(EmployeeDao.class.getName());
	Connection connection = null;
	ArrayList<Employee> employeeArrayList;

	public void addEmployee(Employee employee) throws SQLException {
		Address address = employee.getAddress();
		address = addressRepository.save(address);
		employee.setAddress(address);
		employee = employeeRepository.save(employee);
	}

	public ArrayList<Employee> getAllEmployee() throws SQLException {
		employeeArrayList = (ArrayList<Employee>)employeeRepository.findAll();
		return employeeArrayList;
	}

	public Employee getEmployeeById(int id) throws SQLException {
		Employee employee =  employeeRepository.getOne(id);
		return employee;
	}

	public void updateEmployeeById(int id, Employee employee) throws SQLException {
		employee.setId(id);
		if (employee.getAddress() != null) {
			Address address = employee.getAddress();
			address = addressRepository.save(address);
			employee.setAddress(address);
		}
		employee = employeeRepository.save(employee);
	}

	public void deleteEmployeeById(int id) throws SQLException {
		employeeRepository.deleteById(id);
	}
}
