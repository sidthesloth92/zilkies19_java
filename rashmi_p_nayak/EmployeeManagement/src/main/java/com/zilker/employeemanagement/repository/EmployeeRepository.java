package com.zilker.employeemanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.zilker.employeemanagement.beans.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
