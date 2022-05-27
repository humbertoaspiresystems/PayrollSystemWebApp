package com.test.UserLog;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.DatabaseUsage.ApplicationDao;

//We define the webservlet as /login in the JSP folder
@WebServlet("/JSP/login")

public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			// For the log in, we obtain the username and password parameters from the we
			// page and proceed to use the database for validation
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// We create the HTTP session and set the attribute to the user that logged in
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			// We call the method validateUser which searches for the username and password
			// in the database
			ApplicationDao dao = new ApplicationDao();
			boolean isValid = dao.validateUser(username, password);
			String user = dao.getUser(username, password);
			session.setAttribute("user", user);
			session.setAttribute("errorMessage", "Invalid username or password");
			// If the boolean isValid is true, it means there is an employee with that
			// username and password registered
			// if log in failes, the attribute errorMessage will display to the user telling
			// him its an invalid username or password
			if (!isValid) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Index.jsp");
				requestDispatcher.forward(request, response);
			} else {
				// If log in is successful, the response redirects to the Menu screen
				response.sendRedirect("Menu.jsp");
			}
		}
		// Exception handling
		catch (ServletException servletException) {
			servletException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Input exception problem, enter proper input.");
		}
	}

}
