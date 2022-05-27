package com.test.UserActions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.DatabaseUsage.ApplicationDao;
import com.test.UserInformation.User;

//We define the webservlet as /resetPassword in the JSP folder
@WebServlet("/JSP/resetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			// The request dispatcher will send us to the reset password servlet file
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ResetPassword.jsp");
			// We call the printwriter for displaying data
			PrintWriter printWriter = response.getWriter();
			String empty = "";
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new User(empty, empty, 0, empty, 0, 0, empty, username, password);
			// We call the database for the method resetPassword(User)
			ApplicationDao dao = new ApplicationDao();
			int reset = dao.resetPassword(user);
			requestDispatcher.include(request, response);
			// If there is an employee that matches the username, we set a new password
			// obtained from the input field from the webpage
			// otherwise, the password will not be valid because the user does not exists
			if (reset == 0)
				printWriter.print("New password not valid, please try again.");
			else
				printWriter.print("New password has been saved.");
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
