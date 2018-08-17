package io.ztech.carstats.services;

import java.sql.SQLException;

import io.ztech.carstats.beans.CarType;
import io.ztech.carstats.beans.Make;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.delegate.AddCarDelegate;

public class AddCarService {

	AddCarDelegate addCarDelegate = new AddCarDelegate();

	public boolean addCar(CarType carType, Make make, Specification specification)
			throws SQLException {
		return addCarDelegate.addCar(carType, make, specification);
	}
}
