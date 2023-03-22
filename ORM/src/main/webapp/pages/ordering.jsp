<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordering</title>
</head>
<body>
	<form action="NewOrder" method="post">
		<table>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="t1"></td>
			</tr>
			
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="t2"></td>
			</tr>
			
			<tr>
				<td>Mobile Number</td>
				<td><input type="number" name="t3"></td>
			</tr>
			
			<tr>
				<td>Expected Delivery Date</td>
				<td><input type="date" name="dates" pattern="MM/DD/YYYY"></td>
			</tr>
			 <tr>
				<td>Expected Delivery Schedule</td>
				<td><input type="time" name="times"></td>
			</tr>
		</table>
	<table>
		<tr>
			<th>Product Name</th>
			<th>Product Description</th>
			<th>Product Picture</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Update Quantity</th>
		</tr>
		<c:forEach var="order" varStatus="loop" items="${orderDetails}">
			<tr>
				<td>${order.orderName}</td>
				<td>${order.orderDescription}</td>
				<td><img src="${order.orderPicture}" /></td>
				<td>${order.orderPrice}</td>
				<td>${order.orderquantity}</td>
				<td><input type="number" name="quantity${loop.index}"
					value="${order.orderquantity}"></td>
				<td><input type="hidden" name="itemId${loop.index}"
					value="${order.orderItemID}"></td>
				<td><input type="hidden" name="oldQuantity${loop.index}"
					value="${order.orderquantity}"></td>
			</tr>
		</c:forEach>
	</table>
	Total Price: ${TotalPrice}
	<input type="submit" value="Update Quantities">
	<input type="reset" value="Clear">
	</form>
</body>
</html>