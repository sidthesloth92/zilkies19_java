package io.zilker.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.utility.UserValidator;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public ModelAndView validateUser(HttpServletRequest request , HttpServletResponse response) {
		String userName = request.getParameter("user-name");
		String password = request.getParameter("password");
		ModelAndView model = new ModelAndView("add-match");
		User newUser = new UserValidator().enterLoginDetails(userName, password);
		model.addObject("user" ,newUser);
		if(newUser.getUserType()==1) {
			return model;
		}
		return null;
		
	}
	
}
