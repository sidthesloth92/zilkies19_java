package com.zilker.employeemanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.employeemanagement.beans.Employee;
import com.zilker.employeemanagement.delegate.EmployeeDelegate;

@RestController
@RequestMapping("/employeeManagement/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDelegate employeeDelegate;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = employeeDelegate.getAllEmployees();
		return employeeList;
	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployee(@PathVariable int id) {
		Optional<Employee> employee = employeeDelegate.getEmployee(id);
		return employee;
	}
	
	@PostMapping("/employees") 
	public String addEmployee(@RequestBody Employee employee) {
		String statusMessage = employeeDelegate.addEmployee(employee);
		return statusMessage;
	}
	
	@PutMapping("/employees/{id}")
	public String updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		String statusMessage = employeeDelegate.updateEmployee(id, employee);
		return statusMessage;
	}
	
	@DeleteMapping("/employees/{id}") 
	public String deleteEmployee(@PathVariable int id) {
		String statusMessage = employeeDelegate.deleteEmployee(id);
		return statusMessage;
	}
	
}
