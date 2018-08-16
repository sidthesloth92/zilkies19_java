package io.ztech.carstats.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import io.ztech.carstats.beans.CarType;
import io.ztech.carstats.beans.Make;
import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Request;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.delegate.FetchDetailsDelegate;

public class FetchDetailsService {
	FetchDetailsDelegate fetchDetailsDelegate = new FetchDetailsDelegate();

	public HashMap<Integer, String> displayMakes() throws SQLException {
		return fetchDetailsDelegate.displayMakes();
	}

	public HashMap<Integer, ArrayList<String>> getCars(Make make, CarType carType) throws SQLException {
		return fetchDetailsDelegate.getCars(make, carType);
	}

	public HashMap<Integer, String> displayCarTypes() throws SQLException {
		return fetchDetailsDelegate.displayCarTypes();
	}

	public HashMap<String, Rating> displayRating(Specification specification) {
		return fetchDetailsDelegate.displayRating(specification);
	}

	public ArrayList<Statistics> displayStatistics(Specification specification) {
		return fetchDetailsDelegate.displayStatistics(specification);
	}

	public Specification getCar(Request request) {
		return fetchDetailsDelegate.getCar(request);
	}
}
