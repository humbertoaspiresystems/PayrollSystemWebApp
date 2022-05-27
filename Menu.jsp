<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payroll System</title>
<link rel="stylesheet" href="../CSS/Design.css">
</head>
<body>
<header>
	<h1> Log In to Payroll System</h1>
</header>
<main> 
<div class="form_class">
<h1> Welcome <%= session.getAttribute("user") %> </h1>
<h2> <a style="text-decoration: none" href="RegisterEmployee.jsp">  Add employee </a> </h2>
<form action="remove" method="POST"> 
<h2>Remove employee</h2>
<input maxlength="10" type="number" name="employeeId" placeholder="Enter employee ID" required>
<% if(session.getAttribute("error")!=null){%>
	<h4> <%=session.getAttribute("error") %> </h4>
	<% session.removeAttribute("error");} %>
<input type="submit" value="Remove">
</form>
<form action="pay" method="POST"> 
<h2>Pay Employee</h2>
<input type="number" name="employeeId2" placeholder="Enter employee ID" required>
<% if(session.getAttribute("error2")!=null){%>
	<h4> <%=session.getAttribute("error2") %> </h4>
	<% session.removeAttribute("error2");} %>
<input type="submit" value="Pay">
</form>
<form action="register" method="GET"> 
<h2>Display all employees</h2>
<!-- List<String> users=(List<String>)session.getAttribute("list2");
int finishedRow=0;
 for(int counter=0; counter<users.size()/7; counter++){
 	for(int counter2=0+finishedRow; counter2<7; counter++){
  	 users.get(counter);}
  	 finishedRow+=7;}
  	 </h2> -->
<% if(session.getAttribute("error")!=null){%>
	<h4> <%=session.getAttribute("error") %> </h4>
	<% session.removeAttribute("error");} %>
<input type="submit" value="Display All">
</form>
<form action="queue" method="GET" > 
<h2>Display employee queue</h2>
<input type="submit" value="Queue">
</form>








<form action="logout" method="GET"> 
<input class="submit_class" type="submit" value="Log Out">
</form>
</div>
</main>
</body>
</html>