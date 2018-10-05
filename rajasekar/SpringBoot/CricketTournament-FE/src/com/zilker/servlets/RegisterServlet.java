package com.zilker.servlets;

import java.io.IOException;

import com.zilker.beans.*;
import com.zilker.delegates.*;
import com.zilker.service.*;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	boolean validateFirstName(String firstName) {
		boolean flag = true;
		for (int i = 0; i < firstName.length(); i++) {
			if (Character.isDigit(firstName.charAt(i))) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	boolean validateLastName(String lastName) {
		boolean flag = true;
		for (int i = 0; i < lastName.length(); i++) {
			if (Character.isDigit(lastName.charAt(i))) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	boolean validatePassword(String password) {
		boolean flag = true;
		if (password.length() <= 0 || password.length() >= 15) {
			flag = false;
		}
		return flag;
	}

	boolean validateAge(String age) {
		boolean flag = true;
		for (int i = 0; i < age.length(); i++) {
			if (!(Character.isDigit(age.charAt(i)))) {
				flag = false;
			}
		}
		return flag;
	}

	boolean validateMobile(String mobile) {
		boolean flag = true;
		for (int i = 0; i < mobile.length(); i++) {
			if (!(Character.isDigit(mobile.charAt(i)))) {
				flag = false;
			}
		}
		return flag;
	}

	boolean validateEmail(String email) {
		boolean flag = true;
		if (email == "") {
			flag = false;
		}
		return flag;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		UserDetails user=new UserDetails();
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String password = request.getParameter("regpassword");
		String age = request.getParameter("age");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("regemail");
		boolean flag = true;
		flag = validateFirstName(firstName);
		flag = validateLastName(lastName);
		flag = validatePassword(password);
		flag = validateAge(age);
		flag = validateMobile(mobile);
		flag = validateEmail(email);
		if (flag) {
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setAge(Integer.parseInt(age));
			user.setMobile(mobile);
			user.setEmail(email);
			user.setRole("user");
			ServiceCrudOperations registeruser=new ServiceCrudOperations();
			if(flag) {
			try {
				registeruser.insertUserDetails(user);
				ArrayList tournamentDetails=new ArrayList();
				ServiceCrudOperations tournament=new ServiceCrudOperations();
				tournamentDetails=tournament.showTournament();
				request.setAttribute("tournamentDetails", tournamentDetails);
				request.getRequestDispatcher("/WEB-INF/pages/UserHomePage.jsp").include(request,response);
			}
			catch(Exception e) {
				
			}}
			else {
				String message="Not Registered";
				request.getRequestDispatcher("/WEB-INF/pages/UserHomePage.jsp?message="+ URLEncoder.encode(message, "UTF-8")).include(request,response);
			}
			
		}
	}

}
