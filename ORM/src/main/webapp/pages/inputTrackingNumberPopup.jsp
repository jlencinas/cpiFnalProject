<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/resources/css/trackingNumber.css"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script> var contextPath = '${pageContext.request.contextPath}' + '/';</script>
    <title>Tracking Order</title>
</head>
    <body>
        <div class="card-container-tracking-number">
            <div class="title-tracking-number">
            	<h3 id = "message-input"></h3>
                <h2>Please Enter your Tracking Number</h2>
            </div>
            <div class="input-tracking-number">
                <input id = "input-tracking-code" type="text" oninput="this.value = this.value.toUpperCase()">
            </div>
            <div class="button-tracking-number">
                <button class="button-exit-tracking-box" onclick = "closeTrackOrderPopup()">
                        Close
                </button>
                <button class="button-enter-tracking-box" onclick = "enterTrackingOrder()">
                        Enter
                </button>
            </div>
        </div>
    </body>
</html>