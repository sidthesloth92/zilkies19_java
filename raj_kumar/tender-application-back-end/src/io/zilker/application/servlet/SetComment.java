package io.zilker.application.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.delegate.ContractorDelegate;
import io.zilker.application.delegate.UserDelegate;
import io.zilker.application.logsession.UserSession;

/**
 * Servlet implementation class setComment
 */
@WebServlet("/SetComment")
public class SetComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final Logger LOGGER = Logger.getLogger(SetComment.class.getName());

	public SetComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entering doPost of setComment Servlet !");
		// TODO Auto-generated method stub
		UserDelegate userDelegate = new UserDelegate();
		if (request.getParameter("post-type").equals("add-comment")) {

			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			String projectId = request.getParameter("project_id");
			int pID = Integer.parseInt(projectId);
			try {

				userDelegate.addComment(userSession.getUserId(), pID, request.getParameter("comment-data"));
				ContractorDelegate contractorDelegate = new ContractorDelegate();
				// TODO Auto-generated method stub
				ArrayList<ApprovedProject> project = userDelegate.detailDisplay(pID);
				request.setAttribute("projectDetail", userDelegate.detailDisplay(pID));
				request.setAttribute("contractorName",
						contractorDelegate.getContractorName(project.get(0).getContrID()));
				request.setAttribute("responseData", contractorDelegate.getResponses(project.get(0).getProjectID()));
				request.setAttribute("commentList", userDelegate.getComments(project.get(0).getProjectID()));
				request.getRequestDispatcher("WEB-INF/pages/project-details.jsp?success=1").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.getRequestDispatcher("WEB-INF/pages/project-details.jsp?success=0").forward(request, response);
				e.printStackTrace();
			}
		}
		doGet(request, response);
		LOGGER.info("Leaving doPost of setComment Servlet !");
	}

}
