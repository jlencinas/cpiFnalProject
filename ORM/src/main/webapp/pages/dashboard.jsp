<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard Men</title>
</head>
<body>
	
	WELCOME ${user.getUsername()} 
	
	<br/>
	<button onClick = "window.location.href='/ORM/pages/register.jsp'">Register</button><br/>
	<button onClick = "window.location.href='/ORM/pages/update.jsp'">Update</button>
	<button onClick = "window.location.href='/ORM/pages/newProduct.jsp'">Add Product</button>
	<!-- <form>
	<input type="submit" value ="Update">
	</form> -->
	<form action = "Logout" method = "post">
		<input type="submit" value = "Logout">
	</form>
	
	<h2>${msg}</h2>
</body>
</html>