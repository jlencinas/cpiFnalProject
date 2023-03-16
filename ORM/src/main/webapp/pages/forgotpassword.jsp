<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<form action="Forgot" method="post">
	<h5>Forgot Password</h5>
	<label>Username: </label><input type="text" name = "username"><br/>
	<label>Email: </label><input type="email" name = "email"><br/>
	<input type="submit" value = "Submit"><input type="reset" value ="Clear">
</form><br/>
${message} 