package io.zilker.application.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.delegate.ContractorDelegate;
import io.zilker.application.logsession.UserSession;

/**
 * Servlet implementation class addResponse
 */
@WebServlet("/addResponse")
public class AddResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final Logger logger = Logger.getLogger(AddResponse.class.getName());

	public AddResponse() {
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

		logger.info("entering addResponse Servlet");
		// TODO Auto-generated method stub
		ContractorDelegate contractorDelegate = new ContractorDelegate();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;

		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}

		String data = buffer.toString();
		System.out.println("Data:" + data);
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");

		try {

			JSONObject responseObject = new org.json.JSONObject(data);
			String responseData = (String) responseObject.get("responseData");
			String projectId = (String) (responseObject.get("projectId"));
			int projectIdInteger = Integer.parseInt(projectId);
			contractorDelegate.addResponseService(projectIdInteger, userSession.getUserId(), responseData);

		} catch (Exception e) {
			logger.info(DisplayConstants.SQL_ERR);
		}

		response.setContentType("application/json");
		response.getWriter().write("{issue: true}");
		logger.info("leaving addResponse Servlet");
		// doGet(request, response);

	}

}
