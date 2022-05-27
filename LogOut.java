package com.test.UserLog;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.DatabaseUsage.DBConnection;

//We define the webservlet as /logout in the JSP folder
@WebServlet("/JSP/logout")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// We get the HTTP session and then invalidate it for logging out
			HttpSession session = request.getSession();
			session.invalidate();
			// We then proceed to close the database connection
			Connection connection = DBConnection.closeDatabase();
			// Now request dispatcher will forward us to the Log In menu
			request.getRequestDispatcher("Index.jsp").forward(request, response);
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
