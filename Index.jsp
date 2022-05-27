<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payroll System</title>
<link rel="stylesheet" href="../CSS/Design.css">
</head>
<body>
<h3> <a  href="Index.jsp">  Sign In </a></h3>
<h3> <a href="../Home.html">  Home </a></h3>
<br>
<br>
<header>
	<h1> Log In to Payroll System</h1>
</header>
<main> 
<form action="login" method="POST" class="form_class">
 <div class="form_div">
<label>Username</label>
<input class="field_class" name="username" type="text" placeholder="Enter username" autofocus required>
<label>Password</label>
<input class="field_class" name="password" type="password" placeholder="Enter password" required>
<% if(session.getAttribute("errorMessage")!=null){%>
	<h4> <%=session.getAttribute("errorMessage") %> </h4>
	<% session.removeAttribute("errorMessage");} %>
<input class="submit_class" type="submit" value="Log In">
</div>
<div class="info_div">
<p><a href="../JSP/ResetPassword.jsp">Forgot password?</a></p>
</div>
</form>
</main>
</body>
</html>