package com.app.delegate;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.User;
import com.app.constants.DisplayConstants;
import com.app.dao.UserDao;

@Service
public class UserDelegate {
	private static final Logger logger = Logger.getLogger(UserDelegate.class.getName());

	@Autowired
	private UserDao userDao;

	// User Registration
	public String userRegistration(User user) {
		logger.info("Entering userCreation");
		String returnMessage = "User Details Not Saved";
		User savedUser = null;
		try {
			savedUser = userDao.userRegistration(user);
		} catch (Exception e) {
			logger.info(DisplayConstants.EXCEPTION_ERR);
		}
		if (savedUser != null) {
			returnMessage = "User Details Saved";
		}
		logger.info("Leaving userCreation");
		return returnMessage;
	}

	// Get a particular User
	public Object getUser(int userId) {
		logger.info("Entering into getUser of UserDelegate");
		Optional<User> foundUser = null;
		try {
			foundUser = userDao.getUser(userId);
		} catch (Exception e) {
			logger.info(DisplayConstants.EXCEPTION_ERR);
		}
		if (foundUser.isPresent()) {
			return foundUser;
		} else {
			return "No Such User Found";
		}
	}

	// Getting All Users
	public List<User> getAllUsers() {
		logger.info("Entering getAllUsers of UserDelegate");
		List<User> usersList = null;
		try {
			usersList = userDao.getAllUsers();
		} catch (Exception e) {
			logger.info(DisplayConstants.EXCEPTION_ERR);
		}
		logger.info("Leaving getAllUsers of UserDelegate");
		return usersList;
	}

	// Delete a Particular User, Check if the user is present
	// if present delete the user
	public String deleteUser(int userId) {
		logger.info("Entering deleteUser of UserDelegate");
		String returnMessage = "No Such User is Found or an Error Occured while Deletion";
		try {
			// Need to Check if the boolean is true or not to find if Deleted
			boolean isUserPresent = userDao.isUserPresent(userId);
			if (isUserPresent) {
				userDao.deleteUser(userId);
				returnMessage = "User Deleted Successfully";
			}
		} catch (Exception e) {
			logger.info(DisplayConstants.EXCEPTION_ERR);
		}
		logger.info("Leaving deleteUser of UserDelegate");
		return returnMessage;
	}

	// Update User Information
	public String updateUser(User user, int userId) {
		logger.info("Entering into updateUser of UserDelegate");
		String returnMessage = "Error occured while updation";
		try {
			// Checking if the user is present to update it
			boolean isUserPresent = userDao.isUserPresent(userId);
			if (isUserPresent) {
				user.setId(userId);
				userDao.updateUser(user);
				returnMessage = "Updation was successfull";
			}
		} catch (Exception e) {
			logger.info(DisplayConstants.EXCEPTION_ERR);
		}
		logger.info("Leaving updateUser of UserDelegate");
		return returnMessage;
	}

}
