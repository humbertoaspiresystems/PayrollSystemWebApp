package com.test.UserActions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.DatabaseUsage.ApplicationDao;
import com.test.UserInformation.User;

//We define the webservlet as /remove in the JSP folder
@WebServlet("/JSP/remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			// We use the request dispatcher to enter Menu screen
			int employeeId = 0;
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/Menu.jsp");
			employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String empty = "";
			User user = new User(empty, empty, employeeId, empty, 0, 0, empty, empty, empty);
			HttpSession session = request.getSession(false);
			// We call the database and use the method removeUser(User) to verify ID
			// existence
			ApplicationDao dao = new ApplicationDao();
			int change = dao.removeUser(user);
			// If ID exists, the employee is removed from database, otherwise, the ID is not
			// valid
			if (change == 0)
				session.setAttribute("error", "Employee ID is not valid,please try again.");
			else
				session.setAttribute("error", "Employee removed from database.");
			// We include the response attribute in the same web servlet
			requestDispatcher.include(request, response);
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
