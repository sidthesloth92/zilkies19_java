package io.ztech.servlets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/login")
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SampleServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		response.getWriter().append("Email:" + email + "\n" + "Password:" + password);
		if(!isValid(email,"^[A-Za-z0-9\\.]+@[a-z]+\\.[a-z]{2,6}")) {
			response.getWriter().append("Invalid email entered");
		}
	}
	
	public static boolean isValid(String string, String regex) {
		if(string.equals("-1")) {
			return false;
		} else {
			Matcher matcher = Pattern.compile(regex,Pattern.CASE_INSENSITIVE).matcher(string);
			return matcher.find();
		}
	}

}
