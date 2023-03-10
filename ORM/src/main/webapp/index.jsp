<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<head>
	<title>INDEX JSP</title>
</head>
<body>
	<h2>GOODBYE CRUEL WORLD</h2>
	${message} 
	<form action="Login" method="post">
		<legend>Login Account</legend>
		<label>Username: </label><input type="text" name = "username"><br/>
		<label>Password: </label><input type="password" name = "password"><br/>
		<input type="submit" value = "Submit"><input type="reset" value ="Clear">
	</form>
	<br>

	<!-- <form action="Register" method="post">
		<legend>Register Account</legend>
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
			<td><input type="submit" value = "Submit"></td>
			<td><input type="reset" value = "Clear"></td>
		</tr>	
		</table>
	</form> -->
	<br/>
	
	<form action="" method="post">
		<legend>Forgot Password</legend>
		<label>Username: </label><input type="text" name = "username"><br/>
		<label>Email: </label><input type="email" name = "email"><br/>
		<input type="submit" value = "Submit"><input type="reset" value ="Clear">
	</form>
	
</body>

