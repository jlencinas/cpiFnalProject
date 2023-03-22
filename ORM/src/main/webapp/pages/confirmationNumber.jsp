<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/resources/css/confirmationNumber.css"/>">
    <title>Confirmation Number</title>
</head>
    <body>
        <div class="card-container-confirmation-number">
			<script> var contextPath = '${pageContext.request.contextPath}' + '/';</script>
			<c:set var="seshinfo" value="${cart}" />
			<c:set var="seshinfoPrice" value="${price_cart}" />
			<c:set var="seshcart" value="${not empty seshinfo ? 'true' : 'false'}"/>
			<script>var boolCart = '${seshcart}'</script>
			<c:set var="confNum" value="${confNumber}" />
			<script>var confirmation = '${confNum}'</script>
			<script>
				checkCartSession(boolCart);
				initFunction();
				checkQuan();
			</script>
            <div class="title-confirmation-number">
                <h2>Thank you!</h2>
                <h3>Your order is in process.</h3>
            </div>
            <div class="message-confirmation-number">
                <h3>This is you confirmation number, Please <span>Remember</span> it.</h3>
                <h1 id = "confirmation-num" class = "confirmation-num"></h1>
            </div>
            <div class="close-button-confirmation-number">
                <button class="button-exit-confirmation-box"  onclick =  "closeConfirmationPopup()">
                    Close
                </button>
            </div>
        </div>
        <script>
        $(document).ready(function() {
			initConfNum(confirmation);
			checkCartSession(boolCart);
			initFunction();
		});
        </script>
    </body>
</html>