package com.ztech.io.fitnessfirstprototype.servlets;

import java.io.IOException;

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

@WebFilter("/UserFilter")
public class UserFilter implements Filter {

	// private ArrayList<String> urlPassList;

	public UserFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		String path = request.getRequestURI();
		System.out.println(path + " through filter");

		if (path.endsWith(".css") || path.endsWith(".js") || path.contains("/assets/")) {
			System.out.println("path passed");
			chain.doFilter(request, response);
			return;
		}

		if (path.contains("/homepage.jsp") || path.contains("/LoginServlet") || path.contains("/index.html")
				|| path.contains("/signuppage.html") || path.endsWith("/FitnessFirstPrototype/")
				|| path.endsWith("/UsernameCheckServlet") || path.endsWith("/EmailCheckServlet")
				|| path.endsWith("/SignupPageServlet") || path.endsWith("/bmicalculator.html")
				|| path.endsWith("/bmrcalculator.html") || path.endsWith("/caloriecalculator.html")
				|| path.endsWith("/WeightTrackerServlet") || path.endsWith("/CalorieCalculatorServlet")) {
			System.out.println("path passed");
			chain.doFilter(request, response);
		} else {
			// deleting cache on log out
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setDateHeader("Expires", 0); // Proxies.
			if (session == null || session.getAttribute("userName") == null) {
				System.out.println("no session - forwarded to homepage");
				RequestDispatcher rd = req.getRequestDispatcher("/pages/homepage.jsp");
				rd.forward(req, res);
				return;
			} else {
				chain.doFilter(request, response);
			}
		}

//
//		if (path.contains("/customisationpage.html")
//				|| path.contains("/userdashboard.jsp")) {
//			System.out.println(path+" contained");
//			if (null == session || null == session.getAttribute("userName")) {
//				System.out.println("if");
//				
//			} else {
//				System.out.println("else");
//				chain.doFilter(request, response);
//			}
//		} else {
//			System.out.println("else outer");
//			chain.doFilter(request, response);
//		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		// urlPassList.add("/pages/bmicalculator.html");
	}

}

// !excludeURLs.contains(httpReq.getServletPath()) 
