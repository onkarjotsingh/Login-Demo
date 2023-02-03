<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expire","0");	
	
		List l=(List) request.getAttribute("error");
		if(l!=null){
			for(Iterator it=l.iterator();it.hasNext();){
				String error=(String) it.next();
				%>
	<font color="red">
		<li><%=error %></li>
	</font>
	<% 
			}
		}
	
	%>
	<form action="LoginServlet" method="post">
		Enter UserName:<input type="text" name="UserName"><br>
		<br> Enter Password:<input type="password" name="Password"><br>
		<br> <input type="submit" name="button" value="Login"> <input
			type="submit" name="button" value="SignUp">
	</form>

</body>
</html>