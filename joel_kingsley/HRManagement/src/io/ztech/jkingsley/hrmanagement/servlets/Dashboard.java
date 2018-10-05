package io.ztech.jkingsley.hrmanagement.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.ztech.jkingsley.hrmanagement.beans.objects.Profile;
import io.ztech.jkingsley.hrmanagement.ui.InputHandler;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet(description = "Servlet to handle dashboard endpoint", urlPatterns = { "/dashboard" })
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final static Logger LOGGER = Logger.getLogger(Dashboard.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect("/HR_Management/gateway");
			return;
		}
		
		Profile profile = (Profile) session.getAttribute("profile");
		
		if(profile == null) {
			session.invalidate();
			response.sendRedirect("/HR_Management/gateway");
			return;
		}
		
		switch(profile.getEmployee().getAccount_type()) {
		case EMPLOYEE_LIMITED:
		case EMPLOYEE_MAX:
			request.getRequestDispatcher("/employee/pages/dashboard.jsp").forward(request, response);
			break;
		case MANAGER:
			request.getRequestDispatcher("/manager/pages/dashboard.jsp").forward(request, response);
			break;
		case ADMIN:
			request.getRequestDispatcher("/admin/pages/dashboard.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
