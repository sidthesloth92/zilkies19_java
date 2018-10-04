package io.ztech.cricalertfe.servlets;


import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalertfe.beans.User;
import io.ztech.cricalertfe.constants.Regex;
import io.ztech.cricalertfe.constants.UserMessages;
import io.ztech.cricalertfe.delegates.Validator;
import io.ztech.cricalertfe.delegates.UserDelegate;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        logger = Logger.getLogger(Registration.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered Registration.java");
		UserDelegate userDelegate = new UserDelegate();
		Validator validator = new Validator();
	    String name = request.getParameter("name");
	    String email = request.getParameter("email-id");
	    String userName = request.getParameter("username");
	    String password = request.getParameter("password");
	    User newUser = new User();
	    newUser.setName(name);
	    newUser.setEmail(email);
	    newUser.setUserName(userName);
	    newUser.setPassword(password);
	    if (validator.validateInput(Regex.nameRegex, newUser.getName(), UserMessages.INVALID_FIRST_NAME) && validator.validateInput(Regex.emailRegex, newUser.getEmail(), UserMessages.INVALID_EMAIL)) {
	    	if (userDelegate.createUser(newUser)) {
	    		request.setAttribute("alertMessage", "You have successfully registered! You may log in now");
		    	request.setAttribute("visibility", "visibility: visible;");
		    	response.sendRedirect("pages/index.jsp");
	    	} else {
		    	request.setAttribute("alertMessage", "Registration failed! User already exists. Choose a different username!");
		    	request.setAttribute("visibility", "visibility: visible;");
		    	response.sendRedirect("pages/index.jsp");
		    }
	    } else {
	    	request.setAttribute("alertMessage", "Registration failed! Enter correct credentials!");
	    	request.setAttribute("visibility", "visibility: visible;");
	    	response.sendRedirect("pages/index.jsp");
	    }
	    logger.info("Exited Registration.java");
	}

}
