package com.ztech.io.fitnessfirstprototype.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.io.fitnessfirstprototype.beans.User;
import com.ztech.io.fitnessfirstprototype.delegates.AccountOperationsDelegator;

/**
 * Servlet implementation class UsernameCheckServlet
 */
@WebServlet("/UsernameCheckServlet")
public class UsernameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsernameCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = new User();
		user.setUsername(username);
		try {
			boolean alreadyExists = new AccountOperationsDelegator().ifUsernameExists(user);
			System.out.println(alreadyExists+"");
			response.getWriter().print(alreadyExists);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
