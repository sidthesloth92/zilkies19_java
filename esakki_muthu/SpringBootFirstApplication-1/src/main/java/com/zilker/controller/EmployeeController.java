package com.zilker.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.bean.Employee;
import com.zilker.delegate.EmployeeDelegate;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	Logger logger = Logger.getLogger(EmployeeController.class.getName());
	
	@Autowired
	private EmployeeDelegate employeeDelegate;
	
	@PostMapping("/")
	public String addEmployee(@RequestBody Employee employee) {
		String response = employeeDelegate.addEmployee(employee);
		return response;
	}
	@GetMapping("/")
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> employeeList =  employeeDelegate.getAllEmployees();
		return employeeList;
	}
	@GetMapping("/{id}")
	public Object getEmployeeById(@PathVariable("id") int id) {
		Employee employee = (Employee) employeeDelegate.getEmployeeById(id);
		return employee;
	}
	@PutMapping("/{id}")
	public String updateEmployeeById(@PathVariable("id") int id, @RequestBody Employee employee) {
		String response  = employeeDelegate.updateEmployeeById(id, employee);
		return response;
	}
	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable("id") int id) {
		String response = employeeDelegate.deleteEmployeeById(id);
		return response;
	}
}
