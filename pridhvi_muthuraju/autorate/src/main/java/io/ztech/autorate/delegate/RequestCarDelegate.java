package io.ztech.autorate.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.dao.RequestCarDAO;

public class RequestCarDelegate {
	RequestCarDAO requestDAO = new RequestCarDAO();

	public int addCarUserRequest(int carId, String username) throws SQLException {
		if (requestDAO.addCarUserRequest(carId, username))
			return requestDAO.getRequestId();
		return 0;
	}

	public ArrayList<Request> getRequests(User user) throws SQLException {
		return requestDAO.getRequests(user);
	}

	public boolean approveCar(int carId, int requestId) throws SQLException {
		requestDAO.deleteRequest(requestId);
		return requestDAO.approveCar(carId);
	}
}
