package io.ztech.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.ztech.delegates.EmployeeDelegate;

@Controller
public class EmployeeController {
	EmployeeDelegate employeeDelegate;
	
	@RequestMapping("/GetTable")
	public ModelAndView getTable() {
		employeeDelegate = new EmployeeDelegate();
		return employeeDelegate.getTable();
	}
	
	@RequestMapping("/ValidateForm")
	public String validateForm(HttpServletRequest request, HttpServletResponse response) {
		return employeeDelegate.validateForm(request.getParameter("fullname"), request.getParameter("designation"), request.getParameter("manager"));
	}
}
