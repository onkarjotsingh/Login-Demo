<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="jakarta.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expire","0");
		
		if(session.getAttribute("UserName")==null){
			response.sendRedirect("Login.jsp");
		}
%>
	<H3>Login Successful Welcome....</H3>
	
	<form action="LogoutServlet" method="post">
	<input type="submit" name="button" value="logout">
	</form>
</body>
</html>