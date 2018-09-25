package io.ztech.autorate.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.delegate.FetchCarsDelegate;

@RestController
@RequestMapping("/fetch")
public class FetchDetailsController {

	FetchCarsDelegate fetchCarsDelegate = new FetchCarsDelegate();

	@PostMapping("/makes")
	public ArrayList<Make> displayMakes(@RequestBody CarType carType) {
		try {
			return fetchCarsDelegate.displayMakes(carType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/cars/{makeId}/{carTypeId}")
	public ArrayList<Specification> getCars(@PathVariable int makeId, @PathVariable int carTypeId) {
		try {
			return fetchCarsDelegate.getCars(makeId, carTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/cartypes")
	public ArrayList<CarType> displayCarTypes(@RequestBody Make make) {
		try {
			return fetchCarsDelegate.displayCarTypes(make);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/ratings")
	public HashMap<String, Rating> displayRating(@RequestBody Specification specification) {
		try {
			return fetchCarsDelegate.displayRating(specification);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/rating/{carId}/{username}")
	public Rating getRating(@PathVariable int carId, @PathVariable String username) {
		try {
			return fetchCarsDelegate.getRating(carId, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/car")
	public Specification getCar(@RequestBody Request request) {
		try {
			return fetchCarsDelegate.getCar(request);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
