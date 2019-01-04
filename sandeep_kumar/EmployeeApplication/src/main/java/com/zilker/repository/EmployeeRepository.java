package com.zilker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zilker.beans.EmployeeData;

public interface EmployeeRepository extends CrudRepository<EmployeeData, Long> {

}
