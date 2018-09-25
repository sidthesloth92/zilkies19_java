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
 * Servlet implementation class FetchCarTypesServlet
 */
@WebServlet("/FetchCarTypesServlet")
public class FetchCarTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FetchDetailsDelegate fetchdetailsDelegate = new FetchDetailsDelegate();
	public static final Logger logger = Logger.getLogger(FetchCarTypesServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchCarTypesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<CarType> carTypes = null;
		Make make = new Make();
		make.setMakeId(Integer.parseInt(request.getParameter("makeId")));
		try {
			carTypes = fetchdetailsDelegate.displayCarTypes(make);
		} catch (Exception e) {
			logger.info(AppConstants.ERROR_DATA);
		}

		String json = new Gson().toJson(carTypes);
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
