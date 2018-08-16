package io.ztech.carstats.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.carstats.beans.Request;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.dao.RequestCarDAO;

public class RequestCarDelegate {
	RequestCarDAO requestDAO = new RequestCarDAO();

	public boolean addCarUserRequest(Request request, User user, Specification specification) throws SQLException {
		return requestDAO.addCarUserRequest(request, user, specification) && requestDAO.getRequestId(request);
	}

	public ArrayList<Request> getRequests(User user) {
		return requestDAO.getRequests(user);
	}

	public boolean approveCar(Specification specification, Request request) {
		requestDAO.deleteRequest(request);
		return requestDAO.approveCar(specification);
	}
}
