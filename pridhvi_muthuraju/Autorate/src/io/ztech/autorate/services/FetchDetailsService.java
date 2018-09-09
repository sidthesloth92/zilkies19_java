package io.ztech.autorate.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.delegate.FetchCarsDelegate;

public class FetchDetailsService {
	FetchCarsDelegate fetchCarsDelegate = new FetchCarsDelegate();

	public ArrayList<Make> displayMakes(CarType carType) throws SQLException {
		return fetchCarsDelegate.displayMakes(carType);
	}

	public ArrayList<Specification> getCars(Make make, CarType carType) throws SQLException {
		return fetchCarsDelegate.getCars(make, carType);
	}

	public ArrayList<CarType> displayCarTypes(Make make) throws SQLException {
		return fetchCarsDelegate.displayCarTypes(make);
	}

	public HashMap<User, Rating> displayRating(Specification specification) throws SQLException {
		return fetchCarsDelegate.displayRating(specification);
	}

	public Specification getCar(Request request) throws SQLException {
		return fetchCarsDelegate.getCar(request);
	}
}
