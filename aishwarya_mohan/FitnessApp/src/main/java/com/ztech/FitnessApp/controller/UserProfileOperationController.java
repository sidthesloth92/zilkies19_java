package com.ztech.FitnessApp.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ztech.FitnessApp.beans.UserProfile;
import com.ztech.FitnessApp.delegates.ProfileOperationDelegator;

@RestController
public class UserProfileOperationController {
	private static Logger logger;
	private static ProfileOperationDelegator profileDelegateObject;

	public UserProfileOperationController() {
		logger = Logger.getLogger(UserProfileOperationController.class.getName());
		profileDelegateObject = new ProfileOperationDelegator();
	}

	@RequestMapping(value = "/loggedin-user-get-profile/{userName}", method = RequestMethod.GET)
	public UserProfile getProfile(@PathVariable("userName") String userName) {
		logger.info("enter getProfile @ UserProfileOperationController");

		UserProfile profile = profileDelegateObject.getProfile(userName);

		logger.info("exit getProfile @ UserProfileOperationController");
		return profile;
	}

}
