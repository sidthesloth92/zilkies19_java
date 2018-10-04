package io.ztech.jkingsley.hrmanagement.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.ztech.jkingsley.hrmanagement.beans.objects.Profile;
import io.ztech.jkingsley.hrmanagement.services.EmployeeLogin;
import io.ztech.jkingsley.hrmanagement.services.EmployeeManagement;
import io.ztech.jkingsley.hrmanagement.ui.InputHandler;

/**
 * Servlet implementation class Gateway
 */
@WebServlet(description = "Servlet to handle registration and login", urlPatterns = { "/gateway" })
public class Gateway extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOGGER = Logger.getLogger(Gateway.class.getName());

    /**
     * Default constructor. 
     */
    public Gateway() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		BigInteger empId = BigInteger.valueOf(Long.parseLong(request.getParameter("emp-id")));
	    String password = request.getParameter("password");
	    
		EmployeeLogin employeeLogin = new EmployeeLogin();
		EmployeeManagement  employeeManagement = new EmployeeManagement();
		
		boolean verifiedUser = employeeLogin.isCorrect(empId, password);
		
		if (verifiedUser == false) {
			
			request.setAttribute("alertMessage", "Incorrect username or password. Failed to sign in!");
	    	request.setAttribute("visibility", "visibility: visible;");
	    	request.getRequestDispatcher("/HR_Management/index.jsp").forward(request, response);
		} else {
			
			HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //generate a new session
            HttpSession newSession = request.getSession(true);

            //setting session to expiry in 5 mins
            //newSession.setMaxInactiveInterval(5*60);
            
            Profile profile = employeeManagement.findEmployeeById(empId);
            
			newSession.setAttribute("profile", profile);
			response.sendRedirect("/HR_Management/dashboard");
		}
	}

}
