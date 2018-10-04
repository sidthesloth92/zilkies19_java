package io.ztech.autorate.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.delegates.AddRatingDelegate;

/**
 * Servlet implementation class EditRatingServlet
 */
@WebServlet("/EditRatingServlet")
public class EditRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AddRatingDelegate addRatingDelegate=new AddRatingDelegate();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRatingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Specification specification= new Specification();
		User user=new User();
		Rating rating = new Rating();
		rating.setRating(request.getParameter("rating"));
		rating.setReview(request.getParameter("review"));
		rating.setSubject(request.getParameter("subject"));
		if(request.getSession().getAttribute("status")!=null)
		user.setUsername(request.getSession().getAttribute("username").toString());
		specification.setCarId(Integer.parseInt(request.getParameter("car-id")));
		try {
			addRatingDelegate.editRating(specification, rating, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/Autorate/CarServlet?id="+request.getParameter("car-id"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
