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

import com.google.gson.Gson;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.services.FetchDetailsService;

/**
 * Servlet implementation class FetchCars
 */
@WebServlet("/FetchCarsServlet")
public class FetchCarsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FetchDetailsService fetchdetailsService = new FetchDetailsService();
	public static final Logger logger = Logger.getLogger(FetchCarTypesServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchCarsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Make make=new Make();
		CarType carType=new CarType();
		make.setMakeId(Integer.parseInt(request.getParameter("makeId")));
		carType.setCarTypeId(Integer.parseInt(request.getParameter("carTypeId")));
		ArrayList<Specification> cars=new ArrayList<>();
		try {
			cars = fetchdetailsService.getCars(make, carType);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
		}
		
		String json= new Gson().toJson(cars);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
