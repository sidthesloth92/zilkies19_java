package io.ztech.autorate.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.delegate.RequestCarDelegate;

@RestController
@RequestMapping("/request")
public class RequestCarController {

	RequestCarDelegate requestCarDelegate = new RequestCarDelegate();

	@PostMapping("/addcar/{carId}/{username}")
	public int addCarUserRequest(@PathVariable int carId, @PathVariable String username) {
		try {
			return requestCarDelegate.addCarUserRequest(carId, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@PostMapping("/getrequests")
	public ArrayList<Request> getRequests(@RequestBody User user) {

		try {
			return requestCarDelegate.getRequests(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/approvecar/{carId}/{requestId}")
	public boolean approveCar(@PathVariable int carId, @PathVariable int requestId) {
		try {
			return requestCarDelegate.approveCar(carId, requestId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
