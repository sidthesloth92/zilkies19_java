package io.ztech.placementportal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

@WebServlet("/checkRegisterNumberExists")
public class CheckRegisterNumberExists extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public CheckRegisterNumberExists() {
		super();
		log = Logger.getLogger("CheckRegisterNumberExists.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Register register = new Register();
		RetrieveDetailDelegate delegate = new RetrieveDetailDelegate();
		register.setReg_no(request.getParameter("registerNumber"));
		try {
			if (delegate.checkIsAvailable(register.getReg_no(), ApplicationConstants.REGISTER_NO)) {
				response.getWriter().write("exists");
			}
		} catch (SQLException e) {
			log.warning(e.toString());
		}
	}

}
