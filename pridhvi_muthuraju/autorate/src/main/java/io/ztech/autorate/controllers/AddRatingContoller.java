package io.ztech.autorate.controllers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.delegate.AddRatingDelegate;

@RestController
@RequestMapping("/rating")
public class AddRatingContoller {

	AddRatingDelegate addRatingDelegate = new AddRatingDelegate();

	@PostMapping("/add/{carId}/{username}")
	public boolean addRating(@PathVariable int carId, @RequestBody Rating rating, @PathVariable String username) {
		try {
			return addRatingDelegate.addRating(carId, rating, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@PostMapping("/edit/{carId}/{username}")
	public boolean editRating(@PathVariable int carId, @RequestBody Rating rating, @PathVariable String username) {
		try {
			return addRatingDelegate.editRating(carId, rating, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@GetMapping("/checkrating/{carId}/{username}")
	public boolean isRating(@PathVariable int carId, @PathVariable String username) {
		try {
			return addRatingDelegate.isRating(carId, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
