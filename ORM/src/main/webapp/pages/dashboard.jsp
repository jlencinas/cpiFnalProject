<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.cpi.model.Users"%>

<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<% 
		HttpSession sesh2 = request.getSession(); 
		Users seshinfo = (Users)sesh2.getAttribute("userAccount"); 
		int uid = seshinfo.getUserId();
	%>
	
	<br/>
	<h4>Account Controls</h4>
	<% 
		int rid = seshinfo.getRoleId();
		if(rid == 1){ %>
		<button onClick = "window.location.href='/ORM/pages/register.jsp'">Register User</button>
		<!-- <button onClick = "window.location.href='/ORM/pages/ShowUsers'">Disable / Edit Users</button> -->
		<form action = "ShowUsers" method = "post">
			<input type ="hidden" value ="<%= uid %>" name ="userid">
			<input type = "submit" value = "Disable / Edit Users">
		</form>
	<%	}
	%>
	
	
	<button onClick = "window.location.href='/ORM/pages/updateuserinfo.jsp'">Update Email / Pass</button>
	
	<h4>Product Controls</h4>
	<button onClick = "window.location.href='/ORM/pages/DisplayProduct'">Display Product</button><br/>
	<button onClick = "window.location.href='/ORM/pages/ordering.jsp'">Ordering</button><br/>
	<button onClick = "window.location.href='/ORM/pages/newProduct.jsp'">Add Product</button>
	
	<!-- <button onClick = "window.location.href='/ORM/pages/listOfProducts.jsp'">Update Products</button> -->
	<form action ="listOfProducts" method="post">
		<input type="submit" value="Update Products">
	</form>
	
	
	<% if(rid ==3) {%>
	<h4>Order Controls</h4>
	<button onClick = "window.location.href='/ORM/pages/orderDetails.jsp'">Order Details</button><br/>
	<button onClick = "window.location.href='/ORM/pages/DisplayUpdateOrder'">Update Order Details</button><br/>
	
	<form action = "orderTaker" method = "post">
		<input type ="submit" value = "Display Orders">
	</form>
	<% } %>
	
	<form action = "Logout" method = "post">
		<input type="submit" value = "Logout">
	</form>
	
	<h2>${msg}</h2>
</body>
