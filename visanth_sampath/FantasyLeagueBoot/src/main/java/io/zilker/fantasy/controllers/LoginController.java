package io.zilker.fantasy.controllers;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.utility.UserValidator;

@RestController
@RequestMapping("/FantasyLeague")
public class LoginController {
	
	@RequestMapping(value = "/Login" , method = RequestMethod.POST )
	public User checkPerson(@RequestParam("name") String userName, @RequestParam("password") String password) {
		User user = new UserValidator().enterLoginDetails(userName, password);
		return user;
	}
	
	@RequestMapping(value = "/SignUp" , method = RequestMethod.POST )
	public JSONObject addPerson(@RequestParam("name") String userName, @RequestParam("email") String email, @RequestParam("password") String password) {
		boolean status =  new UserValidator().enterSignUpDetails(userName , email , password);
		JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
	

}
