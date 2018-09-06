package io.ztech.cricalert.servlets;


import java.io.IOException;
import java.io.PrintWriter;

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username");
	    String password = request.getParameter("password");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		User verifiedUser = userController.verifyUser(user);
		if (verifiedUser == null) {
			out.println("<h1> Failed to log in! </h1>");
		} else {
			System.out.println("Verified user details are: ");
			System.out.println(verifiedUser.getName() + " " + verifiedUser.getEmail());
//			request.getRequestDispatcher("pages/home.jsp").forward(request, response);
			response.sendRedirect("pages/home.jsp");
		}
	}

}
