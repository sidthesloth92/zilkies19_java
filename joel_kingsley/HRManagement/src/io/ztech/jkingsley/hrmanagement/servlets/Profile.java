package io.ztech.jkingsley.hrmanagement.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.ztech.jkingsley.hrmanagement.beans.objects.Designation;
import io.ztech.jkingsley.hrmanagement.services.EmployeeManagement;
import io.ztech.jkingsley.hrmanagement.ui.InputHandler;

/**
 * Servlet implementation class Profile
 */
@WebServlet(description = "Servlet to handle Profile", urlPatterns = { "/dashboard/profile" })
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final static Logger LOGGER = Logger.getLogger(Profile.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
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
		
		io.ztech.jkingsley.hrmanagement.beans.objects.Profile profile = (io.ztech.jkingsley.hrmanagement.beans.objects.Profile) session.getAttribute("profile");
		
		if(profile == null) {
			session.invalidate();
			response.sendRedirect("/HR_Management/gateway");
			return;
		}
		
		EmployeeManagement employeeManagement = new EmployeeManagement();
		
		LOGGER.info("ProfileServlet:" + profile.getEmployee().getDesignation_id());
		
		Designation designation = employeeManagement.findDesignationOfDesignationID(profile.getEmployee().getDesignation_id());

		LOGGER.info("ProfileServlet:" + designation.getDesignation_name());
		
		session.setAttribute("designation", designation);
		request.getRequestDispatcher("/general/pages/profile.jsp").forward(request, response);		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
