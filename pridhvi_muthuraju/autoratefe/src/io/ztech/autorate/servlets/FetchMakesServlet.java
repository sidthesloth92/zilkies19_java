package io.ztech.autorate.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.delegates.FetchDetailsDelegate;

/**
 * Servlet implementation class FetchMakesServlet
 */
@WebServlet("/FetchMakesServlet")
public class FetchMakesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FetchDetailsDelegate fetchdetailsDelegate = new FetchDetailsDelegate();
	public static final Logger logger = Logger.getLogger(FetchMakesServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchMakesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Make> makes = null;
		CarType carType = new CarType();
		carType.setCarTypeId(Integer.parseInt(request.getParameter("carTypeId")));
		try {
			makes = fetchdetailsDelegate.displayMakes(carType);
		} catch (Exception e) {
			logger.info(AppConstants.ERROR_DATA);
		}

		String json = new Gson().toJson(makes);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
