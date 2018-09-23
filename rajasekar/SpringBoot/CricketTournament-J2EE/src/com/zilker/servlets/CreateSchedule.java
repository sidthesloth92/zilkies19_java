package com.zilker.servlets;

import java.io.IOException;
import com.zilker.service.*;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.zilker.beans.*;
import com.zilker.constant.ConsoleStrings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zilker.service.ServiceCrudOperations;

/**
 * Servlet implementation class CreateSchedule
 */
@WebServlet("/CreateSchedule")
public class CreateSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSchedule() {
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
		String email = null;
		HttpSession sessionVar = request.getSession();
		email = (String) sessionVar.getAttribute("email");
		ArrayList tournamentDetails=new ArrayList();
		ServiceCrudOperations tournament=new ServiceCrudOperations();
		try {
			tournamentDetails=tournament.showTournament(email);
			request.setAttribute("tourDetails", tournamentDetails);
			request.getRequestDispatcher("/WEB-INF/pages/ScheduleMatch.jsp").include(request, response);
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
			int status = tournamentMatches.deleteFixtures(tourname);
			ArrayList<String> teamList = tournamentMatches.viewTeams(tourname);
			ArrayList<String> arrayList = tournamentMatches.schedule(teamList);
			for (int i = 0; i < arrayList.size(); i++) {
				String array[] = arrayList.get(i).split("v");
				int[] teamId=new int[2];
				teamId = tournamentMatches.getTeamId(array);
				Schedule scheduleobj = new Schedule();
				scheduleobj.setTeamOneId(teamId[0]);
				scheduleobj.setTeamtwoId(teamId[1]);
				scheduleobj.setMatches(arrayList.get(i));
				scheduleobj.setTournamentId(tournamentId);
				if (status == 1) {
					tournamentMatches.fixtures(scheduleobj);
				}
			}
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
