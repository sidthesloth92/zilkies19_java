package com.zilker.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.zilker.bean.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//	// add employee
//	public Employee save(Employee employee);
//	
//	// get all employees
//	public ArrayList<Employee> findAll();
//	
//	// get one employee
//	// public Employee findById(int id);
//	
//	// delete employee
//	public boolean deleteById(int id);
	
}
