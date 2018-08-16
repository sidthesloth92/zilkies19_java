package io.ztech.carstats.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import io.ztech.carstats.beans.CarType;
import io.ztech.carstats.beans.Make;
import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Request;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.dao.FetchDetailsDAO;

public class FetchDetailsDelegate {
	FetchDetailsDAO fetchDetailsDAO = new FetchDetailsDAO();

	public HashMap<Integer, String> displayMakes() throws SQLException {
		return fetchDetailsDAO.displayMakes();
	}

	public HashMap<Integer, ArrayList<String>> getCars(Make make, CarType carType) throws SQLException {
		return fetchDetailsDAO.getCars(make, carType);
	}

	public HashMap<Integer, String> displayCarTypes() throws SQLException {
		return fetchDetailsDAO.displayCarTypes();
	}

	public HashMap<String, Rating> displayRating(Specification specification) {
		return fetchDetailsDAO.displayRating(specification);
	}

	public ArrayList<Statistics> displayStatistics(Specification specification) {
		return fetchDetailsDAO.displayStatistics(specification);
	}

	public Specification getCar(Request request) {
		return fetchDetailsDAO.getCar(request);
	}
}
