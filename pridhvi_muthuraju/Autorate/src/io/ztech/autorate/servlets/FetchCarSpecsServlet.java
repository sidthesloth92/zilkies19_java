package io.ztech.autorate.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.services.FetchDetailsService;

/**
 * Servlet implementation class FetchCarSpecsServlet
 */
@WebServlet("/FetchCarSpecsServlet")
public class FetchCarSpecsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FetchDetailsService fetchdetailsService = new FetchDetailsService();
	public static final Logger logger = Logger.getLogger(FetchCarSpecsServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchCarSpecsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Request requestBean=new Request();
		requestBean.setCarId(Integer.parseInt(request.getParameter("id")));
		Specification carBean=null;
		try {
			carBean = fetchdetailsService.getCar(requestBean);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
		}

		String json= new Gson().toJson(carBean);
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
