package io.ztech.carstats.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import io.ztech.carstats.beans.CarType;
import io.ztech.carstats.beans.Make;
import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Request;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.FetchCarsDelegate;

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
