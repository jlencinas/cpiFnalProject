<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<head>
<meta charset="ISO-8859-1">
<title>Dashboard Men</title>
</head>
<body>
	<% 
	HttpSession sesh2 = request.getSession(); 
	String seshmsg = sesh2.getAttribute("username").toString(); 
	out.println("<h1>" + seshmsg + "</h1>");
	%>
	WELCOME ${user.getUsername()} 
	
	<br/>
	<button onClick = "window.location.href='/ORM/pages/register.jsp'">Register User</button><br/>
	<button onClick = "window.location.href='/ORM/pages/display.jsp'">Display Product</button><br/>
	<button onClick = "window.location.href='/ORM/pages/disable.jsp'">Disable Users</button><br/>
	<button onClick = "window.location.href='/ORM/pages/update.jsp'">Update Email / Pass</button>
	<button onClick = "window.location.href='/ORM/pages/newProduct.jsp'">Add Product</button>
	<button onClick = "window.location.href='/ORM/pages/updateProduct.jsp'">Update Product</button>
	<!-- <form>
	<input type="submit" value ="Update">
	</form> -->
	<form action = "Logout" method = "post">
		<input type="submit" value = "Logout">
	</form>
	
	<h2>${msg}</h2>
</body>
