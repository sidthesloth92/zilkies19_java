package io.zilker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.delegates.UserDelegate;

/**
 * Servlet implementation class GetChatServlet
 */
@WebServlet("/GetChatServlet")
public class GetChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<Message> messages = new ArrayList<Message> ();
		messages = new UserDelegate().displayMessages();
		for(int index = 0; index < messages.size() ; index++ ) {
			out.println("<div class='right-user'>");
			out.println("<div class='name'>");
			out.println(messages.get(index).getUserName());
			out.println("</div>");
			out.println("<div class='message'>");
			out.println("<p>");
			out.println(messages.get(index).getMessage());
			out.println("</p><br/>");
			out.println("<div class='time'>");
			out.println(messages.get(index).getInsertedTime().substring(10,16));
			out.println(" </div></div>");
			out.println("</div>");
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
