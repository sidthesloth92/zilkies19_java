package io.ztech.carstats.services;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.carstats.beans.Request;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.RequestCarDelegate;

public class RequestCarService {
	RequestCarDelegate rcDelegate = new RequestCarDelegate();

	public boolean addCarUserRequest(Request request, User user, Specification specification) throws SQLException {
		return rcDelegate.addCarUserRequest(request, user, specification);
	}

	public ArrayList<Request> getRequests() {
		return rcDelegate.getRequests();
	}

	public boolean approveCar(Specification specification, Request request) {
		return rcDelegate.approveCar(specification, request);
	}
}
