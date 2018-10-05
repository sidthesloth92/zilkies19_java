package com.zilker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zilker.beans.*;
import com.zilker.constant.ConsoleStrings;
import com.zilker.service.ServiceCrudOperations;

/**
 * Servlet implementation class RegisterTeam
 */
@WebServlet("/RegisterTeam")
public class RegisterTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterTeam() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		ServiceCrudOperations tournament = new ServiceCrudOperations();
		try {
			String email = null;
			HttpSession sessionVar = request.getSession();
			email = (String) sessionVar.getAttribute("email");
			String tournamentName = String.valueOf(request.getParameter("tourname"));
			int flag = 0;
			ServiceCrudOperations tour = new ServiceCrudOperations();
			int tournamentid=tour.getTourId(tournamentName);
			flag = tour.isUserAlreadyRegistered(email,tournamentid);
			System.out.println("flag is:"+flag);
			PrintWriter out = response.getWriter();
			if (flag == 1) {
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('You are Already Registered');");
				   out.println("location='http://localhost:8080/CricketTour/Home';");
				   out.println("</script>");
			} else {
				request.getRequestDispatcher("/WEB-INF/pages/TeamRegistration.jsp?tourname=" + tournamentName + "")
						.include(request, response);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		System.out.println("inside register team servlet");
		String[] firstname = request.getParameterValues("firstname");
		String[] lastname = request.getParameterValues("lastname");
		String[] role = request.getParameterValues("role");
		String[] teamname = request.getParameterValues("teamname");
		String tournamentName = request.getParameter("tourname");
		String check = tournamentName;
		if (tournamentName == null) {
			System.out.println("yes");
			tournamentName = request.getParameter("dropdown1");
		}
		HttpSession sessionVar = request.getSession();
		String email = (String) sessionVar.getAttribute("email");
		ServiceCrudOperations tournament = new ServiceCrudOperations();
		Login login = new Login();
		login.setEmail(email);
		try {
			int tournamentId = tournament.getTourId(tournamentName);
			int userid = tournament.getUserId(login);
			ArrayList<Player> playersDetails = new ArrayList<Player>();
			int teamid = 0;
			Team teamobj = new Team();
			teamobj.setTournamentId(tournamentId);
			teamobj.setTeamName(teamname[0]);
			teamobj.setEmail(email);
			if (check != null) {
				tournament.addTeam(teamobj, userid);
				teamid = tournament.getteamId(teamname[0]);
				System.out.println(firstname.length + " " + lastname.length + " " + role.length);
				for (int i = 0; i < firstname.length; i++) {
					Player players = new Player();
					players.setPlayerName(firstname[i]);
					players.setPlayerLastName(lastname[i]);
					players.setPlayerRole(role[i]);
					players.setTeamId(teamid);
					playersDetails.add(players);
				}
				System.out.println("check is" + check);
				tournament.addPlayer(playersDetails, userid);
			} else {
				teamid = tournament.getteamId(teamname[0]);
				int playerid = tournament.getPlayerId(teamid);
				playerid = playerid - 11;
				System.out.println("teamname " + teamname);
				System.out.println("yea " + playerid + " " + teamid);
				for (int i = 0; i < firstname.length; i++) {
					Player players = new Player();
					players.setPlayerName(firstname[i]);
					players.setPlayerLastName(lastname[i]);
					players.setPlayerRole(role[i]);
					players.setTeamId(teamid);
					playersDetails.add(players);
				}
				tournament.updatePlayer(playersDetails, userid, playerid);
			}
			ArrayList tournamentDetails = tournament.showTournament();
			request.setAttribute("tournamentDetails", tournamentDetails);
			request.getRequestDispatcher("/WEB-INF/pages/UserHomePage.jsp").include(request, response);
			request.getRequestDispatcher("/WEB-INF/pages/UserHomePage.jsp").include(request, response);

		} catch (SQLException e) {

		}

	}

}
