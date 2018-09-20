package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.dao.AddCarDAO;

public class AddCarDelegate {
	AddCarDAO addCarDAO = new AddCarDAO();

	public int addCar(CarType carType, Make make, Specification specification)
			throws SQLException {
		addCarDAO.addCar(carType, make, specification);
		make.setMakeId(this.getMakeId(make));
		carType.setCarTypeId(this.getCarTypeId(carType));
		specification.setCarId(this.getCarId());
		this.addCarId(carType, make, specification);
		return specification.getCarId();
	}

	public int getMakeId(Make make) throws SQLException {
		return addCarDAO.getMakeId(make);
	}

	public int getCarTypeId(CarType carType) throws SQLException {
		return addCarDAO.getCarTypeId(carType);
	}

	public int getCarId() throws SQLException {
		return addCarDAO.getCarId();
	}

	public boolean addCarId(CarType carType, Make make, Specification specification) throws SQLException {
		return addCarDAO.addCarId(carType, make, specification);
	}
}
