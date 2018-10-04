package io.ztech.autorate.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.delegates.AddCarDelegate;
import io.ztech.autorate.delegates.RequestCarDelegate;

/**
 * Servlet implementation class AddCarServlet
 */
@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestCarDelegate requestCarDelegate = new RequestCarDelegate();
	AddCarDelegate addCarDelegate = new AddCarDelegate();
	public static final Logger logger = Logger.getLogger(AddCarServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CarType carType = new CarType();
		if (request.getParameter("cartype-name") != null && request.getParameter("car-types").equals("nil")) {
			carType.setCarTypeName(request.getParameter("cartype-name"));
		} else
			carType.setCarTypeId(Integer.parseInt(request.getParameter("car-types")));

		Make make = new Make();
		if (request.getParameter("make-name") != null && request.getParameter("makes").equals("nil")) {
			make.setMakeName(request.getParameter("make-name"));
		} else
			make.setMakeId(Integer.parseInt(request.getParameter("makes")));

		Specification specification = new Specification();
		specification.setCarName(request.getParameter("car-name"));
		specification.setAbs(request.getParameter("abs"));
		specification.setAirbag(request.getParameter("airbag"));
		specification.setCylinder(Integer.parseInt(request.getParameter("cylinder")));
		specification.setDisplacement(Integer.parseInt(request.getParameter("displacement")));
		specification.setDrivetrain(request.getParameter("drivetrain"));
		specification.setEngineType(request.getParameter("engine-type"));
		specification.setFuelCapacity(Integer.parseInt(request.getParameter("fuel-capacity")));
		specification.setKerbWeight(Integer.parseInt(request.getParameter("kerb-weight")));
		specification.setPower(Integer.parseInt(request.getParameter("power")));
		specification.setPrice(Integer.parseInt(request.getParameter("price")));
		specification.setTorque(Integer.parseInt(request.getParameter("torque")));
		specification.setTransmission(Integer.parseInt(request.getParameter("transmission")));
		specification.setWheelbase(Integer.parseInt(request.getParameter("wheelbase")));

		Request requestBean = new Request();
		User user = new User();
		if (request.getSession().getAttribute("status") != null) {
			user.setUsername(request.getSession().getAttribute("username").toString());

			if (request.getSession().getAttribute("status").equals("ADMIN")) {
				specification.setCarStatus(AppConstants.APPROVED);
			} else if (request.getSession().getAttribute("status").equals("USER")) {
				specification.setCarStatus(AppConstants.UNAPPROVED);
				requestBean.setRequestId(1);
			}

			try {
				specification.setCarId(addCarDelegate.addCar(carType, make, specification));
				if (request.getSession().getAttribute("status").equals("USER"))
					request.setAttribute("request-id",
							requestCarDelegate.addCarUserRequest(user, specification));
			} catch (Exception e) {
				logger.info(AppConstants.ERROR_DATA);
				e.printStackTrace();
			}

		}
		request.getRequestDispatcher("/FetchAddCarServlet").forward(request, response);
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
