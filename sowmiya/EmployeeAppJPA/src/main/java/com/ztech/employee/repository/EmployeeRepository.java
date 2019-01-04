package com.ztech.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ztech.employee.bean.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
