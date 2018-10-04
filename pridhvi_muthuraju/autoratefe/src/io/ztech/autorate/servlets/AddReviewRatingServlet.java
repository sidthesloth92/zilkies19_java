package io.ztech.autorate.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.delegates.AddRatingDelegate;

/**
 * Servlet implementation class AddReviewRatingServlet
 */
@WebServlet("/AddReviewRatingServlet")
public class AddReviewRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final Logger logger = Logger.getLogger(AddReviewRatingServlet.class.getName());
	AddRatingDelegate addRatingDelegate= new AddRatingDelegate();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReviewRatingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Specification specification=new Specification();
		Rating rating= new Rating();
		User user= new User();
		specification.setCarId(Integer.parseInt(request.getParameter("car-id")));
		user.setUsername(request.getParameter("username"));
		rating.setRating(request.getParameter("rating"));
		rating.setReview(request.getParameter("user-review"));
		rating.setSubject(request.getParameter("subject"));
		try {
			addRatingDelegate.addRating(specification, rating, user);
		} catch (Exception e) {
			logger.info(AppConstants.ERROR_DATA);
		}
		request.getRequestDispatcher("/CarServlet?id="+request.getParameter("car-id")).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
