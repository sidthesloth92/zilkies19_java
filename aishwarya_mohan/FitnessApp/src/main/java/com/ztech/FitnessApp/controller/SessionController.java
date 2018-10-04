package com.ztech.FitnessApp.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ztech.FitnessApp.beans.CurrentUser;
import com.ztech.FitnessApp.dao.UserAccountDao;

@RestController
public class SessionController {
	private static Logger logger;

	public SessionController() {
		logger = Logger.getLogger(SessionController.class.getName());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public CurrentUser login(@RequestBody CurrentUser user) {
		logger.info("enter login @ SessionController");
		
//		input : userName, password

		boolean userSession = new UserAccountDao().login(user);

		logger.info("exit login @ SessionController");

		if (userSession) {
			return user;
		} else {
			return null;
		}
	}

}
