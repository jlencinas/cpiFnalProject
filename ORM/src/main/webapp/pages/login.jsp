<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
        rel="stylesheet">
    <title>Log in - Ordersite</title>
	<script type="text/javascript" src="resources/js/login.js"></script>
	<script type="text/javascript" src="resources/js/controller.js"></script>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>


<script>
	var contextPath = '${pageContext.request.contextPath}' + '/';
</script>

</head>

<body>
    <div class="logInDiv">
        <div class="loginLogo">
            <img class = "logo" src="resources/images/Logo-01.png" alt="">
        </div>
        <div class="loginTitle">
            <h1>Login</h1>
        </div>
        <div class="inputFieldLogin">
            <div class="inputField">
                <h3>User Name:</h3>
                <div class="inputFieldLogo">
                    <ion-icon name="person" class = "user-logo"></ion-icon>
                    <input type="text" id = "username">
                </div>
            </div>
            <div class="inputField">
                <h3>Password:</h3>
                <div class="inputFieldLogo">
                    <ion-icon name="lock-closed" class = "lock-logo"></ion-icon>
                    <input type="password" id = "password">
                </div>
            </div>
        </div>
        <div class="forgotPasswordField">
            <a href="">Forgot password?</a>
        </div>
        <div class="buttonDiv">
        <input class="submitButton" id="loginButton" type="submit"
				value="Log In">
           <!--  <button id="loginButton">LOGIN</button> -->
        </div>
    </div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		initLogin();
	});
</script>
</html>