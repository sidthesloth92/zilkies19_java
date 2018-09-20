package io.ztech.placementportal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.delegate.DeleteDetailsDelegate;

@WebServlet("/delete")
public class DeleteDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public DeleteDetailsController() {
		super();
		log = Logger.getLogger("DeleteDetailsController.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of DeleteDetailsController");
		String type = request.getParameter("type");
		DeleteDetailsDelegate delete = new DeleteDetailsDelegate();
		try {
			if (type.equals("student")) {
				if (delete.deleteStudent(request.getParameter("id"))) {
					response.getWriter().write("success");
				}
			}
			if (type.equals("company")) {
				System.out.println("hello");

				if (delete.deleteCompany(request.getParameter("id"))) {
					System.out.println("in delete controller");
					response.getWriter().write("success");

				}
			} else {
				if (type.equals("project")) {
					type = ApplicationConstants.PROJECT;
				} else if (type.equals("course")) {
					System.out.println("course");
					type = ApplicationConstants.CERTIFICATIONS;
				} else {
					type = ApplicationConstants.ACHIEVEMENT;
				}
				if (delete.deleteProfile(request.getParameter("id"), type)) {
					System.out.println("in delete controller");
					response.getWriter().write("success");

				}

			}
		} catch (SQLException e) {
			log.warning(e.toString());
		}
		log.info("Exiting post method of DeleteDetailsController");

	}

}
