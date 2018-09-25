package io.ztech.autorate.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		if (request.getHeader("Referer").contains("AddCarServlet")||request.getHeader("Referer").contains("add-car.jsp") || request.getHeader("Referer").contains("admin")
				|| request.getHeader("Referer").contains("user-requests")
				|| request.getHeader("Referer").contains("FetchRequestsServlet")
				|| request.getHeader("Referer").contains("EditCarServlet")
				|| request.getHeader("Referer").contains("FetchAdminServlet")
				|| request.getHeader("Referer").contains("LoginServlet")) {
			response.sendRedirect("/autoratefe/IndexServlet");
			return;
		} else if (request.getHeader("Referer").contains("CarServlet?id=")) {
			String temp = request.getHeader("Referer");
			for (int i = 0; i < temp.length(); i++) {
				if (temp.charAt(i) == '&') {
					response.sendRedirect(temp.substring(0, i));
					return;
				}
			}
		}
		response.sendRedirect(request.getHeader("Referer"));

	}

}
