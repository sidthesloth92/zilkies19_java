package io.ztech.placementportal.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/Authentication")
public class Authentication implements Filter {
	public Authentication() {
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ArrayList<String> pathList = new ArrayList<>();
		pathList.add("/PlacementPortal/student");
		pathList.add("/PlacementPortal/studentProfile");
		pathList.add("/PlacementPortal/job");
		pathList.add("/PlacementPortal/company");
		pathList.add("/PlacementPortal/admin");
		pathList.add("/PlacementPortal/list");

		String path = ((HttpServletRequest) request).getRequestURI();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		if (pathList.contains(path)) {
			if (session.getAttribute("student_id") == null || session.getAttribute("username") == null) {
				RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
