package io.ztech.autorate.services;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.delegate.RequestCarDelegate;

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
