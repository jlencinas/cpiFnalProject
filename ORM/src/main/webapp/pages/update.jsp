<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.sql.*" %>


<head>
<meta charset="ISO-8859-1">
<title>Update Information</title>
</head>
<body>

	<form action="Update" method="post">
		<label>Username: </label><input type="text" name = "username"><br/>
		<label>Password: </label><input type="password" name = "password"><br/>
		<label>New Email: </label><input type="text" name = "new email"><br/>
		<label>New Pass</label><input type="password" name = "new pass"><br/>
		<label>Confirm New Pass</label><input type="password" name = "con pass">
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
	
</body>
