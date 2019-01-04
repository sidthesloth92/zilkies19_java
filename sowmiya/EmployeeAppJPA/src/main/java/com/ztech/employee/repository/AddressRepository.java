package com.ztech.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.ztech.employee.bean.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
	

}
