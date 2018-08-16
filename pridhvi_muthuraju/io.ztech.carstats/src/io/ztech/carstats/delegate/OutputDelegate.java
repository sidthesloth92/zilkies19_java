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
import io.ztech.carstats.beans.User;
import io.ztech.carstats.dao.OutputDAO;

public class OutputDelegate {
	OutputDAO odao = new OutputDAO();

	public HashMap<Integer, String> displayMakes() throws SQLException {
		return odao.displayMakes();
	}

	public HashMap<Integer, ArrayList<String>> getCars(Make make, CarType carType) throws SQLException {
		return odao.getCars(make, carType);
	}

	public HashMap<Integer, String> displayCarTypes() throws SQLException {
		return odao.displayCarTypes();
	}

	public HashMap<String, Rating> displayRating(Specification specification) {
		return odao.displayRating(specification);
	}

	public ArrayList<Statistics> displayStatistics(Specification specification) {
		return odao.displayStatistics(specification);
	}

	public Specification getCar(Request request) {
		return odao.getCar(request);
	}
}
