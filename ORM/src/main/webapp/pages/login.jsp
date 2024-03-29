<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
<script type="text/javascript"
	src="<c:url value="/resources/js/login.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/controller.js"/>"></script>
<script type="module"
	src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.6.4.min.js"/>"></script>
<title>Login</title>

<script>
	var contextPath = '${pageContext.request.contextPath}' + '/';
</script>
</head>
<body>
	<div class="logInDiv">
		<div class="loginLogo">
			<a href = "/ORM/"> <img id = "login-logo" class="logo" src="<c:url value="resources/images/Logo-01.png"/>" alt=""></a>
		</div>
		<h2 class = "message-text">${msg}</h2>
		<div class="loginTitle">
			<h1>Login</h1>
		</div>
			<div class="inputFieldLogin">
				<div class="inputField">
					<h3>User Name:</h3>
					<div class="inputFieldLogo">
						<ion-icon name="person" class="user-logo"></ion-icon>
						<input name = "username" type="text" id="username" maxlength = "20" pattern="[a-zA-Z0-9\s]+" required>
					</div>
				</div>
				<div class="inputField">
					<h3>Password:</h3>
					<div class="inputFieldLogo">
						<ion-icon name="lock-closed" class="lock-logo"></ion-icon>
						<input name = "password" type="password" id="password" maxlength = "12"  required>
					</div>
				</div>
			</div>
			<div class="forgotPasswordField">
				<a href="#" id = "forgot-pass-button">Forgot password?</a>
			</div>
			<div class="buttonDiv">
				<button id = "loginButton" >LOGIN</button>
			</div>
	</div>
	
</body>
	<script type="text/javascript">
		$(document).ready(function() {
			initButtons();
		});
	</script>
</html>