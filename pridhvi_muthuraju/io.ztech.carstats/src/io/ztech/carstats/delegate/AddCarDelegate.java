package io.ztech.carstats.delegate;

import java.sql.SQLException;

import io.ztech.carstats.beans.CarType;
import io.ztech.carstats.beans.Make;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.dao.AddCarDAO;

public class AddCarDelegate {
	AddCarDAO addao = new AddCarDAO();

	public boolean addCar(CarType carType, Make make, Specification specification)
			throws SQLException {
		addao.addCar(carType, make, specification);
		make.setMakeId(this.getMakeId(make));
		carType.setCarTypeId(this.getCarTypeId(carType));
		specification.setCarId(this.getCarId());
		this.addCarId(carType, make, specification);
		return true;
	}

	public int getMakeId(Make make) throws SQLException {
		return addao.getMakeId(make);
	}

	public int getCarTypeId(CarType carType) throws SQLException {
		return addao.getCarTypeId(carType);
	}

	public int getCarId() throws SQLException {
		return addao.getCarId();
	}

	public boolean addCarId(CarType carType, Make make, Specification specification) {
		return addao.addCarId(carType, make, specification);
	}
}
