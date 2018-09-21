package com.example.demo.controller;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Register;
import com.example.demo.delegate.LoginDelegate;

@RestController
@RequestMapping("/demo")
public class Login {

	@RequestMapping(value="/login",
			       method=RequestMethod.POST,
			       consumes="application/json")
	public Register validateLogin(@RequestBody Register login){
		Logger log=Logger.getLogger("Login.class");
		LoginDelegate loginDelegate=new LoginDelegate();
		return loginDelegate.checkLoginCredential(login);
	}
}
