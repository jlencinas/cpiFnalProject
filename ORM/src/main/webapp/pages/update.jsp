<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.sql.*" %>


<head>
<meta charset="ISO-8859-1">
<title>Update Information</title>
<script>
  
function matchPassword() {  
	var pw1 = document.getElementById("pswd1");  
	var pw2 = document.getElementById("pswd2");  
	if(pw1 != pw2)  {   
	  alert("Passwords did not match");  
	} 
	else {  
	  alert("Passwords matches");  
	}  
}  
</script>

</head>
<body>
	DATA HELD: ${user}<br/>
	DATA HELD: ${user.getUserId()} <br/>
	DATA HELD: ${user.getEmail()} <br/>
	DATA HELD: ${user.getPassword()} <br/>
	
	<form>
	
	</form>
	
	<form>   
		<h3>Password Validation </h3>   
		<td> Enter Password </td>  
		<input type = "password" name = "pswd1"> <br><br>   
		<td> Confirm Password </td>  
		<input type = "password" name = "pswd2" onChange= "matchPassword()"> <br><br>  
		<button type = "submit" hidden= "true">Submit</button>  
		<button type = "reset" value = "Reset" >Reset</button>  
	</form>  
	
</body>
