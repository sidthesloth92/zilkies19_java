package com.ztech.FitnessApp.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ztech.FitnessApp.beans.CurrentUser;
import com.ztech.FitnessApp.beans.UserAccount;
import com.ztech.FitnessApp.delegates.UserAccountOperationDelegate;

@RestController
public class UserAccountOperationController {

	private static Logger logger;

	public UserAccountOperationController() {
		logger = Logger.getLogger(UserAccountOperationController.class.getName());
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public CurrentUser signup(@RequestBody UserAccount newAccount) {
		logger.info("enter signup @ UserAccountOperationController");

		boolean accountAdded = new UserAccountOperationDelegate().signUp(newAccount);

		if (accountAdded) {
			CurrentUser user = new CurrentUser();
			user.setUserName(newAccount.getUserName());
			user.setPassword(newAccount.getPassword());
			logger.info("exit signup @ UserAccountOperationController with signedin user object");
			return user;
		} else {
			logger.info("exit signup @ UserAccountOperationController with null (not signed in)");
			return null;
		}

	}

	@RequestMapping(value = "/CheckIfUserNameExists", method = RequestMethod.GET)
	public boolean ifUserNameExists(@RequestParam("userName") String userName) {
		return new UserAccountOperationDelegate().ifUserNameExists(userName);
	}
	
	@RequestMapping(value = "/CheckIfEmailExists", method = RequestMethod.GET)
	public boolean ifEmailExists(@RequestParam("email") String email) {
		return new UserAccountOperationDelegate().IfEmailExists(email);
	}

}
