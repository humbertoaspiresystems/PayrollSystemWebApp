package com.test.UserLog;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.DatabaseUsage.ApplicationDao;
import com.test.DatabaseUsage.DBConnection;
import com.test.UserInformation.User;

//We define the webservlet as /register in the JSP folder
@WebServlet("/JSP/register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// We call the printwriter for the use of HTML coding on the register screen for
			// the user
			PrintWriter printWriter = response.getWriter();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Menu.jsp");
			requestDispatcher.include(request, response);
			response.setContentType("text/html");
			printWriter.println("<html><head> <link rel=\"stylesheet\" href=\"../CSS/Design.css\"> </head> <body>");
			// We establish a connection with the database to select all data from the table
			// Employees
			Connection connection = DBConnection.getConnectionToDatabase();
			String displayEmployees = "SELECT * FROM Employees";
			PreparedStatement statement = connection.prepareStatement(displayEmployees);
			ResultSet resultSet = statement.executeQuery();
			printWriter.println("<table border=1 width=50% height=15%>");
			printWriter.println("<tr><th>First Name</th><th>Last Name</th><th>Employee ID</th>");
			printWriter.println("<th>Salary Type</th><th>Start Year</th><th>Salary</th>");
			printWriter.println("<th>Payment Status</th></tr>");
			// While the resultSet has values, we will obtain every string and integer
			// declared on the database and set it to the user
			// afterwards, we print out a table with the employees information
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				int employeeId = resultSet.getInt("employeeId");
				String salaryType = resultSet.getString("salaryType");
				int startYear = resultSet.getInt("startYear");
				int salary = resultSet.getInt("salary");
				String paymentStatus = resultSet.getString("paymentStatus");
				printWriter.println("<tr><td>" + firstName + "</td><td>" + lastName + "</td><td>" + employeeId
						+ "</td><td>" + salaryType + "</td><td>" + startYear + "</td><td>" + salary + "</td><td>"
						+ paymentStatus + "</td></tr>");
			}
			// We close the HTML coding there is no more data from the table
			printWriter.println("</table></body></html>");

		}
		// Exception handling
		catch (ServletException servletException) {
			servletException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Input exception problem, enter proper input.");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception, try connection again.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			// We request the session and obtain all the values from the inputs in the web
			// page
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("RegisterEmployee.jsp");
			HttpSession session = request.getSession(false);
			// We get the parameters from all the fields
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String salaryType = request.getParameter("salaryType");
			int startYear = Integer.parseInt(request.getParameter("startYear"));
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int salary = 0;
			String paymentStatus = "Unpaid";
			// Depending on the input for salary type, the salary is calculated in regards
			// of starting year and half or full time employee
			if (salaryType.equals("Half Time")) {
				salary = 9000 + 450 * (2022 - startYear);
			} else if (salaryType.equals("Full Time")) {
				salary = 18000 + 900 * (2022 - startYear);
			} else {
				// If the input is different than half or full time, we will set the attribute
				// error to be displayed and set the salary type to null
				session.setAttribute("error", "Invalid salary type");
				salaryType = "";
			}
			if ((2022 - startYear) > 45) {
				session.setAttribute("error", "Start date to far away");
			}
			// We check for invalid inputs, we will set an error in case there are empty
			// fields
			if (firstName == "" || lastName == "" || salaryType == "" || employeeId == 0 || username == ""
					|| password == "") {
				session.setAttribute("error", "Empty field value, please fill in all fields.");
			} else {
				// If there are no errors, we proceed to call the database for
				// validateNewRegister method which will
				// check if there is an employee with that name or ID, if there is, we will set
				// a meesage that the employee or ID are duplicate
				ApplicationDao dao = new ApplicationDao();
				boolean validateNewEmployee = dao.validateNewRegister(firstName, lastName, employeeId);
				if (validateNewEmployee == false) {
					session.setAttribute("error", "Username or employee ID duplicate.");
				} else {
					// Once we obtain the values we set the User with this parameters
					User user = new User(firstName, lastName, employeeId, salaryType, startYear, salary, paymentStatus,
							username, password);
					// In case there are no duplicates, we call the registerUser method for adding
					// the employee to the database
					dao.registerUser(user);
					// Now we set the attribute for displaying the employee has been added
					session.setAttribute("error", "Employee added to database.");
				}
				requestDispatcher.include(request, response);
			}
		}

		// Exception handling
		catch (ServletException servletException) {
			servletException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Input exception problem, enter proper input.");
		} catch (NumberFormatException numberFormatException) {
			numberFormatException.printStackTrace();
			System.out.println("Number format exception, enter proper number format.");
		}
	}
}
