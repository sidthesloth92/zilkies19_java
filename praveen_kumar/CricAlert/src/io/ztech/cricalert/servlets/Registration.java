package io.ztech.cricalert.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String nameRegex = "^[a-zA-Z ]{2,30}$";
	    String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
	    String name = request.getParameter("name");
	    String email = request.getParameter("email-id");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    if (!Pattern.matches(nameRegex, name)) {
	    	out.println("<h1> Invalid name entered! </h1>");
	    }
	    if (!Pattern.matches(emailRegex, email)) {
	    	out.println("<h1> Invalid name entered! </h1>");
	    }
	    if (username.equals("")) {
	    	out.println("<h1> Username is required! </h1>");
	    }
	    if (password.equals("")) {
	    	out.println("<h1> Password is required! </h1>");
	    }
	    out.println("<h1> Successfully registered! </h1>");
//		doGet(request, response);
	}

}
