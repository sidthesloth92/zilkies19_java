package io.ztech.autorate.controllers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.delegate.LoginDelegate;

@RestController
@RequestMapping("/login")
public class LoginController {
	LoginDelegate loginDelegate = new LoginDelegate();

	@PostMapping("/signup")
	public boolean signup(@RequestBody User user) {
		try {
			return loginDelegate.signup(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@PostMapping("/login")
	public boolean login(@RequestBody User user) {
		return loginDelegate.logoutLogin(true, user);
	}

	@PostMapping("/checkuser")
	public boolean isUser(@RequestBody User user) {
		try {
			return loginDelegate.isUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@PostMapping("/checkadmin")
	public boolean isAdmin(@RequestBody User user) {
		try {
			return loginDelegate.isAdmin(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
