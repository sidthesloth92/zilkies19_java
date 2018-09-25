package io.ztech.autorate.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.delegates.RequestCarDelegate;

/**
 * Servlet implementation class FetchRequestsServlet
 */
@WebServlet("/FetchRequestsServlet")
public class FetchRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestCarDelegate requestCarDelegate = new RequestCarDelegate();
	public static final Logger logger = Logger.getLogger(FetchRequestsServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchRequestsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		if(request.getSession().getAttribute("status").equals("USER")) {
			user.setUsername(request.getSession().getAttribute("username").toString());
			user.setAdminStatus("USER");
		}
		ArrayList<Request> requests = null;
		try {
			requests = requestCarDelegate.getRequests(user);
		} catch (Exception e) {
			logger.info(AppConstants.ERROR_DATA);
		}
		request.setAttribute("requests", requests);
		if(request.getSession().getAttribute("status").equals("ADMIN"))
			request.getRequestDispatcher("WEB-INF/pages/admin-requests.jsp").forward(request, response);
		else if(request.getSession().getAttribute("status").equals("USER")) {
			request.getRequestDispatcher("WEB-INF/pages/user-requests.jsp").forward(request, response);
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
