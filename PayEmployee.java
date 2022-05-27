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

//We define the webservlet as /pay in the JSP folder
@WebServlet("/JSP/pay")
public class PayEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			// Use of request dispatcher for entering the menu screen
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/Menu.jsp");
			int employeeId = 0;
			// We obtain the employee ID from input text box web page
			employeeId = Integer.parseInt(request.getParameter("employeeId2"));
			String empty = "";
			User user = new User(empty, empty, employeeId, empty, 0, 0, empty, empty, empty);
			// We call the database and use the method payUser(user) to validate existence
			ApplicationDao dao = new ApplicationDao();
			int verify = dao.payUser(user);
			HttpSession session = request.getSession(false);
			// If the employee exists, request dispatcher will tell us the employee has been
			// paid,otherwise,there is no ID associated in the database
			if (verify == 0)
				session.setAttribute("error2", "There is no employee with that ID, please try again.");
			else
				session.setAttribute("error2", "Employee has been paid.");
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
