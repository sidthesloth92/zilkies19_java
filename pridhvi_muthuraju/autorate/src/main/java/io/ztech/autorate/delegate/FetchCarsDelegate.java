package io.ztech.autorate.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dao.FetchCarsDAO;

public class FetchCarsDelegate {
	FetchCarsDAO fetchCarsDAO = new FetchCarsDAO();

	public ArrayList<Make> displayMakes(CarType carType) throws SQLException {
		if (carType.getCarTypeId() == 0)
			return fetchCarsDAO.displayMakes(SQLConstants.SELECT_ALL_MAKE);
		else
			return fetchCarsDAO.displayMakes(SQLConstants.SELECT_ALL_MAKE_CAR_TYPE + carType.getCarTypeId() + ")");
	}

	public ArrayList<Specification> getCars(int makeId, int carTypeId) throws SQLException {
		return fetchCarsDAO.getCars(makeId, carTypeId);
	}

	public ArrayList<CarType> displayCarTypes(Make make) throws SQLException {
		if (make.getMakeId() == 0)
			return fetchCarsDAO.displayCarTypes(SQLConstants.SELECT_ALL_CAR_TYPE);
		else
			return fetchCarsDAO.displayCarTypes(SQLConstants.SELECT_ALL_CAR_TYPE_MAKE + make.getMakeId() + ")");

	}

	public HashMap<String, Rating> displayRating(Specification specification) throws SQLException {
		return fetchCarsDAO.displayRating(specification);
	}

	public Rating getRating(int carId, String username) throws SQLException {
		return fetchCarsDAO.getRating(carId, username);
	}

	public Specification getCar(Request request) throws SQLException {
		if (request.getCarId() != 0)
			return fetchCarsDAO.getCar(request);
		else
			return fetchCarsDAO.getCarByRequest(request);
	}
}
