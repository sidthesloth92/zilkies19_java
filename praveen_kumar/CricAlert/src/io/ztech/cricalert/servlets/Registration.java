package io.ztech.cricalert.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.controller.UserController;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserController userController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        userController = new UserController();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
//		PrintWriter out = response.getWriter();
	    String name = request.getParameter("name");
	    String email = request.getParameter("email-id");
	    String userName = request.getParameter("username");
	    String password = request.getParameter("password");
	    User newUser = new User();
	    newUser.setName(name);
	    newUser.setEmail(email);
	    newUser.setUserName(userName);
	    newUser.setPassword(password);
	    if (userController.createUser(newUser)) {
	    	request.setAttribute("alertMessage", "You have successfully registered! You may log in now");
	    	request.setAttribute("visibility", "visibility: visible;");
	    	response.sendRedirect("pages/index.jsp");
	    } else {
	    	request.setAttribute("alertMessage", "Registration failed! User already exists. Choose a different username!");
	    	request.setAttribute("visibility", "visibility: visible;");
	    	response.sendRedirect("pages/index.jsp");
	    }
	}

}
