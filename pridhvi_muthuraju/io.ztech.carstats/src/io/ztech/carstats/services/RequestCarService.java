package io.ztech.carstats.services;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.carstats.beans.Request;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.RequestCarDelegate;

public class RequestCarService {
	RequestCarDelegate requestCarDelegate = new RequestCarDelegate();

	public boolean addCarUserRequest(Request request, User user, Specification specification) throws SQLException {
		return requestCarDelegate.addCarUserRequest(request, user, specification);
	}

	public ArrayList<Request> getRequests(User user) throws SQLException {
		return requestCarDelegate.getRequests(user);
	}

	public boolean approveCar(Specification specification, Request request) throws SQLException {
		return requestCarDelegate.approveCar(specification, request);
	}
}
