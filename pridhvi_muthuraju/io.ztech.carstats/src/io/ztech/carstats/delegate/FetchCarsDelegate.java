package io.ztech.carstats.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import io.ztech.carstats.beans.CarType;
import io.ztech.carstats.beans.Make;
import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Request;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dao.FetchCarsDAO;

public class FetchCarsDelegate {
	FetchCarsDAO fetchCarsDAO = new FetchCarsDAO();

	public ArrayList<Make> displayMakes(CarType carType) throws SQLException {
		if (carType.getCarTypeId() == 0)
			return fetchCarsDAO.displayMakes(SQLConstants.SELECT_ALL_MAKE);
		else
			return fetchCarsDAO.displayMakes(SQLConstants.SELECT_ALL_MAKE_CAR_TYPE + carType.getCarTypeId() + ")");
	}

	public ArrayList<Specification> getCars(Make make, CarType carType) throws SQLException {
		return fetchCarsDAO.getCars(make, carType);
	}

	public ArrayList<CarType> displayCarTypes(Make make) throws SQLException {
		if (make.getMakeId() == 0)
			return fetchCarsDAO.displayCarTypes(SQLConstants.SELECT_ALL_CAR_TYPE);
		else
			return fetchCarsDAO.displayCarTypes(SQLConstants.SELECT_ALL_CAR_TYPE_MAKE + make.getMakeId() + ")");

	}

	public HashMap<User, Rating> displayRating(Specification specification) throws SQLException {
		return fetchCarsDAO.displayRating(specification);
	}

	public Specification getCar(Request request) throws SQLException {
		return fetchCarsDAO.getCar(request);
	}
}
