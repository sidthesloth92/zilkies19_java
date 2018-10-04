package io.ztech.autorate.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.services.DeleteCarService;

/**
 * Servlet implementation class DeclineRequestServlet
 */
@WebServlet("/DeclineRequestServlet")
public class DeclineRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DeleteCarService deleteCarService = new DeleteCarService();
	public static final Logger logger = Logger.getLogger(DeclineRequestServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeclineRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Specification specification=new Specification();
		specification.setCarId(Integer.parseInt(request.getParameter("carId")));
		try {
			deleteCarService.deleteCar(specification);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
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
