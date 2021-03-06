package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.zilker.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.service.ServiceCrudOperations;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Logout() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user","");
		request.getSession().invalidate();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		ArrayList tournamentDetails=new ArrayList();
		ServiceCrudOperations tournament=new ServiceCrudOperations();
		try {
			tournamentDetails=tournament.showTournament();
		} catch (Exception e) {
		}
		request.setAttribute("tournamentDetails", tournamentDetails);
		request.getRequestDispatcher("/WEB-INF/pages/UserHomePage.jsp").include(request,response);
	}

}
