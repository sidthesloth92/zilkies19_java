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

/**
 * Servlet implementation class LeaveTracker
 */
@WebServlet(description = "Servlet that handles Leave Tracker", urlPatterns = { "/leave-tracker" })
public class LeaveTracker extends HttpServlet {
	
	private final Logger LOGGER = Logger.getLogger(LeaveTracker.class.getName());
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveTracker() {
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
		
		LOGGER.info("Profile Name:" + profile.getEmployee().getAccount_type());
		
		switch(profile.getEmployee().getAccount_type()) {
		case EMPLOYEE_LIMITED:
		case EMPLOYEE_MAX:
			request.getRequestDispatcher("/employee/pages/leave-tracker.jsp").forward(request, response);
			break;
		case MANAGER:
			request.getRequestDispatcher("/manager/pages/leave-tracker.jsp").forward(request, response);
			break;
		case ADMIN:
			request.getRequestDispatcher("/admin/pages/leave-tracker.jsp").forward(request, response);
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
