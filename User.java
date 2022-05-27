package com.test.UserInformation;

//The class User will obtain all data for the employee and save it in different string using different methods and objects
public class User {
	// We declare Strings and Integers as stated in the database for easier handling
	private String firstName, lastName, salaryType, paymentStatus, username, password;
	private int employeeId, startYear, salary;

	// Public User will recieve all user data and set the Strings to the ones
	// obtained from the servlet
	public User(String firstName, String lastName, int employeeId, String salaryType, int startYear, int salary,
			String paymentStatus, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
		this.salaryType = salaryType;
		this.startYear = startYear;
		this.salary = salary;
		this.paymentStatus = paymentStatus;
		this.username = username;
		this.password = password;
	}

	// All of this strings and voids help us obtain each variable for the database,
	// starting with first name and ending with password
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(String salaryType) {
		this.salaryType = salaryType;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
