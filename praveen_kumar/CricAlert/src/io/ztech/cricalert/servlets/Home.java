package io.ztech.cricalert.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalert.beans.Match;
import io.ztech.cricalert.beans.User;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User user = (User) request.getSession(false).getAttribute("user");
		ArrayList<Match> liveMatchList = new ArrayList<>();
		ArrayList<Match> upcomingMatchList = new ArrayList<>();
		ArrayList<Match> pastMatchList = new ArrayList<>();
		ArrayList<Match> matchList = user.getMatches();
		
		for (Match match : matchList) {
			if (match.getStatus().equals("scheduled")) {
				upcomingMatchList.add(match);
			} else if (match.getStatus().equals("ongoing")) {
				liveMatchList.add(match);
			} else if (match.getStatus().equals("completed")) {
				pastMatchList.add(match);
			}
		}
		request.setAttribute("liveMatchList", liveMatchList);
		request.setAttribute("upcomingMatchList", upcomingMatchList);
		request.setAttribute("pastMatchList", pastMatchList);
		
		request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
