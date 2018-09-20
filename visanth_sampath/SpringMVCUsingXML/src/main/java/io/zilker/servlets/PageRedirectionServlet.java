package io.zilker.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageRedirectionServlet
 */
@WebServlet("/PageRedirectionServlet")
public class PageRedirectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageRedirectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = (String) request.getParameter("page-name");
		if(page.equals("user-home")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/user-home.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("available-matches")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/available-matches.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("matches-upcoming")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/matches-upcoming.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("leaderboard")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/leaderboard.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("top-picks")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/top-picks.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("add-match")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/add-match.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("add-player")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/add-player.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("edit-rating")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/edit-rating.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("end-match")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/end-match.jsp");
			dispatcher.forward(request, response);
		}
		//RequestDispatcher dispatcher = request.getRequestDispatcher("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
