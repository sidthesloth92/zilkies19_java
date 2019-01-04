package com.zilker.repository;

import org.springframework.data.repository.CrudRepository;

import com.zilker.beans.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
