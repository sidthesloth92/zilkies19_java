package io.ztech.autorate.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.services.FetchDetailsService;

/**
 * Servlet implementation class AddCarServlet
 */
@WebServlet("/FetchAddCarServlet")
public class FetchAddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FetchDetailsService fetchdetailsService = new FetchDetailsService();
	public static final Logger logger = Logger.getLogger(FetchAddCarServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchAddCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Make> makes= null;
		try {
			makes = fetchdetailsService.displayMakes(new CarType());
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
		}
		request.setAttribute("makes", makes);
		
		ArrayList<CarType> carTypes= null;
		try {
			carTypes = fetchdetailsService.displayCarTypes(new Make());
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
		}
		request.setAttribute("carTypes", carTypes);
		
		request.getRequestDispatcher("/WEB-INF/pages/add-car.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
