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
		out.println("<h1>" + seshmsg.getUserId() + "</h1></br>");
		out.println("<h1>" + seshmsg.getRoleId() + "</h1></br>");
		out.println("<h1>" + seshmsg.getUsername() + "</h1></br>");
		out.println("<h1>" + seshmsg.getPassword() + "</h1></br>");
		out.println("<h1>" + seshmsg.getEmail() + "</h1></br>");
		out.println("<h1>" + seshmsg.getStatus() + "</h1></br>");
	%>
	<%-- WELCOME ${user.getUsername()}  --%>
	
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
