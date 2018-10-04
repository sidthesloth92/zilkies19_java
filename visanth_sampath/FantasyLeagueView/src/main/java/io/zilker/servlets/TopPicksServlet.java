package io.zilker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.zilker.fantasy.delegates.UserDelegate;

/**
 * Servlet implementation class TopPicksServlet
 */
@WebServlet("/TopPicksServlet")
public class TopPicksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPicksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int matchId = Integer.parseInt(request.getParameter("match-id"));
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<String> playerNames = new UserDelegate().viewMostPicked(matchId);
		for(int index = 0 ; index < playerNames.size() ;index++) {
			out.println("<div class='row player'>");
			out.println("<div class='col-4 image'>");
			out.println("<img src='https://image.flaticon.com/icons/svg/53/53732.svg' alt='badge' title='top-picks' class='badge'>");
			out.println("</div>");
			out.println("<div class='col-8'>");
			out.println(playerNames.get(index));
			out.println("</div>");
            out.println("</div>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
