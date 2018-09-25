package io.ztech.cricalertbe.controllers;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.cricalertbe.beans.User;
import io.ztech.cricalertbe.delegates.UserDelegate;

@RestController
@RequestMapping("CricAlertBE/users")
public class UserController {

	@PostMapping("/verify")
	public User verifyUser(@RequestBody User user) {
		Logger logger = Logger.getLogger(UserController.class.getName());
		logger.info("Entered UserController");
		UserDelegate userDelegate = new UserDelegate();
		User returnUser = userDelegate.verifyUser(user);
		logger.info("Exited UserController");
		return returnUser;
	}

	@PostMapping("/create")
	public boolean createUser(@RequestBody User newUser) {
		UserDelegate userDelegate = new UserDelegate();
		return userDelegate.createUser(newUser);
	}
}
