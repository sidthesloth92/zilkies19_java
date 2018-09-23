package com.zilker.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zilker.beans.Schedule;
import com.zilker.beans.Team;
import com.zilker.service.ServiceCrudOperations;

/**
 * Servlet implementation class ViewSchedule
 */
@WebServlet("/ViewSchedule")
public class ViewSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSchedule() {
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
			tournamentDetails=tournament.showTournament();
			request.setAttribute("tourDetails", tournamentDetails);
			request.getRequestDispatcher("/WEB-INF/pages/schedule.jsp").include(request, response);
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
		try {
			ArrayList matches = tournamentMatches.viewSchedule(tourname);
			String json = new Gson().toJson(matches);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		catch(Exception e) {
			response.getWriter().write("nothing");
		}
	}
	}
