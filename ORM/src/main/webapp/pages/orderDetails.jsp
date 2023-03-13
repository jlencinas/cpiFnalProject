<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Details</title>
</head>
<body>
	<form action="orderDetails" method="post">
		<input type="number" name="mobileNumber" pattern="[0-9]+" required>
		<input type="submit" value="Submit">
	</form>

	<table>
		<tr>
			<th>Order ID</th>
			<th>Order Status</th>
			<th>Expected Delivery Date</th>
			<th>Payment Status</th>
		</tr>
		<tr>
			<td><c:out value="${order.orderId}"></c:out></td>
			<td><c:out value="${order.orderStatus}"></c:out></td>
			<td><c:out value="${order.deliveryDate}"></c:out></td>
			<td><c:out value="${order.paymentStatus}"></c:out></td>
		</tr>
	</table>
</body>
</html>