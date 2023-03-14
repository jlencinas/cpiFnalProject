<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Order Details</title>
</head>
<body>
<h1>Update Order Details</h1>
<form action="UpdateOrderDetails" method="post">
    <table>
        <tr>
            <th>Item ID</th>
            <th>Order ID</th>
            <th>Product ID</th>
            <th>Product Price</th>
            <th>Discount</th>
            <th>Total Price</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Update Quantity</th>
        </tr>
        <c:forEach items="${updateorderdetails}" var="updateOrderDetails">
            <tr>
                <td>${updateOrderDetails.item_id}</td>
                <td>${updateOrderDetails.order_id}</td>
                <td>${updateOrderDetails.product_id}</td>
                <td>${updateOrderDetails.price}</td>
                <td>${updateOrderDetails.discount}</td>
                <td>${updateOrderDetails.totalPrice}</td>
                <td>${updateOrderDetails.productName}</td>
                <td>${updateOrderDetails.quantity}</td>
                <td><input type="number" name="itemId" value="${updateOrderDetails.quantity}"></td>
                <td><input type="hidden" name="itemIds" value="${updateOrderDetails.item_id}"></td>    
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Update Quantities">
</form>
</body>
</html>
