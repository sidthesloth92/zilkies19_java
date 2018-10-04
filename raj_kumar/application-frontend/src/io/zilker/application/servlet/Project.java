package io.zilker.application.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.delegate.ContractorDelegate;
import io.zilker.application.delegate.UserDelegate;

/**
 * Servlet implementation class Project
 */
@WebServlet("/Project")
public class Project extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContractorDelegate contractorDelegate = new ContractorDelegate();
		// TODO Auto-generated method stub
		UserDelegate userDelegate = new UserDelegate();
		request.getParameter("projectId");
		ArrayList<ApprovedProject> project = userDelegate
				.detailDisplay(Integer.parseInt(request.getParameter("projectId")));
		request.setAttribute("projectDetail",
				userDelegate.detailDisplay(Integer.parseInt(request.getParameter("projectId"))));
		request.setAttribute("contractorName", contractorDelegate.getContractorName(project.get(0).getContrID()));
		request.setAttribute("responseData", contractorDelegate.getResponses(project.get(0).getProjectID()));
		request.setAttribute("commentList", userDelegate.getComments(project.get(0).getProjectID()));
		request.getRequestDispatcher("WEB-INF/pages/project-details.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
