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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserController userController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        userController = new UserController();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("username");
	    String password = request.getParameter("password");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		User verifiedUser = userController.verifyUser(user);
		if (verifiedUser == null) {
			request.setAttribute("alertMessage", "Incorrect username or password. Failed to sign in!");
	    	request.setAttribute("visibility", "visibility: visible;");
	    	request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("user", verifiedUser);
			request.getRequestDispatcher("/Home").forward(request, response);
		}
	}

}
