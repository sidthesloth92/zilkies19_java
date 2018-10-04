package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.zilker.beans.Team;
import com.zilker.service.ServiceCrudOperations;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class Scorecard
 */
@WebServlet("/Scorecard")
public class Scorecard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scorecard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String teamName=request.getParameter("teamname");
		ServiceCrudOperations scorecardfetch=new ServiceCrudOperations();
		try {
			int team_id=scorecardfetch.getteamId(teamName);
			ArrayList score=scorecardfetch.getScore(team_id);
			String json = new Gson().toJson(score);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
		}*/
		//ArrayList scorecard=scorecardfetch.getScore(teamName);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		ArrayList tournamentDetails=new ArrayList();
		ServiceCrudOperations tournament=new ServiceCrudOperations();
		try {
			String email= null;
			HttpSession sessionVar = request.getSession();
			email = (String) sessionVar.getAttribute("email");
			tournamentDetails=tournament.showTournament(email);
			request.setAttribute("tourDetails", tournamentDetails);
			request.getRequestDispatcher("/WEB-INF/pages/Scorecard.jsp").include(request, response);
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
		System.out.println(tournamentName);
		ServiceCrudOperations tournamentMatches=new ServiceCrudOperations();
		int tournamentId=0;
		try {
			tournamentId = tournamentMatches.getTourId(tournamentName);
		} catch (Exception e1) {
		}
		Team tourname=new Team();
		tourname.setTournamentId(tournamentId);
		ArrayList matches;
		try {
			matches = tournamentMatches.viewSchedule(tourname);
			String json = new Gson().toJson(matches);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
		}
		
	}

}
