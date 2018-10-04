package io.zilker.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.delegates.UserDelegate;

/**
 * Servlet implementation class PickedTeamServlet
 */
@WebServlet("/PickedTeamServlet")
public class PickedTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PickedTeamServlet() {
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
		
		String playingTeam = request.getParameter("playing-team");
		String credits = request.getParameter("credits");
		int matchId = Integer.parseInt(request.getParameter("match-id"));
		String [] playingTeamStringArray = playingTeam.split(",");
		String [] creditsStringArray = credits.split(",");
		ArrayList<Integer> playingTeamArray = new ArrayList<Integer>();
		ArrayList<Integer> creditsArray = new ArrayList<Integer>();
		for(int i=0;i<playingTeamStringArray.length;i++) {
			playingTeamArray.add(Integer.parseInt(playingTeamStringArray[i]));
			creditsArray.add(Integer.parseInt(creditsStringArray[i]));
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		//System.out.println(user.getUserId());
		new UserDelegate().addTeam(user.getUserId() , matchId, playingTeamArray , creditsArray);
	}

}
