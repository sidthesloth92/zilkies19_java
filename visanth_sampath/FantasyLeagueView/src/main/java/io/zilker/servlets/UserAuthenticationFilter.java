package io.zilker.servlets;

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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = ((HttpServletRequest) request).getRequestURI();
		//System.out.println(path);
		if (path.contains("/PickTeamServlet") || path.contains("/ViewTeamServlet")
				|| path.contains("/ModifyTeamServlet") || path.contains("/AddMatch")) {
			System.out.println(path);
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			System.out.println(session == null);
			if (session.getAttribute("isLoggedIn")==null || session == null) {
				RequestDispatcher rd = req.getRequestDispatcher("./index.jsp");
				rd.forward(req, res);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
