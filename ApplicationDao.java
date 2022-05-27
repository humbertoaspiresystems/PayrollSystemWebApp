package com.test.DatabaseUsage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.UserInformation.User;

import java.sql.PreparedStatement;

//Public class for retrieving and writing information to the database from DBConnection
public class ApplicationDao {
	// Variables for methods to reuse for connection, queries and users
	Connection connection = null;
	String query = null;
	String user = null;
	// Create prepared statement and result set for methods
	PreparedStatement statement;
	ResultSet resultSet;

	// getUser method obtains the username and password from servlet and verifies if
	// user exists in database for log in
	public String getUser(String username, String password) {
		// Get the connection to database and obtain firstname for database if username
		// and password match
		connection = DBConnection.getConnectionToDatabase();
		query = "SELECT firstName FROM Employees WHERE username='" + username + "' AND password='" + password + "'";
		user = null;
		try {
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			// If result set contains firstname, we get the string for displaying the user
			if (resultSet.next()) {
				user = resultSet.getString("firstName");
			}
		}
		// Exception handling

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");

		}
		// We return the firstname in the user string
		return user;
	}

	// Validation for new employee, we check if first and last name or ID are in
	// database, if there is no one with the name or ID we add him to
	// the database
	public boolean validateNewRegister(String firstName, String lastName, int employeeId) {
		connection = DBConnection.getConnectionToDatabase();
		query = "SELECT * FROM Employees WHERE (firstName='" + firstName + "' AND lastName='" + lastName
				+ "') OR employeeId=" + employeeId;
		// We create a boolean for obtaining validation from the database
		boolean isValidUser = true;
		try {
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				isValidUser = false;
			}
		}
		// Exception handling

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");

		}
		// We return true in case someone exists with that name or ID, false if there is
		// no one with that name or ID
		return isValidUser;
	}

	// Validate user method for signing in to servlets, we obtain the username and
	// password and see if it matches with information
	// from the database
	public boolean validateUser(String username, String password) {
		connection = DBConnection.getConnectionToDatabase();
		query = "SELECT * FROM Employees WHERE username='" + username + "' AND password='" + password + "'";
		boolean isValidUser = false;
		try {
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				isValidUser = true;
			}
		}
		// Exception handling

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");

		}
		// For true, we have found that the user exists in the database, otherwise it
		// doesnt exists and will not be able to sign in
		return isValidUser;
	}

	// registerUser obtains all data from User within the servlet and proceeds to
	// add the user info in the database
	public int registerUser(User user) {
		// The int rowsAffected will help us know if there were changes present in the
		// database once the query is executed
		// if it is equal to 1, it means we added the user to the database
		int rowsAffected = 0;
		try {
			connection = DBConnection.getConnectionToDatabase();
			query = "INSERT IGNORE INTO Employees VALUES('" + user.getFirstName() + "','" + user.getLastName() + "',"
					+ user.getEmployeeId() + ",'" + user.getSalaryType() + "'," + user.getStartYear() + ","
					+ user.getSalary() + ",'" + user.getPaymentStatus() + "','" + user.getUsername() + "','"
					+ user.getPassword() + "')";

			statement = connection.prepareStatement(query);
			rowsAffected = statement.executeUpdate();
		}
		// Exception handling

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");

		}
		return rowsAffected;
	}

	// removeUser get the user info en checks the employee ID within the database to
	// see if the employee exists
	// if it doesnt exist, rows will be equal to 0, if it does exist, it will be
	// deleted and the rowaffected will be equal to 1
	public int removeUser(User user) {
		int rowsAffected = 0;
		try {
			connection = DBConnection.getConnectionToDatabase();
			query = "DELETE FROM Employees WHERE employeeId=" + user.getEmployeeId();
			statement = connection.prepareStatement(query);
			rowsAffected = statement.executeUpdate();
		}
		// Exception handling

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");

		}
		return rowsAffected;
	}

	// this method allows us to pay the employee once we obtain his ID, if there is
	// no ID associated in the database
	// there will be no changes, otherwise rowsAffected will be equal to 1 and the
	// paymentStatus will be paid
	public int payUser(User user) {
		int rowsAffected = 0;
		try {
			connection = DBConnection.getConnectionToDatabase();
			query = "UPDATE Employees SET paymentStatus='Paid' WHERE employeeId=" + user.getEmployeeId();
			statement = connection.prepareStatement(query);
			rowsAffected = statement.executeUpdate();
		}
		// Exception handling

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");

		}
		return rowsAffected;
	}

	// this List allows use to obtain the queue for employee payment, we check the
	// database for employees with paymentStatus equal to
	// Unpaid, once we get them we order them by starting year in ascending order,
	// if there are no employees the list will be empty
	public List<String> displayQueue() {
		List<String> list = new ArrayList<>();
		try {
			connection = DBConnection.getConnectionToDatabase();
			query = "SELECT firstName,lastName FROM Employees WHERE paymentStatus='Unpaid' ORDER BY startYear ASC";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				for (int databaseRowCounter = 1; databaseRowCounter < 2; databaseRowCounter++)
					list.add(resultSet.getString(databaseRowCounter) + " "
							+ resultSet.getString(databaseRowCounter + 1));
			}
		}
		// Exception handling

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");
		}
		// We return the list for the employees whether it is empty or not
		return list;
	}

	// we create a method for obtaining the employees from the database in a list,
	// once we enter the database we obtain all employee
	// information except for username and password as they are private
	public List<String> employees() {
		List<String> list = new ArrayList<>();
		try {
			connection = DBConnection.getConnectionToDatabase();
			query = "SELECT * FROM Employees ";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				for (int databaseRowCounter = 1; databaseRowCounter < 8; databaseRowCounter++)
					list.add(resultSet.getString(databaseRowCounter));
			}
		}
		// Exception handling

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");
		}
		// We return the list with the employees from the database
		return list;
	}

	// the resetPassword metod allows us to obtain the username for the employee and
	// change the password to a new one, once we execute the
	// query, the rows will tell us if the query was executed or not, for rows equal
	// to 0 means there is no employee with that username
	public int resetPassword(User user) {
		int rowsAffected = 0;
		try {
			connection = DBConnection.getConnectionToDatabase();
			query = "UPDATE Employees SET password='" + user.getPassword() + "' WHERE username='" + user.getUsername()
					+ "'";
			statement = connection.prepareStatement(query);
			rowsAffected = statement.executeUpdate();
		}
		// Exception handling

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception problem, try to connect again.");
		}
		return rowsAffected;
	}
}
