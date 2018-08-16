package io.ztech.carstats.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.carstats.beans.Request;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.dao.RequestCarDAO;

public class RequestCarDelegate {
	RequestCarDAO rcdao = new RequestCarDAO();

	public boolean addCarUserRequest(Request request, User user, Specification specification) throws SQLException {
		return rcdao.addCarUserRequest(request, user, specification) && rcdao.getRequestId(request);
	}

	public ArrayList<Request> getRequests() {
		return rcdao.getRequests();
	}

	public boolean approveCar(Specification specification, Request request) {
		rcdao.deleteRequest(request);
		return rcdao.approveCar(specification);
	}
}
