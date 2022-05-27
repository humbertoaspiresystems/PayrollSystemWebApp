package com.test.DatabaseUsage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//class for getting database connection from MySQL
public class DBConnection {

	// Declaring variables for database connection parameters
	protected static Connection connectionToDatabase = null;
	private static String user = "root";
	private static String password = "aspire@123";
	private static String url = "jdbc:mysql://localhost:3306/Employees";

	// static constructor for accessing connection to database
	public static Connection getConnectionToDatabase() {
		try {
			// Class name for obtaining driver
			Class.forName("com.mysql.jdbc.Driver");
			// Use of DriverManager for getting connection from database with parameters
			connectionToDatabase = DriverManager.getConnection(url, user, password);
		}
		// Exception handling for Class not found and SQL connection
		catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
			System.out.println("Class Not Found Exception, try obtaining class.");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL Exception, try connection again.");

		}
		// Return the connection to database
		return connectionToDatabase;

	}

	// Void for closing connection to database
	public static Connection closeDatabase() {
		try {
			connectionToDatabase.close();
		}
		// Handling SQL exception
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("SQL exception, try connection again.");
		}
		// Message for user disconnected from database
		System.out.println("Disconnected from database.");
		return null;
	};

}
