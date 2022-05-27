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
	<h1> Reset password </h1>
</header>
<main> 
<form action="resetPassword" method="POST" class="form_class">
 <div class="form_div">
<label>Username</label>
<input class="field_class" name="username" type="text" placeholder="Enter username">
<label>New Password</label>
<input class="field_class" name="password" type="text" placeholder="Enter new password">
<input class="submit_class" type="submit" value="Reset Password">
<a style="text-decoration: none" href="../JSP/Index.jsp"> <input  class="submit_class" type="button" value="Back"> </a>
</div>
</form>
</main>
</body>
</html>