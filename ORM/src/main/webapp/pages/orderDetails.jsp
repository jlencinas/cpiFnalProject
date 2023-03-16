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
            <c:choose>
                <c:when test="${order.orderStatus == 1}">
                    <td>Pending</td>
                </c:when>
                <c:when test="${order.orderStatus == 2}">
                    <td>Ready for Pick Up</td>
                </c:when>
                <c:when test="${order.orderStatus == 3}">
                    <td>Completed</td>
                </c:when>
                <c:when test="${order.orderStatus == 50}">
                    <td>Cancelled</td>
                </c:when>
                <c:when test="${order.orderStatus == 90}">
                    <td>Rejected</td>
                </c:when>
            </c:choose>
            <td><c:out value="${order.deliveryDate}"></c:out></td>
            <c:choose>
                <c:when test="${order.paymentStatus == 2}">
                    <td>Paid</td>
                </c:when>
                <c:when test="${order.paymentStatus == 1}">
                    <td>Not Paid</td>
                </c:when>
            </c:choose>
        </tr>
    </table>
    <button onClick="window.location.href='/ORM/pages/dashboard.jsp'">Back</button>
</body>
</html>