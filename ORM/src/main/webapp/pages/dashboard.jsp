<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.cpi.model.Users"%>

<head>
<meta charset="ISO-8859-1">
<title>Dashboard Men</title>
</head>
<body>
	<% 
		HttpSession sesh2 = request.getSession(); 
		Users seshmsg = (Users)sesh2.getAttribute("userAccount"); 
		out.println("<p>" + seshmsg.getUserId() + "</p></br>");
		out.println("<p>" + seshmsg.getRoleId() + "</p></br>");
		out.println("<p>" + seshmsg.getUsername() + "</p></br>");
		out.println("<p>" + seshmsg.getPassword() + "</p></br>");
		out.println("<p>" + seshmsg.getEmail() + "</p></br>");
		out.println("<p>" + seshmsg.getStatus() + "</p></br>");
	%>
	
	<br/>
	<h4>Account Controls</h4>
	<button onClick = "window.location.href='/ORM/pages/register.jsp'">Register User</button>
	<button onClick = "window.location.href='/ORM/pages/update.jsp'">Update Email / Pass</button>
	<button onClick = "window.location.href='/ORM/pages/disable.jsp'">Disable Users</button>
	
	<h4>Product Controls</h4>
	<button onClick = "window.location.href='/ORM/pages/display.jsp'">Display Product</button><br/>
	<button onClick = "window.location.href='/ORM/pages/ordering.jsp'">Ordering</button><br/>
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
