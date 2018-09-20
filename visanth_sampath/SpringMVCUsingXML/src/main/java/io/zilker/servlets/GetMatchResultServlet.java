package io.zilker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.zilker.fantasy.bean.ResultBoard;
import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.delegate.UserOperations;

/**
 * Servlet implementation class GetMatchResultServlet
 */
@WebServlet("/GetMatchResultServlet")
public class GetMatchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMatchResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int matchId = Integer.parseInt(request.getParameter("match-id"));
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		ResultBoard result = new UserOperations().viewLeaderBoard(user ,matchId);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<String> names = result.getNames();
		ArrayList<Integer> points = result.getMatchPoints();
		for(int index = 0 ; index < names.size() ;index++) {
			out.println("<div class='row'>");
			out.println("<div class='col-2'>");
			out.println(index+1);
			out.println("</div>");
			out.println("<div class='col-6'>");
			out.println(names.get(index));
			out.println("</div>");
			out.println("<div class='col-4'>");
			out.println(points.get(index));
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
