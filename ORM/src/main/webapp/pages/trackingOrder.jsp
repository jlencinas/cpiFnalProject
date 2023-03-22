<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/resources/css/trackingOrder.css"/>">
	<script type="text/javascript" src="<c:url value="/resources/js/controller.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script> var contextPath = '${pageContext.request.contextPath}' + '/';</script>
    <title>Tracking Order</title>
</head>
    <body>
    	<c:set var = "orderStat" value = "${order.orderStatus}"/>
    	
    	<c:if test =  "${orderStat == 1}">
    		<c:set var = "orderStatus" value = "Pending"/>
    	</c:if>
    	<c:if test =  "${orderStat == 2}">
    		<c:set var = "orderStatus" value = "Ready for Pick Up"/>
    	</c:if>
    	<c:if test =  "${orderStat == 3}">
    		<c:set var = "orderStatus" value = "Completed"/>
    	</c:if>
    	<c:if test =  "${orderStat == 50}">
    		<c:set var = "orderStatus" value = "Cancelled"/>
    	</c:if>
    	<c:if test =  "${orderStat == 90}">
    		<c:set var = "orderStatus" value = "Rejected"/>
    	</c:if>
    	<c:set var = "orderDelDate" value = "${order.deliveryDate}"/>
    	<c:set var = "orderPaymentStat" value = "${order.paymentStatus}"/>
    	
    	<c:if test =  "${orderPaymentStat == 1}">
    		<c:set var = "orderPaymentStatus" value = "Not Paid"/>
    	</c:if>
    	<c:if test =  "${orderPaymentStat == 2}">
    		<c:set var = "orderPaymentStatus" value = "Paid"/>
    	</c:if>
        <div class="card-container-tracking-order">
            <div class="title-tracking-order">
                <h2 id = "tracking-order-title">${confNum}</h2>
            </div>
            <div class="status-tracking-order">
                <h3>Order Status: </h3>
                <h3>${orderStatus}</h3>
            </div>
            <div class="delivery-date-tracking-order">
                <h3>Expected Deliver Date: </h3>
                <h3>${orderDelDate}</h3>
            </div>
            <div class="payment-tracking-order">
                <h3>Payment Status: </h3>
                <h3>${orderPaymentStatus}</h3>
            </div>
            <div class="button-tracking-order">
                <button class="button-exit-tracking-box" onclick = "closeTrackOrderPopup()">
                        Close
                </button>
            </div>
        </div>
    </body>
</html>