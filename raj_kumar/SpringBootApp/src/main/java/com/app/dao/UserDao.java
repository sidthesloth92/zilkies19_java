package com.app.dao;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.beans.User;
import com.app.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	UserRepository userRepository;
	private static final Logger logger = Logger.getLogger(UserDao.class.getName());

	// New User Registration
	public User userRegistration(User user) throws Exception {
		logger.info("Entering UserDAO");
		User savedUser = userRepository.save(user);
		logger.info("Leaving UserDAO");
		return savedUser;
	}

	// Get a particular User
	public Optional<User> getUser(int userId) throws Exception {
		logger.info("Entering into getUser of UserDao");
		Optional<User> foundUser = userRepository.findById(userId);
		logger.info("Leaving getUser of UserDao");
		return foundUser;
	}

	// Getting all users
	public List<User> getAllUsers() throws Exception {
		logger.info("Entering getAllUsers of UserDAO");
		List<User> userList = userRepository.findAll();
		logger.info("Leaving getAllUsers of USerDAO");
		return userList;
	}

	// Delete User
	public void deleteUser(int userId) throws Exception {
		logger.info("Entering deleteUser of UserDao");
		userRepository.deleteById(userId);
		logger.info("Leaving deleteUser of UserDao");
	}

	// To check if user is present in the records
	public boolean isUserPresent(int userId) throws Exception {
		logger.info("Entering into isUserPresent in UserDao");
		boolean isUserPresent = userRepository.existsById(userId);
		logger.info("Leaving is isUserPresent in UserDao");
		return isUserPresent;
	}

	public void updateUser(User user) throws Exception {
		logger.info("Entering into updateUser of UserDao");
		userRepository.save(user);
		logger.info("Leaving updateUser of UserDao");
	}
}
