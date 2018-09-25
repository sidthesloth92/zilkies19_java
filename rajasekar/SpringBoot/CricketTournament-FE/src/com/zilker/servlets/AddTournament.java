package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.beans.*;
import com.zilker.service.ServiceCrudOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTournament
 */
@WebServlet("/AddTournament")
public class AddTournament extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTournament() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		request.getRequestDispatcher("/WEB-INF/pages/AddTournamentRequest.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		String email=request.getParameter("email");
		String tourname=request.getParameter("tourname");
		String tourformat=request.getParameter("tourformat");
		ServiceCrudOperations tournament=new ServiceCrudOperations();
		Login credentials=new Login();
		credentials.setEmail(email);
		try {
			int userid=tournament.getUserId(credentials);
			System.out.println("userid is:"+userid);
			com.zilker.beans.Tournament tour=new com.zilker.beans.Tournament();
			tour.setEmail(email);
			tour.setFormat(tourformat);
			tour.setStatus(0);
			tour.setTournamentName(tourname);
			tour.setUserId(userid);
			tournament.insertTournament(tour);
			ArrayList tournamentDetails=new ArrayList();
			tournamentDetails=tournament.showTournament();
			request.setAttribute("tournamentDetails", tournamentDetails);
			request.getRequestDispatcher("/WEB-INF/pages/UserHomePage.jsp").include(request, response);
		} catch (SQLException e) {
			
		}
	}

}
