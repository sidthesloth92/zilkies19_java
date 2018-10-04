package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.zilker.service.ServiceCrudOperations;

/**
 * Servlet implementation class EditPage
 */
@WebServlet("/EditPage")
public class EditPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPage() {
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
		ArrayList tournamentDetails=new ArrayList();
		ServiceCrudOperations tournament=new ServiceCrudOperations();
		try {
			String email = null;
			HttpSession sessionVar = request.getSession();
			email = (String) sessionVar.getAttribute("email");
			tournamentDetails=tournament.showTournament(email);
			request.setAttribute("tournamentDetails", tournamentDetails);
			request.getRequestDispatcher("/WEB-INF/pages/EditPage.jsp").include(request, response);
		} catch (Exception e) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		String tournamentName=request.getParameter("tournamentname");
		HttpSession sessionVar = request.getSession();
		String email = (String) sessionVar.getAttribute("email");
		System.out.println("inside edit page servlet "+tournamentName+" "+email);
		ServiceCrudOperations tournament=new ServiceCrudOperations();
		try {
			int tourid=tournament.getTourId(tournamentName);
			String teamname=tournament.getTeamName(tourid,email);
			int teamid=tournament.getteamId(teamname);
			System.out.println("teamid in edit page:"+teamid);
			ArrayList players=tournament.getplayers(teamid);
			players.add(0,teamname);
			String json = new Gson().toJson(players);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		} catch (SQLException e) {
		}
	}

}
