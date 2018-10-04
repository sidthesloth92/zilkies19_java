package io.zilker.application.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.application.delegate.UserDelegate;

/**
 * Servlet implementation class getDataServlet
 */
@WebServlet("/getDataServlet")
public class GetDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final Logger LOGGER = Logger.getLogger(GetDataServlet.class.getName());

	public GetDataServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOGGER.info("Entering doPost of getDataServlet");
		UserDelegate userDelegate = new UserDelegate();
		response.setContentType("text/html");
		PrintWriter output = response.getWriter();
		// TODO Auto-generated method stub
		if (userDelegate.isUserPresent(request.getParameter("uName"))) {
			output.append("Username is already taken !");
		} else {
			output.append("");
		}
		LOGGER.info("Leaving doPost of getDataServlet");
	}

}
