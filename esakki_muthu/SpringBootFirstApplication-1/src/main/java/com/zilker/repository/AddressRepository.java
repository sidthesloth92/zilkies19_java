package com.zilker.repository;

import org.springframework.data.repository.CrudRepository;

import com.zilker.bean.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
