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
 * Servlet implementation class RemoveTournament
 */
@WebServlet("/RemoveTournament")
public class RemoveTournament extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveTournament() {
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
		request.getRequestDispatcher("/WEB-INF/pages/RemoveTournament.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		String email = null;
		HttpSession sessionVar = request.getSession();
		email = (String) sessionVar.getAttribute("email");
		ServiceCrudOperations tournament=new ServiceCrudOperations();
		try {
			ArrayList tournamentlist=tournament.getTournament(email);
			for(int i=0;i<tournamentlist.size();i+=4) {
				String mobile=tournament.getMobile((int)tournamentlist.get(i+3));
				tournamentlist.set((i+3), mobile);
			}
			String json = new Gson().toJson(tournamentlist);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			
		}
	}

}
