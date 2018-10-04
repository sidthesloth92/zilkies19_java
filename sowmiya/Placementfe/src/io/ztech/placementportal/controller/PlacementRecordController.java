package io.ztech.placementportal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

@WebServlet("/placementRecord")
public class PlacementRecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public PlacementRecordController() {
		super();
		log = Logger.getLogger("PlacementRecordController.class");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("in doget() of PlacementRecordController()");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		try {
			ArrayList<PlacedDetail> placementDetail = retrieveDelegate.getPlacementDetails();
			request.setAttribute("placement", placementDetail);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/placementRecord.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting doget() of PlacementRecordController()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
