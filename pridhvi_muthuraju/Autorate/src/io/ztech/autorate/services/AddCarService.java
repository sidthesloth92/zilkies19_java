package io.ztech.autorate.services;

import java.sql.SQLException;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.delegate.AddCarDelegate;

public class AddCarService {

	AddCarDelegate addCarDelegate = new AddCarDelegate();

	public boolean addCar(CarType carType, Make make, Specification specification)
			throws SQLException {
		return addCarDelegate.addCar(carType, make, specification);
	}
}
