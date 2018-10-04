package com.zilker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.zilker.beans.*;
import com.zilker.service.ServiceCrudOperations;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewRequest
 */
@WebServlet("/ViewRequest")
public class ViewRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		request.getRequestDispatcher("/WEB-INF/pages/ApproveRequest.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		ServiceCrudOperations tournament=new ServiceCrudOperations();
		try {
			ArrayList tournamentlist=tournament.getTournament();
			for(int i=0;i<tournamentlist.size();i+=4) {
				Double d=(double)tournamentlist.get(i+3);
				int t=d.intValue();
				String mobile=tournament.getMobile(t);
				tournamentlist.set((i+3), mobile);
			}
			String json = new Gson().toJson(tournamentlist);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			
		}
	}

}
