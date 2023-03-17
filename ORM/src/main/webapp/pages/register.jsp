<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register New User</title>
</head>
<body>
	<h2>${msg}</h2>
	<form action="Register" method="post">
		<table>
		<tr>
			<td>USERNAME</td><td><input type="text" name = "username"></td>
		</tr>
		<tr>
			<td>EMAIL</td><td><input type="email" name = "email"></td>
		</tr>
		<tr>
			<td>PASSWORD</td><td><input type="password" name = "password"></td>
		</tr>
		<tr>
			<td>ROLE</td>
			<td>
				<select name="roleid">
					 <option value="1">ADMINISTRATOR</option>
					 <option value="2">PRODUCER</option>
					 <option value="3">ORDER TAKER</option>
					 <option value="4">AUDITOR</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value = "Register"></td>
			<td><input type="reset" value = "Clear"></td>
		</tr>	
		</table>
	</form>
	<button onClick = "window.location.href='/ORM/pages/dashboard.jsp'">Back</button>
</body>
</html>