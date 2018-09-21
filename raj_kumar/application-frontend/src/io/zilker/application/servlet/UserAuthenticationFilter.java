package io.zilker.application.servlet;

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

/**
 * Servlet Filter implementation class UserAuthenticationFilter
 */
@WebFilter("/UserAuthenticationFilter")
public class UserAuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public UserAuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = ((HttpServletRequest) request).getRequestURI();
		System.out.println("---------------------------------");
		System.out.println(path.contains("/Admin"));
		System.out.println(path);
		System.out.println("---------------------------------");
		if ((path.contains("/AddResponse") || path.contains("/AddResponse") || path.contains("/Admin"))) {
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			HttpSession session = req.getSession(false);
//			UserSession userSessionU = (UserSession) session.getAttribute("userSession");
			System.out.println("---------------------------------");
			System.out.println("Inside User Authentication Filter");
			System.out.println("---------------------------------");
			if (session.getAttribute("isLoggedIn") == null) {
				System.out.println("Inside the if condition");
				RequestDispatcher rd = req.getRequestDispatcher("./index.jsp");
				rd.forward(req, res);
			} else {
				System.out.println("inside the else statement");
				System.out.println(session.getAttribute("isLoggedIn"));
				chain.doFilter(request, res);
			}

		}
		// pass the request along the filter chain
		System.out.println("Outside Filter");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
