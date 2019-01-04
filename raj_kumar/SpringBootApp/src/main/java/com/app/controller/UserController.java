package com.app.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.User;
import com.app.delegate.UserDelegate;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDelegate userDelegate;
	Logger logger = Logger.getLogger(UserController.class.getName());

	// User Registration
	@PostMapping("/")
	public String userRegistration(@RequestBody User user) {
		logger.info("Entering userRegistration");
		String returnMessage = userDelegate.userRegistration(user);
		logger.info("Leaving userRegistration");
		return returnMessage;
	}

	// To Get All Users
	@GetMapping("/")
	public List<User> getAllUsers() {
		logger.info("Entering getAllUsers");
		List<User> userList = userDelegate.getAllUsers();
		logger.info("Leaving getAllUsers");
		return userList;
	}

	@GetMapping("/{id}")
	public Object getUser(@PathVariable("id") Integer userId) {
		logger.info("Entering into getUser UserController");
		Object foundUser = userDelegate.getUser(userId);
		logger.info("Leaving getUser UserController");
		return foundUser;
	}

	// To Delete a Particular user
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") Integer userId) {
		logger.info("Entering deleteUser");
		String returnMessage = userDelegate.deleteUser(userId);
		logger.info("Leaving deleteUser");
		return returnMessage;
	}

	// Updating a particular user
	@PutMapping("/{id}")
	public String updateUser(@RequestBody User user, @PathVariable("id") Integer userId) {
		String returnMessage = null;
		logger.info("Entering updateUser of UserController");
		returnMessage = userDelegate.updateUser(user, userId);
		logger.info("Leaving updateUser of User Controller");
		return returnMessage;
	}
}
