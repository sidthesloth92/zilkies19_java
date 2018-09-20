package io.zilker.application.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.application.beans.Project;
import io.zilker.application.delegate.AdminDelegate;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final Logger LOGGER = Logger.getLogger(Admin.class.getName());

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entering doGet of Admin !");
		AdminDelegate adminDelegate = new AdminDelegate();
		// TODO Auto-generated method stub
		if (request.getParameter("getType").equals("add-new-project")) {

			request.getRequestDispatcher("WEB-INF/pages/admin.jsp").forward(request, response);

		} else if (request.getParameter("getType").equals("display-requested-projects")) {

			request.setAttribute("requestedProjectList", adminDelegate.displayRequested());
			request.getRequestDispatcher("WEB-INF/pages/admin-requested.jsp").forward(request, response);

		} else if (request.getParameter("getType").equals("view-all-contractors")) {

			request.setAttribute("contractorsList", adminDelegate.displayAllContractors());
			request.getRequestDispatcher("WEB-INF/pages/view-contractors-admin.jsp").forward(request, response);

		} else if (request.getParameter("getType").equals("contractor-detail")) {

			String contractorID = request.getParameter("contractor-id");
			request.setAttribute("contractorDetail", adminDelegate.getContractor(Integer.parseInt(contractorID)));
			request.getRequestDispatcher("WEB-INF/pages/contractor-detail.jsp").forward(request, response);

		}
		LOGGER.info("Leaving doGet of Admin !");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entering doPost of Admin !");
		AdminDelegate adminDelegate = new AdminDelegate();
		// TODO Auto-generated method stub
		if (request.getParameter("post-type").equals("adding-new-project")) {

			Project project = new Project();
			project.setProjectName(request.getParameter("project-name"));
			project.setLocation(request.getParameter("project-location"));
			project.setDescription(request.getParameter("project-description"));
			try {

				adminDelegate.addNewProject(project);
				request.getRequestDispatcher("WEB-INF/pages/admin.jsp?success=1").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("post-type").equals("accept-tender-request")) {

			try {

				adminDelegate.approveProject(Integer.parseInt(request.getParameter("request-id")));
				request.getRequestDispatcher("WEB-INF/pages/admin.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		LOGGER.info("Leaving doPost of Admin !");
	}

}
