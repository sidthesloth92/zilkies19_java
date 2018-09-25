package io.ztech.autorate.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.delegates.EditCarDelegate;

/**
 * Servlet implementation class EditCarServlet
 */
@WebServlet("/EditCarServlet")
public class EditCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EditCarDelegate editCarDelegate= new EditCarDelegate();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Specification specification = new Specification();
		specification.setCarId(Integer.parseInt(request.getParameter("car-id")));
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
		
		
		try {
			editCarDelegate.editCar(specification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/FetchEditCarServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
