<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>    
    
<head>
	<title>INDEX JSP</title>
	<!-- <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
	
	<script type="text/javascript">
    $(document).ready(function() {
        checkSession();
    });
    
    function checkSession() {
        console.log("hello");
        console.log(contextPath);
        $.ajax({
            url: contextPath + "CheckSession",
            method: "POST",
            success: function(result) {
                $("#mainDiv").html(result);
            }
        });
    }
	</script>
	<script type="text/javascript">
	    var contextPath = '${pageContext.request.contextPath}' + '/';
	</script> -->
</head>
<body>
	<h2>GOODBYE CRUEL WORLD</h2><br/>
	${message} 
	
<!-- 	<form action="Login" method="post">
		<label>Login Account</label>
		<label>Username: </label><input type="text" name = "username"><br/>
		<label>Password: </label><input type="password" name = "password"><br/>
		<input type="submit" value = "Submit"><input type="reset" value ="Clear">
	</form> -->
	<button onClick = "window.location.href='/ORM/pages/login.jsp'">Login</button><br/>

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
	
<!-- 	<form action="Forgot" method="post">
		<label>Forgot Password</label>
		<label>Username: </label><input type="text" name = "username"><br/>
		<label>Email: </label><input type="email" name = "email"><br/>
		<input type="submit" value = "Submit"><input type="reset" value ="Clear">
	</form> -->
	
<!-- 	<form action="Update" method="post">
		<label>Update</label><br/>
		<label>Username: </label><input type="text" name = "username"><br/>
		<label>Password: </label><input type="password" name = "password"><br/>
		<label>New Pass</label><input type="password" name = "new pass"><br/>
		<label>Confirm New Pass</label><input type="password" name = "con pass">
		<input type="submit" value = "Submit"><input type="reset" value ="Clear">
	</form> -->
	
</body>

