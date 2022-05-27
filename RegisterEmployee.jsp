<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../CSS/Design.css">
<title>Aspire Payroll System</title>
</head>
<body>
<header>
	<h1> Add new employee to database</h1>
</header>
<main>
<div class="form_class">
<h1> Welcome <%= session.getAttribute("user") %> </h1>
 <form action="register" method="POST"> 
<h2> Enter employee first name: <input type="text" name="firstName"></h2>
<h2>Enter employee last name: <input type="text" name="lastName"></h2>
<h2>Enter employee id: <input type="number" name="employeeId"></h2>
<h2>Enter employee salary type: <input type="text" name="salaryType"></h2>
<h2>Enter employee start year:<input type="text" name="startYear"></h2>
<h2>Enter employee username: <input type="text" name="username"></h2>
<h2>Enter employee password: <input type="text" name="password"></h2>
<% if(session.getAttribute("error")!=null){%>
	<h4> <%=session.getAttribute("error") %> </h4>
	<% session.removeAttribute("error");} %>
<input class="submit_class" type="submit" value="Add Employee">
</form>
<a href="../JSP/Menu.jsp" style="text-decoration: none">  <input class="submit_class" type="submit" value="Back"> </a>
</div>
</main>
</body>
</html>