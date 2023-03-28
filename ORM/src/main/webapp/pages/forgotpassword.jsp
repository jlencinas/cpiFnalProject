<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/forgotPassword.css"/>">
	<script type="text/javascript" src="<c:url value="/resources/js/controller.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.6.4.min.js"/>"></script>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script>var contextPath = '${pageContext.request.contextPath}' + '/';</script>
</head>

<body>
    <div class="card-container">
        <div class="forgotPassLogo">
	        <a href = "#" id="goback-login" onclick = "loginButton(contextPath)">
	        	<img class = "logo" src="../resources/images/Logo-01.png" alt="">
	        </a>
        </div>
        <div class="title">
            <h1>Forgot Password</h1>
        </div>
		<h2 class = "message-text">${msg}</h2>
        <div class="inputFieldContainer">
            <div class="inputField">
                <div class="inputFieldLabel">
                    <h3>Username: </h3>
                </div>
                <div class="inputFieldInput">
                    <input type="text" id = "username-input" required>
                </div>
            </div>
            <div class="inputField">
                <div class="inputFieldLabel">
                    <h3>E-mail Address:</h3>
                </div>
                <div class="inputFieldInput">
                    <input type="email"
                    placeholder="sample123@email.com"
                    id = "email-input" required>
                </div>
            </div>
        <div class="buttonDiv">
            <button type = "submit" onclick = "forgotPassword()">Reset</button>
        </div>
    </div>
</body>
</html>    
    