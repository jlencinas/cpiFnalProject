<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<head>
	<title>LOGIN PAGE</title>
</head>
<body>
<h2>GOODBYE CRUEL WORLD</h2>
${message} 
<form action="Login" method="post">
	<label>Username: </label><input type="text" name = "username"><br/>
	<label>Password: </label><input type="password" name = "password"><br/>
	<input type="submit" value = "Submit"><input type="reset" value ="Clear">
</form>

</body>

