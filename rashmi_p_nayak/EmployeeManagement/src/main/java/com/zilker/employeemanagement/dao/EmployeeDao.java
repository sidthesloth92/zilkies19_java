package com.zilker.employeemanagement.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zilker.employeemanagement.beans.Employee;
import com.zilker.employeemanagement.repository.EmployeeRepository;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() throws SQLException{
		List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
		return employeeList;
	}
	
	public Optional<Employee> getEmployee(int id) throws SQLException{
		Optional<Employee> employee = employeeRepository.findById(id);
		return employee;
	}
	
	public Object addEmployee(Employee employee) throws SQLException{
		Object isSuccessObject = employeeRepository.save(employee);
		return isSuccessObject;
	}
	
	public Object updateEmployee(Employee employee) throws SQLException{
		Object isSuccessObject = employeeRepository.save(employee);
		return isSuccessObject;
	}
	
	public void deleteEmployee(int id) throws SQLException{
		employeeRepository.deleteById(id);
	}
}
