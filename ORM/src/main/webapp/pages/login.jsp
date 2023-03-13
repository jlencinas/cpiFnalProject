<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

	<form action="Login" method="post">
		<label>Login Account</label>
		<label>Username: </label><input type="text" name = "username"><br/>
		<label>Password: </label><input type="password" name = "password"><br/>
		<input type="submit" value = "Login"><input type="reset" value ="Clear">
	</form>
	<button onClick = "window.location.href='/ORM/pages/forgotpassword.jsp'">Forgot Password?</button>
</body>
</html>