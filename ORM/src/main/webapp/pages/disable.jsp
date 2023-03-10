<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cpi.model.Users"%>
<%@ page import="com.cpi.dao.DisplayUsers"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Disable/Edit Employees</title> 
</head>
<body>
	<h1>Employee Information</h1>
	
	<table>
		<tr>
			<th></th>
		</tr>
		<%
		List<Users> userList = DisplayUsers.getUsers();
		for (Users acct : userList) {
		%>
		<tr>
			<td><%= acct.getRoleId() %></td>
			<td><%= acct.getUsername() %></td>
			<td><%= acct.getEmail() %></td>
			<td><%= acct.getStatus() %></td>
		</tr>
		<% } %>
	</table>
</body>
</html>