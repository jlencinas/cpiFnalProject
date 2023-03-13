<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.cpi.model.Users"%>

<head>
<meta charset="ISO-8859-1">
<title>Update Information</title>
</head>
<body>
	<% 
		HttpSession sesh2 = request.getSession(); 
		Users seshinfo = (Users)sesh2.getAttribute("userAccount"); 
		int uid = seshinfo.getUserId();
		String un = seshinfo.getUsername();
		String pwd = seshinfo.getPassword();
		/* out.println("<p>" + uid + "</p>");
		out.println("<p>" + seshinfo.getRoleId() + "</p>");
		out.println("<p>" + un + "</p>");
		out.println("<p>" + pwd + "</p>");
		out.println("<p>" + seshinfo.getEmail() + "</p>");
		out.println("<p>" + seshinfo.getStatus() + "</p>"); */
	%>
	<form action="Update" method="post">
		<h6>Only Fill Blanks to be Updated</h6>
		<input type="hidden" value = "<%= un %>" name = "username">
		<label>New Email: </label><input type="email" name = "new email"><br/>
		<label>Password: </label><input type="password" name = "password"><br/>
		<label>New Password</label><input type="password" name = "new pass"><br/>
		<label>Confirm New Password</label><input type="password" name = "con pass">
		<input type="submit" value = "Submit"><input type="reset" value ="Clear">
	</form>
	
<!-- 	<form>   
		<h3>Password Validation </h3>   
		<td> Enter Password </td>  
		<input type = "password" name = "pswd1"> <br><br>   
		<td> Confirm Password </td>  
		<input type = "password" name = "pswd2" onChange= "matchPassword()"> <br><br>  
		<button type = "submit" hidden= "true">Submit</button>  
		<button type = "reset" value = "Reset" >Reset</button>  
	</form>   -->
	<button onClick = "window.location.href='/ORM/pages/dashboard.jsp'">Back</button>
</body>
