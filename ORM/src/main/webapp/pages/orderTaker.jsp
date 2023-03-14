<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Taker</title>
<script>
	function confirmUpdate(orderId) {
		var remarks = document.getElementById("remarks_" + orderId).value;
		var message = "Are you sure you want to update this order?";
		if (remarks != null) {
			message += "\nRemarks: " + remarks;
		}
		return confirm(message);
	}
</script>
</head>
<body>
	<c:forEach items="${allOrders}" var="order">
		<form action="updateOrders?orderID=${order.orderId}" method="post"
			onsubmit="return confirmUpdate(${order.orderId})">
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
					<td><input type="text" id="remarks_${order.orderId}" name="remarks" maxlength="30"></input></td>
					<td><button type="submit">Apply Changes</button></td>
				</tr>
			</table>
		</form>
		<br>
	</c:forEach>
</body>
</html>