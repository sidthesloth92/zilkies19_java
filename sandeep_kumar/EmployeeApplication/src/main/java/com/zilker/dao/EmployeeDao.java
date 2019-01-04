package com.zilker.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zilker.beans.EmployeeData;
import com.zilker.repository.EmployeeRepository;

@Repository
public class EmployeeDao {
	@Autowired
	private EmployeeRepository employeeRepository;

	public ArrayList<EmployeeData> getAllEmployees() throws SQLException {
		ArrayList<EmployeeData> employeesList = new ArrayList<EmployeeData>();
		employeeRepository.findAll().forEach(employeesList::add);
		return employeesList;
	}

	public Optional<EmployeeData> getEmployeeById(long employeeId) throws SQLException {
		return employeeRepository.findById(employeeId);
	}

	public EmployeeData addEmployee(EmployeeData employee) throws SQLException {
		return employeeRepository.save(employee);
	}

	public EmployeeData updateEmployee(long employeeId, EmployeeData employee) throws SQLException {
		employee.setEmployeeId(employeeId);
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(long employeeId) throws SQLException {
		employeeRepository.deleteById(employeeId);
	}

	public boolean existsById(long employeeId) {
		return employeeRepository.existsById(employeeId);
	}
}
