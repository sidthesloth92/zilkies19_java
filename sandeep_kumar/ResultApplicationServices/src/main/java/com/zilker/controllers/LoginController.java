package com.zilker.controllers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.beans.LoggedInUserData;
import com.zilker.delegates.LoginDelegator;

@RestController
@RequestMapping("/ResultApplication")
public class LoginController {
	
	@PostMapping("/login")
	public LoggedInUserData validateUser(@RequestParam("registrationNumber")String registrationNumber, @RequestParam("password")String password) {
		LoginDelegator login=new LoginDelegator();
		try {
			return login.isValidUser(Long.parseLong(registrationNumber), password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
