package com.zilker.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.beans.EmployeeData;
import com.zilker.delegates.EmployeeDelegate;

@RestController
@RequestMapping("/EmployeeApplication")
public class EmployeeController {

	@Autowired
	private EmployeeDelegate employeeDelegate;

	@GetMapping("/employees")
	public ArrayList<EmployeeData> getAllEmployees() {
		try {
			if (employeeDelegate.getAllEmployees() != null) {
				return employeeDelegate.getAllEmployees();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/employees/{employeeId}")
	public Object getEmployeesById(@PathVariable("employeeId") long employeeId) {
		return employeeDelegate.getEmployeeById(employeeId);
	}

	@PostMapping("/employees")
	public String addEmployee(@RequestBody EmployeeData employee) {
		try {
			if (employeeDelegate.addEmployee(employee)) {
				return "Successfully Inserted!";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return "Error in Adding Employee!";
	}

	@PutMapping("/employees/{employeeId}")
	public String updateEmployee(@PathVariable("employeeId") long employeeId, @RequestBody EmployeeData employee) {
		try {
			if (employeeDelegate.updateEmployee(employeeId, employee)) {
				return "Updation Successful!";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return "Updation Unsuccessful!";
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable("employeeId") long employeeId) {
		try {
			if (employeeDelegate.deleteEmployee(employeeId)) {
				return "Deletion Successful!";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return "Invalid Id";
	}

}