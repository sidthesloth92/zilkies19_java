package io.ztech.autorate.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.dao.RequestCarDAO;

public class RequestCarDelegate {
	RequestCarDAO requestDAO = new RequestCarDAO();

	public boolean addCarUserRequest(Request request, User user, Specification specification) throws SQLException {
		return requestDAO.addCarUserRequest(request, user, specification) && requestDAO.getRequestId(request);
	}

	public ArrayList<Request> getRequests(User user) throws SQLException {
		return requestDAO.getRequests(user);
	}

	public boolean approveCar(Specification specification, Request request) throws SQLException {
		requestDAO.deleteRequest(request);
		return requestDAO.approveCar(specification);
	}
}
