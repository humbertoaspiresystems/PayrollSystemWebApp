package com.test.UserLog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.DatabaseUsage.ApplicationDao;

//We define the webservlet as /queue in the JSP folder
@WebServlet("/JSP/queue")
public class Queue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// We get the printwriter and create a new instance of the database for
			// obtaining employee information and adding it to the list
			PrintWriter printWriter = response.getWriter();
			ApplicationDao dao = new ApplicationDao();
			// We call the method displayQueue() to retrieve the employee information
			List<String> list = dao.displayQueue();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/Menu.jsp");
			requestDispatcher.include(request, response);
			// We request the HTTP session and set the attribute list to the list obtained
			// from the database
			HttpSession session = request.getSession(false);
			session.setAttribute("list", list);
			// For empty list, we display that payment is done, otherwise, we print out the
			// queue on the same servlet using HTML
			if (list.isEmpty())
				printWriter.println("All employee payment is done.");
			else {
				printWriter.println("<html><body><table border=1 width=20% height=15%>");
				printWriter.println("<tr><th>Employee Queue</th></tr>");
				for (int listCounter = 0; listCounter < list.size(); listCounter++)
					printWriter.println("<tr><th>" + list.get(listCounter) + "</th></tr>");
				printWriter.println("</table></body></html>");
			}
		} // Exception handling
		catch (ServletException servletException) {
			servletException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Input exception problem, enter proper input.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
