<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Page</title>
</head>
<body>
	<%
	User user = (User)session.getAttribute("session_user");
	%>
	
	<h1>Welcome</h1>
	<h5> UserName: <%= user.getUsername()%> </h5>
	<h5> Password: <%= user.getPassword()%> </h5>
	
	<a href = "Logout"> Logout </a>
</body>
</html>