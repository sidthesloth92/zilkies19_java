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
import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.OutputDelegate;

public class OutputService {
	OutputDelegate oDelegate = new OutputDelegate();

	public HashMap<Integer, String> displayMakes() throws SQLException {
		return oDelegate.displayMakes();
	}

	public HashMap<Integer, ArrayList<String>> getCars(Make make, CarType carType) throws SQLException {
		return oDelegate.getCars(make, carType);
	}

	public HashMap<Integer, String> displayCarTypes() throws SQLException {
		return oDelegate.displayCarTypes();
	}

	public HashMap<String, Rating> displayRating(Specification specification) {
		return oDelegate.displayRating(specification);
	}

	public ArrayList<Statistics> displayStatistics(Specification specification) {
		return oDelegate.displayStatistics(specification);
	}

	public Specification getCar(Request request) {
		return oDelegate.getCar(request);
	}
}
