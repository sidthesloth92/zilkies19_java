package io.zilker.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.fantasy.delegate.AdminOperations;

/**
 * Servlet implementation class AddMatch
 */
@WebServlet("/AddMatch")
public class AddMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String teamOne = request.getParameter("team-one");
		String teamTwo = request.getParameter("team-two");
		String startDate = request.getParameter("start-date");
		String startTime = request.getParameter("start-time");
		String endTime = request.getParameter("end-time");
		int credits = Integer.parseInt(request.getParameter("credits"));
		startTime+=":00";
		endTime+=":00";
		System.out.println(credits);
		new AdminOperations().scheduleNewMatch(teamOne, teamTwo, startDate, startTime, endTime, credits);
		//new AdminService().scheduleNewMatchCaller(teamOne, teamTwo, startDate, startTime, endTime, credits);
	}

}
