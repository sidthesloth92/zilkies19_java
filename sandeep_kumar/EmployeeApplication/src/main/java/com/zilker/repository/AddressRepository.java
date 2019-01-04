package com.zilker.repository;

import org.springframework.data.repository.CrudRepository;

import com.zilker.beans.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
