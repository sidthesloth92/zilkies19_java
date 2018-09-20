package com.ztech.io.fitnessfirstprototype.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztech.io.fitnessfirstprototype.beans.User;
import com.ztech.io.fitnessfirstprototype.beans.UserAccount;
import com.ztech.io.fitnessfirstprototype.delegates.AccountOperationsDelegator;

@WebServlet("/SignupPageServlet")
public class SignupPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignupPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/pages/signuppage.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmedPassword = request.getParameter("confirmedpassword");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		if (firstName.length() == 0 || !Pattern.matches("[a-zA-Z ]{2,30}", firstName)) {
			response.getWriter().print("invalidfirstname");
			return;
		}
		if (lastName.length() == 0 || !Pattern.matches("[a-zA-Z ]{2,30}", lastName)) {
			response.getWriter().print("invalidlastn	ame");
			return;
		}
		if (userName.length() == 0 || !Pattern.matches("[a-zA-Z0-9_ ]{2,30}", userName)) {
			response.getWriter().print("invalidusername");
			return;
		}
		String username = request.getParameter("username");
		User user = new User();
		user.setUsername(username);
		try {
			boolean alreadyExists = new AccountOperationsDelegator().ifUsernameExists(user);
			System.out.println(alreadyExists+"");
			if(alreadyExists) {
				response.getWriter().print("invalidusername");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (password.length() == 0 || !Pattern.matches("[\\S]{6,}", password)) {
			response.getWriter().print("invalidpassword");
			return;
		}
		if (confirmedPassword.length() == 0 || !password.equals(confirmedPassword)) {
			response.getWriter().print("wrongpassword");
			return;
		}

		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		java.util.regex.Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		if (email.length() == 0 || !matcher.find()) {
			response.getWriter().print("invalidemail");
			return;
		}
		
		try {
			boolean alreadyExists = new AccountOperationsDelegator().ifEmailExists(email);
			System.out.println(alreadyExists+"");
			if(alreadyExists) {
				response.getWriter().print("invalidemail");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String regex = "^[0-9]{10}$";
		if (phone.length() == 0 || !Pattern.matches(regex, phone)) {
			response.getWriter().print("invalidphone");
			return;
		}

		UserAccount newAccount = new UserAccount();
		newAccount.setFirstName(firstName);
		newAccount.setLastName(lastName);
		newAccount.setUserName(userName);
		newAccount.setPassword(password);
		newAccount.setEmailID(email);
		newAccount.setPhoneNo(phone);
		HttpSession session = request.getSession();
		session.setAttribute("userName", newAccount.getUserName());

		try {
			boolean signupFlag = new AccountOperationsDelegator().signUp(newAccount);
			response.getWriter().print(signupFlag+"");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.getWriter().print("Signupfailed");
		}

	}
}
