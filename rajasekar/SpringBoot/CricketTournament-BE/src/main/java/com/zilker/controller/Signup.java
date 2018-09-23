package com.zilker.controller;

import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.dao.CricketTournamentDAO;
import com.zilker.delegates.DelegatesCrudOperations;
import com.zilker.beans.*;

@RestController


@RequestMapping("/login")
public class Signup {
    @RequestMapping(value = "/email", method = RequestMethod.GET)
	public Login isValidEmail(@RequestParam("email")String email) throws SQLException {
		boolean validate=false;
		DelegatesCrudOperations delegates=new DelegatesCrudOperations();
		Login credentials=new Login();
		try {
			CricketTournamentDAO tournament=new CricketTournamentDAO();
			credentials.setEmail(email);
			validate = delegates.isValidEmail(credentials);
			credentials.setLoginstatus(validate);
		}
		catch(SQLException e) {
			throw e;
		}
		return credentials;
	}
    
    @PostMapping(value = "/password")
	public Login isValidPassword(@RequestBody Login info) throws SQLException {
		boolean validate=false;
		DelegatesCrudOperations delegates=new DelegatesCrudOperations();
		Login credentials=new Login();
		try {
			CricketTournamentDAO tournament=new CricketTournamentDAO();
			credentials.setEmail(info.getEmail());
			credentials.setPassword(info.getPassword());
			validate = delegates.isValidCredentials(credentials);
			credentials.setLoginstatus(validate);
		}
		catch(SQLException e) {
			throw e;
		}
		return credentials;
	}
    
    @RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public Login getUserLoginInfo(@RequestParam("email")String email,@RequestParam String password) throws SQLException {
		boolean validate=false;
		DelegatesCrudOperations delegates=new DelegatesCrudOperations();
		Login credentials=new Login();
		try {
			CricketTournamentDAO tournament=new CricketTournamentDAO();
			credentials.setEmail(email);
			credentials.setPassword(password);
			validate = delegates.getUserLoginInfo(credentials);
			credentials.setLoginstatus(validate);
		}
		catch(SQLException e) {
			throw e;
		}
		return credentials;
	}
    
    @PostMapping(value = "/username")
	public String getUserName(@RequestBody Login info) throws SQLException {
		String user="";
		DelegatesCrudOperations delegates=new DelegatesCrudOperations();
		Login credentials=new Login();
		try {
			credentials.setEmail(info.getEmail());
			credentials.setPassword(info.getPassword());
			user = delegates.getUserName(credentials);
		}
		catch(SQLException e) {
			throw e;
		}
		return user;
	}
    
    @PostMapping(value = "/role")
	public boolean getRole(@RequestBody Login info) throws SQLException {
		boolean validate=false;
		String user="";
		DelegatesCrudOperations delegates=new DelegatesCrudOperations();
		Login credentials=new Login();
		try {
			validate=delegates.getRole(info);
		}
		catch(SQLException e) {
			throw e;
		}
		return validate;
	}
}
