package com.ztech.employee.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ztech.employee.bean.Employee;
import com.ztech.employee.delegate.EmployeeDelegate;

@RestController
@RequestMapping("/employeeapp")
public class EmployeeController {
	@Autowired
	private EmployeeDelegate employeeDelegate;
	private Logger log = Logger.getLogger(EmployeeController.class);

	@GetMapping("/employee")
	public List<Employee> getAllEmployees() {

		log.info("EmployeeController: getAllEmployees()");

		List<Employee> employeesList = employeeDelegate.getAllEmployees();
		return employeesList;

	}

	@GetMapping("/employee/{id}")
	public Object getEmployee(@PathVariable int id) {

		log.info("EmployeeController: getEmployee()");

		Object employee = employeeDelegate.getEmployee(id);
		return employee;

	}

	@PostMapping("/employee")
	public String addEmployee(@RequestBody Employee employee) {

		log.info("EmployeeController: addEmployees()");

		String status = employeeDelegate.addEmployee(employee);
		return status;

	}

	@PutMapping("/employee/{id}")
	public String updateEmployee(@RequestBody Employee employee, @PathVariable int id) {

		log.info("EmployeeController: updateEmployee()");

		String status = employeeDelegate.updateEmployee(employee, id);
		return status;

	}

	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable int id) {

		log.info("EmployeeController: deleteEmployee()");

		String status = employeeDelegate.deleteEmployee(id);
		return status;
	}

}
