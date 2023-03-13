<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Taker</title>
</head>
<body>
	<c:forEach items="${allOrders}" var="order">
		<form action="updateOrders" method="post">
			<table>
				<tr>
					<th>Order ID</th>
					<th>Order Status</th>
					<th>Payment Status</th>
				</tr>
				<tr>
					<td><c:out value="${order.orderId}"></c:out></td>
					<td><select name="orderStatus">
							<option value="1"
								<c:if test = "${order.orderStatus == 1}">selected</c:if>>Pending</option>
							<option value="2"
								<c:if test = "${order.orderStatus == 2}">selected</c:if>>Ready
								for Pick Up</option>
							<option value="3"
								<c:if test = "${order.orderStatus == 3}">selected</c:if>>Completed</option>
							<option value="50"
								<c:if test = "${order.orderStatus == 50}">selected</c:if>>Cancelled</option>
							<option value="90"
								<c:if test = "${order.orderStatus == 90}">selected</c:if>>Rejected</option>
					</select></td>
					<td><select name="paymentStatus">
							<option value="1"
								<c:if test="${order.paymentStatus == 1}">selected</c:if>>Not
								Paid</option>
							<option value="2"
								<c:if test="${order.paymentStatus == 2}">selected</c:if>>Paid</option>
					</select></td>
					<td><button type="submit">Apply Changes</button></td>
				</tr>
			</table>
			<input type="hidden" name="orderID" value="${order.orderId}" />
		</form>
		<br>
	</c:forEach>
</body>
</html>